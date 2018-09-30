package mimir.grails

import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityService
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*


class ProfileController {

    ProfileService profileService
    SpringSecurityService springSecurityService

    static responseFormats = ['json']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index(Integer max) {
        respond profileService.list(params), model:[profileCount: profileService.count()]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def show(Long id) {
        respond profileService.get(id)
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
