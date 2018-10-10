package mimir.grails

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory
import spock.lang.Ignore

@Ignore
@Integration
@Rollback
class SocialLinkServiceSpec extends Specification {

    SocialLinkService socialLinkService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new SocialLink(...).save(flush: true, failOnError: true)
        //new SocialLink(...).save(flush: true, failOnError: true)
        //SocialLink socialLink = new SocialLink(...).save(flush: true, failOnError: true)
        //new SocialLink(...).save(flush: true, failOnError: true)
        //new SocialLink(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //socialLink.id
    }

    void "test get"() {
        setupData()

        expect:
        socialLinkService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<SocialLink> socialLinkList = socialLinkService.list(max: 2, offset: 2)

        then:
        socialLinkList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        socialLinkService.count() == 5
    }

    void "test delete"() {
        Long socialLinkId = setupData()

        expect:
        socialLinkService.count() == 5

        when:
        socialLinkService.delete(socialLinkId)
        sessionFactory.currentSession.flush()

        then:
        socialLinkService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        SocialLink socialLink = new SocialLink()
        socialLinkService.save(socialLink)

        then:
        socialLink.id != null
    }
}
