package vcmsa.ci.game

object GameSessionProcessor {

    class GameSessionProcessor {
        private val sessionDates = mutableListOf<String>()
        private val minutesPlayed = mutableListOf<Int>()
        private val genres = mutableListOf<String>()
        private val ratings = mutableListOf<Float>()

        fun addSession(date: String, minutes: Int, genre: String, rating: Float) {
            sessionDates.add(date)
            minutesPlayed.add(minutes)
            genres.add(genre)
            ratings.add(rating)
        }

        fun getAverageMinutesPlayed(): Double {
            return if (minutesPlayed.isNotEmpty()) minutesPlayed.average() else 0.0
        }

        fun getHighestRatedGame(): String {
            return if (ratings.isNotEmpty()) {
                val maxIndex = ratings.indexOf(ratings.maxOrNull()!!)
                "${sessionDates[maxIndex]} - ${genres[maxIndex]} (Rating: ${ratings[maxIndex]})"
            } else "No data available"
        }

        fun displaySessions() {
            println("Game Sessions:")
            for (i in sessionDates.indices) {
                println("${sessionDates[i]} | ${minutesPlayed[i]} mins | ${genres[i]} | Rating: ${ratings[i]}")
            }
        }
    }

    // Example usage
    fun main() {
        val processor = GameSessionProcessor()
        processor.addSession("2025-06-03", 120, "RPG", 4.5f)
        processor.addSession("2025-06-04", 90, "Shooter", 4.0f)

        processor.displaySessions()
        println("Average Minutes Played: ${processor.getAverageMinutesPlayed()}")
        println("Highest Rated Game: ${processor.getHighestRatedGame()}")
    }
}