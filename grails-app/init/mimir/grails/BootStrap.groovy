package mimir.grails

class BootStrap {

    def importService

    def init = { servletContext ->

        if(!ImportProfilesURL.get(1)) {
            println 'Importing Profiles...'
            new ImportProfilesURL(url: "https://tinyurl.com/ybss7rrb", count: importService.importProfiles("https://tinyurl.com/ybss7rrb".toURL().text)).save()
        }

        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save()
        def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save()

        def adminUser = User.findByUsername('user') ?: new User(username: 'admin', password: 'admin').save()
        def player0 = User.findByUsername('player') ?: new User(username: 'player', password: 'player').save()
        def player1 = User.findByUsername('Batman') ?: new User(username: 'Batman', password: 'Batman').save()
        def player2 = User.findByUsername('Megaman') ?: new User(username: 'Megaman', password: 'Megaman').save()
        def player3 = User.findByUsername('Zelda') ?: new User(username: 'Zelda', password: 'Zelda').save()
        def player4 = User.findByUsername('Atreus') ?: new User(username: 'Atreus', password: 'Atreus').save()
        def player5 = User.findByUsername('Loki') ?: new User(username: 'Loki', password: 'Loki').save()
        def player6 = User.findByUsername('Tron') ?: new User(username: 'Tron', password: 'Tron').save()

        UserRole.create adminUser, adminRole
        UserRole.create player0, userRole
        UserRole.create player1, userRole
        UserRole.create player2, userRole
        UserRole.create player3, userRole
        UserRole.create player4, userRole
        UserRole.create player5, userRole
        UserRole.create player6, userRole


        player0.statistics = new Statistics(
            correct: 0,
            wrong: 0,
            total: 0,
            totalTimeSeconds: 0,
            averageTimeSeconds: 0,
            fractionCorrect: 0,
            user: player0
        ).save()
        player1.statistics = new Statistics(
            correct: 10,
            wrong: 10,
            total: 20,
            totalTimeSeconds: 0,
            averageTimeSeconds: 0,
            fractionCorrect: 0,
            user: player1
        ).save()
        player2.statistics = new Statistics(
            correct: 100,
            wrong: 10,
            total: 20,
            totalTimeSeconds: 0,
            averageTimeSeconds: 0,
            fractionCorrect: 0,
            user: player2
        ).save()
        player3.statistics = new Statistics(
            correct: 10,
            wrong: 100,
            total: 20,
            totalTimeSeconds: 0,
            averageTimeSeconds: 0,
            fractionCorrect: 0,
            user: player3
        ).save()
        player4.statistics = new Statistics(
            correct: 99,
            wrong: 10,
            total: 20,
            totalTimeSeconds: 0,
            averageTimeSeconds: 0,
            fractionCorrect: 0,
            user: player4
        ).save()
        player5.statistics = new Statistics(
            correct: 1,
            wrong: 1,
            total: 20,
            totalTimeSeconds: 0,
            averageTimeSeconds: 0,
            fractionCorrect: 0,
            user: player5
        ).save()
        player6.statistics = new Statistics(
            correct: 0,
            wrong: 0,
            total: -1,
            totalTimeSeconds: 0,
            averageTimeSeconds: 0,
            fractionCorrect: 0,
            user: player6
        ).save()


    }
    def destroy = {
    }
}
