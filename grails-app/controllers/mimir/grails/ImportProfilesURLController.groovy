package mimir.grails

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ImportProfilesURLController {

    ImportProfilesURLService importProfilesURLService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond importProfilesURLService.list(params), model:[importProfilesURLCount: importProfilesURLService.count()]
    }

    def show(Long id) {
        respond importProfilesURLService.get(id)
    }

    def save(ImportProfilesURL importProfilesURL) {
        if (importProfilesURL == null) {
            render status: NOT_FOUND
            return
        }

        try {
            importProfilesURLService.save(importProfilesURL)
        } catch (ValidationException e) {
            respond importProfilesURL.errors, view:'create'
            return
        }

        respond importProfilesURL, [status: CREATED, view:"show"]
    }

    def update(ImportProfilesURL importProfilesURL) {
        if (importProfilesURL == null) {
            render status: NOT_FOUND
            return
        }

        try {
            importProfilesURLService.save(importProfilesURL)
        } catch (ValidationException e) {
            respond importProfilesURL.errors, view:'edit'
            return
        }

        respond importProfilesURL, [status: OK, view:"show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        importProfilesURLService.delete(id)

        render status: NO_CONTENT
    }
}
