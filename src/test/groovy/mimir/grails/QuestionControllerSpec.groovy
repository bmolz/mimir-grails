package mimir.grails

import spock.lang.*
import static org.springframework.http.HttpStatus.*
import grails.validation.ValidationException
import grails.testing.web.controllers.ControllerUnitTest
import grails.testing.gorm.DomainUnitTest
import grails.plugin.springsecurity.SpringSecurityService

@Ignore
class QuestionControllerSpec extends Specification implements ControllerUnitTest<QuestionController>, DomainUnitTest<Question> {

    def setup() {

        def player0 = new User(username: 'player', password: 'player').save(flush:true)
        controller.springSecurityService = Mock(SpringSecurityService)
        controller.springSecurityService.currentUser >> User.findByUsername('player')
        controller.springSecurityService.isLoggedIn() >> true
        controller.gameService = Mock(GameService) // can't access service from unit test... need to use integration Test


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

    void "Get Question"() {
        when:
        controller.save()

        then:
        println response.json
    }

}
