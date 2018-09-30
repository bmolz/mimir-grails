package mimir.grails

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(['ROLE_ADMIN'])
class ImportProfilesURLController {

    ImportProfilesURLService importProfilesURLService
    ImportService importService

    static allowedMethods = [save: "POST"]

    def index() {
        respond importProfilesURLService.list(), model:[importProfilesURLCount: importProfilesURLService.count()]
    }

    def show(Long id) {
        respond importProfilesURLService.get(id)
    }

    def save(ImportProfilesURL importProfilesURL) {
        if (importProfilesURL == null) {
            render status: NOT_FOUND
            return
        }

        importProfilesURL.count = importService.importProfiles(importProfilesURL.url.toURL().text)

        try {
            importProfilesURLService.save(importProfilesURL)
        } catch (ValidationException e) {
            respond importProfilesURL.errors, view:'create'
            return
        }

        respond importProfilesURL, [status: CREATED, view:"show"]
    }
}
