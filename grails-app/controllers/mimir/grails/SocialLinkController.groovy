package mimir.grails

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*


@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class SocialLinkController {

    static responseFormats = ['json']

    def index() {
        respond SocialLink.findAllByProfile(Profile.get(params.profileId))
    }
}
