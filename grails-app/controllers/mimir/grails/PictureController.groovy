package mimir.grails

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*


@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class PictureController {

    PictureService pictureService

    def index() {
        forward(action: "show", id: Profile.get(params.profileId).headshotId)
    }

    def show(Long id) {
        def result =  Picture.get(id)
        if(result) respond result
        else render status: NOT_FOUND
    }
}
