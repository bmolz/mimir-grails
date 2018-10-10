package mimir.grails

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory
import spock.lang.Ignore

@Ignore
@Integration
@Rollback
class ImportProfilesURLServiceSpec extends Specification {

    ImportProfilesURLService importProfilesURLService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ImportProfilesURL(...).save(flush: true, failOnError: true)
        //new ImportProfilesURL(...).save(flush: true, failOnError: true)
        //ImportProfilesURL importProfilesURL = new ImportProfilesURL(...).save(flush: true, failOnError: true)
        //new ImportProfilesURL(...).save(flush: true, failOnError: true)
        //new ImportProfilesURL(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //importProfilesURL.id
    }

    void "test get"() {
        setupData()

        expect:
        importProfilesURLService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ImportProfilesURL> importProfilesURLList = importProfilesURLService.list(max: 2, offset: 2)

        then:
        importProfilesURLList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        importProfilesURLService.count() == 5
    }

    void "test delete"() {
        Long importProfilesURLId = setupData()

        expect:
        importProfilesURLService.count() == 5

        when:
        importProfilesURLService.delete(importProfilesURLId)
        sessionFactory.currentSession.flush()

        then:
        importProfilesURLService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ImportProfilesURL importProfilesURL = new ImportProfilesURL()
        importProfilesURLService.save(importProfilesURL)

        then:
        importProfilesURL.id != null
    }
}
