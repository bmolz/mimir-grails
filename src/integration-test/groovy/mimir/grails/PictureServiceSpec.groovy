package mimir.grails

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory
import spock.lang.Ignore

@Ignore
@Integration
@Rollback
class PictureServiceSpec extends Specification {

    PictureService pictureService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Picture(...).save(flush: true, failOnError: true)
        //new Picture(...).save(flush: true, failOnError: true)
        //Picture picture = new Picture(...).save(flush: true, failOnError: true)
        //new Picture(...).save(flush: true, failOnError: true)
        //new Picture(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //picture.id
    }

    void "test get"() {
        setupData()

        expect:
        pictureService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Picture> pictureList = pictureService.list(max: 2, offset: 2)

        then:
        pictureList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        pictureService.count() == 5
    }

    void "test delete"() {
        Long pictureId = setupData()

        expect:
        pictureService.count() == 5

        when:
        pictureService.delete(pictureId)
        sessionFactory.currentSession.flush()

        then:
        pictureService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Picture picture = new Picture()
        pictureService.save(picture)

        then:
        picture.id != null
    }
}
