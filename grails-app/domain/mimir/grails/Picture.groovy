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
        alt blank: true, nullable: true
        url url: true
    }

    static belongsTo = [
        profile:Profile
    ]

    static mapping = {
        type defaultValue: "'image'"
        url sqlType: 'text'
    }

}
