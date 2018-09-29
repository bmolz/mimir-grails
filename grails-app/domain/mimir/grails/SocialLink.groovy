package mimir.grails

class SocialLink {
    String type
    String callToAction
    String url

    static belongsTo = [
        profile:Profile
    ]

    static constraints = {
        type blank: true, nullable: true
        callToAction blank: true, nullable: true
        url blank: true, nullable: true
    }

    static mapping = {
        callToAction sqlType: 'text'
        url sqlType: 'text'
    }
}
