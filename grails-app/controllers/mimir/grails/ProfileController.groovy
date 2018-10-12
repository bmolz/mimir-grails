package mimir.grails

import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*


class ProfileController {

    ProfileService profileService
    SpringSecurityService springSecurityService

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index() {
        def max = Math.min(params?.max?.toInteger() ?: 200, 500)
        def offset = params?.offset?.toInteger() ?: 0
        def sortBy = ['firstName', 'lastName', 'jobTitle', 'slug'].contains(params?.sortBy) ? params.sortBy : 'firstName'
        def orderBy = ['desc', 'asc'].contains(params?.orderBy) ? params.orderBy : 'asc'

        def profiles = Profile.createCriteria().list(max:max, offset:offset){
            if(params.search) {
                or {
                    ilike('firstName', "%${params.search}%")
                    ilike('lastName', "%${params.search}%")
                    ilike('bio', "%${params.search}%")
                    ilike('jobTitle', "%${params.search}%")
                }
            }
            order(sortBy, orderBy)
        }
        respond([
            profiles: profiles,
            count: profiles.getTotalCount(),
            max: max,
            offset: offset
        ], status: 200)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def show(Long id) {
        def result =  Profile.get(id)
        if(result) respond result
        else render status: NOT_FOUND
    }

    def login() {
        def user = User.findByUsername('player')
        if(params.id) {
            user = User.findByUsername(params?.id)
            if(!user) {
                user = new User(username: params?.id, password: params?.id).save()
                def userRole = Role.findByAuthority('ROLE_USER')
                UserRole.create user, userRole
                UserRole.withSession {
                    it.flush()
                    it.clear()
                }
            }
        }
        println "Logging in as ${user.username}"
        springSecurityService.reauthenticate(user.username, user.username)
        def ret = [message: "Logging in as ${user.username} (resend request if intercepted)"]
        render ret as JSON
    }
}
