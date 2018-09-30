package mimir.grails

import grails.gorm.transactions.Transactional
import groovy.time.TimeCategory

import java.util.concurrent.ThreadLocalRandom

@Transactional
class GameService {

    def generateQuestion(Question q) {
        if(q?.choices) q.choices.clear()
        def rAns = ThreadLocalRandom.current().nextInt(1, Profile.count+1)
        q.answer = Profile.get(rAns)
        def rChoices = [rAns] as Set
        while (rChoices.size() < 6) {
            rChoices << ThreadLocalRandom.current().nextInt(1, Profile.count+1)
        }
        Profile.getAll(rChoices).each{
            q.addToChoices(it)
        }
        q.save()
    }

    def win(Question q, User u){
        u.question=null
        u.save()
        q.delete()

        Statistics stats = getStatistics(u)
        stats.totalTimeSeconds += TimeCategory.minus(new Date(), q.dateCreated).seconds
        stats.correct ++
        stats.total ++
        stats.fractionCorrect = stats.correct / stats.total
        stats.averageTimeSeconds = stats.totalTimeSeconds / stats.total
        stats.save()
    }

    def lose(Question q, User u){
        q.attempts ++
        q.save()
        Statistics stats = getStatistics(u)
        stats.wrong ++
        stats.total ++
        stats.fractionCorrect = stats.correct / stats.total
        stats.save()
    }

    def getStatistics(User u){
        def stats = u?.statistics
        if(!stats) {
            stats = new Statistics(
                correct: 0,
                wrong: 0,
                total: 0,
                totalTimeSeconds: 0,
                averageTimeSeconds: 0,
                fractionCorrect: 0,
                user: u
            ).save()
        }
        return stats
    }
}
