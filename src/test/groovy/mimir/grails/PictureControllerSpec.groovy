package mimir.grails

import spock.lang.*
import static org.springframework.http.HttpStatus.*
import grails.validation.ValidationException
import grails.testing.web.controllers.ControllerUnitTest
import grails.testing.gorm.DomainUnitTest

class PictureControllerSpec extends Specification implements ControllerUnitTest<PictureController>, DomainUnitTest<Picture> {

    def setup() {
        new Profile([uuid:'aaaa', type:'people', slug: 'harry-potter', jobTitle: 'Senior Auror', firstName: 'Harry', lastName: 'Potter', headshot:[uuid:'bbbb', url:'//harry.jpg', alt: '1 image alt text', height: 340, width: 340, type: 'image', mimeType:'image/jpeg']]).save()
        new Profile([uuid:'aaab', type:'people', slug: 'ron-weasley', jobTitle: 'Master Prankster', firstName: 'Ronald', lastName: 'Weasley', headshot:[uuid:'bbbc', url:'//ron.jpg', alt: '2 image alt text', height: 340, width: 340, type: 'image', mimeType:'image/jpeg']]).save()
        new Profile([uuid:'aaac', type:'people', slug: 'hermione-granger', jobTitle: 'Minister for Magic', firstName: 'Hermione', lastName: 'Granger', headshot:[uuid:'bbbd', url:'//hermione.jpg', alt: '3 image alt text', height: 340, width: 340, type: 'image', mimeType:'image/jpeg']]).save()
    }

    void "GET /api/profile/{id}/picture forwards to /picture/show"() {
        when:"Index with profileId"
        params.profileId = 2
        controller.index()

        then:"Check forwardedUrl"
        response.status == OK.value()
        response.forwardedUrl == '/picture/show/2?profileId=2'
    }


    void "GET /picture/show/{id} with null id"() {
        when:"The show action is executed with a null domain"
        controller.show()

        then:"A 404 error is returned"
        response.status == NOT_FOUND.value()
    }

    void "GET /picture/show/{id} with valid id"() {
        when:"A domain instance is passed to the show action"
        controller.show(3)

        then:"A model is populated containing the domain instance"
        response.status == OK.value()
        println response.json
    }
}
