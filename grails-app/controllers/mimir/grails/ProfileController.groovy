package mimir.grails

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ProfileController {

    ProfileService profileService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond profileService.list(params), model:[profileCount: profileService.count()]
    }

    def show(Long id) {
        respond profileService.get(id)
    }

    def save(Profile profile) {
        if (profile == null) {
            render status: NOT_FOUND
            return
        }

        try {
            profileService.save(profile)
        } catch (ValidationException e) {
            respond profile.errors, view:'create'
            return
        }

        respond profile, [status: CREATED, view:"show"]
    }

    def update(Profile profile) {
        if (profile == null) {
            render status: NOT_FOUND
            return
        }

        try {
            profileService.save(profile)
        } catch (ValidationException e) {
            respond profile.errors, view:'edit'
            return
        }

        respond profile, [status: OK, view:"show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        profileService.delete(id)

        render status: NO_CONTENT
    }
}
