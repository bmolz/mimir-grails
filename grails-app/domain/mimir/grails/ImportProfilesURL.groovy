package mimir.grails

class ImportProfilesURL {

    String url
    Integer count

    static mapping = {
        count defaultValue: '0'
    }
}
