package mimir.grails

class SocialLink {
    String type
    String callToAction
    String url

    static constraints = {
        callToAction blank: true, nullable: true
        url url: true
    }

    static mapping = {
        callToAction sqlType: 'text'
        url sqlType: 'text'
    }
}
