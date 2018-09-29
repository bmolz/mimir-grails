package mimir.grails

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*


@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class ProfileController {

    ProfileService profileService

    static responseFormats = ['json']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        respond profileService.list(params), model:[profileCount: profileService.count()]
    }

    def show(Long id) {
        respond profileService.get(id)
    }
}
