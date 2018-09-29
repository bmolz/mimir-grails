package mimir.grails

import grails.gorm.transactions.Transactional
import groovy.json.JsonSlurper

@Transactional
class ImportService {

    def importProfiles(p) {
        int count
        def jsonSlurper = new JsonSlurper()
        def profileList = jsonSlurper.parseText(p)
        if(profileList instanceof List) {
            profileList.each{ profile ->
                if(profile instanceof Map) {
                    profile.uuid = profile.id
                    profile.remove('id')
                    profile.headshot.uuid  = profile.headshot.id
                    profile.headshot.remove('id')
                    Profile n = new Profile(profile)
                    n.save()
                    count ++
                }
            }
        }
        return count
    }
}
