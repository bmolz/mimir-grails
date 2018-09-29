package mimir.grails

class Picture {

    String uuid
    String type
    String mimeType
    String url
    String alt
    Integer height
    Integer width

    static constraints = {
        uuid blank: true, nullable: true
        type blank: true, nullable: true
        mimeType blank: true, nullable: true
        url blank: true, nullable: true
        alt blank: true, nullable: true
        height nullable: true
        width nullable: true
    }

    static belongsTo = [
        profile:Profile
    ]

    static mapping = {
        url sqlType: 'text'
        type defaultValue: "''"
        mimeType defaultValue: "''"
        url defaultValue: "''"
        alt defaultValue: "''"
        height defaultValue: '0'
        width defaultValue: '0'
    }
}
