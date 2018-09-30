package mimir.grails

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*


@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class PictureController {

    PictureService pictureService

    def index() {
        forward(action: "show", id: Picture.findByProfile(Profile.get(params.profileId)).id)
    }

    def show(Long id) {
        respond pictureService.get(id)
    }
}
