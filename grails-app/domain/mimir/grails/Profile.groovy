package mimir.grails

class Profile {
    String uuid
    String type
    String slug
    String jobTitle
    String firstName
    String lastName
    String bio

    static hasMany = [
        socialLinks: SocialLink
    ]

    static hasOne = [
        headshot: Picture
    ]

    static constraints = {
        uuid blank: true, nullable: true
        type blank: true, nullable: true
        slug blank: true, nullable: true
        jobTitle blank: true, nullable: true
        firstName blank: true, nullable: true
        lastName blank: true, nullable: true
        bio blank: true, nullable: true
        headshot nullable: true
    }
}
