package mimir.grails

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SocialLinkController {

    SocialLinkService socialLinkService

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond socialLinkService.list(params), model:[socialLinkCount: socialLinkService.count()]
    }

    def show(Long id) {
        respond socialLinkService.get(id)
    }

    def save(SocialLink socialLink) {
        if (socialLink == null) {
            render status: NOT_FOUND
            return
        }

        try {
            socialLinkService.save(socialLink)
        } catch (ValidationException e) {
            respond socialLink.errors, view:'create'
            return
        }

        respond socialLink, [status: CREATED, view:"show"]
    }

    def update(SocialLink socialLink) {
        if (socialLink == null) {
            render status: NOT_FOUND
            return
        }

        try {
            socialLinkService.save(socialLink)
        } catch (ValidationException e) {
            respond socialLink.errors, view:'edit'
            return
        }

        respond socialLink, [status: OK, view:"show"]
    }

    def delete(Long id) {
        if (id == null) {
            render status: NOT_FOUND
            return
        }

        socialLinkService.delete(id)

        render status: NO_CONTENT
    }
}
