package mimir.grails

class Profile {
    String uuid
    String type
    String slug
    String jobTitle
    String firstName
    String lastName
    String bio
    Picture headshot

    static hasMany = [
        socialLinks: SocialLink
    ]

    static constraints = {
        jobTitle blank: true, nullable: true
        firstName blank: true, nullable: true
        lastName blank: true, nullable: true
        bio blank: true, nullable: true
        headshot nullable: true
    }

    static mapping = {
        type defaultValue: "'people'"
    }
}
