package mimir.grails

import grails.converters.JSON
import spock.lang.*
import static org.springframework.http.HttpStatus.*
import grails.testing.web.controllers.ControllerUnitTest
import grails.testing.gorm.DomainUnitTest

class ProfileControllerSpec extends Specification implements ControllerUnitTest<ProfileController>, DomainUnitTest<Profile> {

    def setup() {
        new Profile([uuid:'aaaa', type:'people', slug: 'harry-potter', jobTitle: 'Senior Auror', firstName: 'Harry', lastName: 'Potter', headshot:[uuid:'bbbb', url:'//harry.jpg', alt: '1 image alt text', height: 340, width: 340, type: 'image', mimeType:'image/jpeg']]).save()
        new Profile([uuid:'aaab', type:'people', slug: 'ron-weasley', jobTitle: 'Master Prankster', firstName: 'Ronald', lastName: 'Weasley', headshot:[uuid:'bbbc', url:'//ron.jpg', alt: '2 image alt text', height: 340, width: 340, type: 'image', mimeType:'image/jpeg']]).save()
        new Profile([uuid:'aaac', type:'people', slug: 'hermione-granger', jobTitle: 'Minister for Magic', firstName: 'Hermione', lastName: 'Granger', headshot:[uuid:'bbbd', url:'//hermione.jpg', alt: '3 image alt text', height: 340, width: 340, type: 'image', mimeType:'image/jpeg']]).save()
        new Profile([uuid:'aaad', type:'people', slug: 'matt1', jobTitle: 'Engineer', firstName: 'matt1', lastName: 'Potter', headshot:[uuid:'bbbe', url:'//matt1.jpg', alt: '4 image alt text', height: 340, width: 340, type: 'image', mimeType:'image/jpeg']]).save()
        new Profile([uuid:'aaae', type:'people', slug: '2matt3', jobTitle: 'Design', firstName: '2matt3', lastName: 'Potter', headshot:[uuid:'bbbf', url:'//2matt3.jpg', alt: '5image alt text', height: 340, width: 340, type: 'image', mimeType:'image/jpeg']]).save()
        new Profile([uuid:'aaaf', type:'people', slug: '4matt', jobTitle: 'Tester', firstName: '4matt', lastName: 'Potter', headshot:[uuid:'bbbg', url:'//4matt.jpg', alt: '6image alt text', height: 340, width: 340, type: 'image', mimeType:'image/jpeg']]).save()
        new Profile([uuid:'aaag', type:'people', slug: 'mat111', jobTitle: 'Engineer', firstName: 'aaaa', lastName: 'mat111', headshot:[uuid:'bbbh', url:'//aaaa.jpg', alt: '7image alt text', height: 340, width: 340, type: 'image', mimeType:'image/jpeg']]).save()
        new Profile([uuid:'aaah', type:'people', slug: 'guy123', jobTitle: 'Engineer', firstName: 'Harry', lastName: 'Potter', headshot:[uuid:'bbbi', url:'//bbbbb.jpg', alt: '8image alt text', height: 340, width: 340, type: 'image', mimeType:'image/jpeg']]).save()
        new Profile([uuid:'aaai', type:'people', slug: 'ggggg', jobTitle: 'Engineer', firstName: 'Matttttt', lastName: 'Potter', headshot:[uuid:'bbbj', url:'//ccccc.jpg', alt: '9image alt text', height: 340, width: 340, type: 'image', mimeType:'image/jpeg']]).save()
        new Profile([uuid:'aaaj', type:'people', slug: '111111', jobTitle: 'Engineer', firstName: 'Mat', lastName: 'Potter', headshot:[uuid:'bbbk', url:'//ddddd.jpg', alt: '10image alt text', height: 340, width: 340, type: 'image', mimeType:'image/jpeg']]).save()
        new Profile([uuid:'aaak', type:'people', slug: '222222', jobTitle: 'Engineer', firstName: 'Harry', lastName: '1Matt', headshot:[uuid:'bbbl', url:'//eeeee.jpg', alt: '11image alt text', height: 340, width: 340, type: 'image', mimeType:'image/jpeg']]).save()
        new Profile([uuid:'aaal', type:'people', slug: 'bbbbbb', jobTitle: 'Engineer', firstName: 'Harry', lastName: 'Matt', headshot:[uuid:'bbbm', url:'//fffff.jpg', alt: '12image alt text', height: 34000000, width: 3400000, type: 'image', mimeType:'image/jpeg']]).save()
    }

    void "GET /api/profile"() {
        when:"The index action is executed"
        controller.index()

        then:"The response is correct"
        response.json.profiles.find{it.firstName == 'Harry'}
        response.json.profiles.find{it.firstName == 'Ronald'}
        response.json.count == 12
        println response.json
    }

    void "GET /api/profile with pagination"() {
        when:"The index action is executed"
        params.offset=1
        params.max=4
        controller.index()

        then:"The response is correct"
        response.json.profiles.find{it.firstName == 'Harry'}
        response.json.profiles.find{it.firstName == 'Ronald'} == null
        response.json.count == 12
        response.json.profiles.size() == 4
        println response.json
    }

    void "GET /api/profile/{id} with a invalid id"() {
        when:"Id too large"
        params.id = 1000
        controller.show()

        then:"A 404 error is returned"
        response.status == NOT_FOUND.value()
    }

    void "GET /api/profile/{id}"() {
        when:"A domain instance is passed to the show action"
        controller.show(1)

        then:"A model is populated containing the domain instance"
        response.status == OK.value()
        println response.json
    }

    void "GET /api/profile with search filter"() {
        when:"/api/profile?search=Engineer"
        params.search='Engineer'
        controller.index()

        then:"Expect 7 results"
        response.json.count == 7
        println response.json
    }
}
