package mimir.grails

import grails.gorm.services.Service

@Service(ImportProfilesURL)
interface ImportProfilesURLService {

    ImportProfilesURL get(Serializable id)

    List<ImportProfilesURL> list(Map args)

    Long count()

    void delete(Serializable id)

    ImportProfilesURL save(ImportProfilesURL importProfilesURL)

}