package mimir.grails

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PictureController {

    PictureService pictureService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond pictureService.list(params), model:[pictureCount: pictureService.count()]
    }

    def show(Long id) {
        respond pictureService.get(id)
    }

    def save(Picture picture) {
        if (picture == null) {
            render status: NOT_FOUND
            return
        }

        try {
            pictureService.save(picture)
        } catch (ValidationException e) {
            respond picture.errors, view:'create'
            return
        }

        respond picture, [status: CREATED, view:"show"]
    }

    def update(Picture picture) {
        if (picture == null) {
            render status: NOT_FOUND
            return
        }

        try {
            pictureService.save(picture)
        } catch (ValidationException e) {
            respond picture.errors, view:'edit'
            return
        }

        respond picture, [status: OK, view:"show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        pictureService.delete(id)

        render status: NO_CONTENT
    }
}
