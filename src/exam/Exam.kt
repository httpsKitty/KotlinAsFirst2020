package exam

import java.lang.IllegalArgumentException

private fun parseScoreTable(text: String): Map<String, Int> {
    val scoreTable = mutableMapOf<String, Int>()
    val scoreTokens = text.split("; ")

    for (scoreToken in scoreTokens) {
        try {
            val teamsTokens = scoreToken.split(":")

            val team1Tokens = teamsTokens[0].split(" ")
            val team2Tokens = teamsTokens[1].split(" ")

            val team1Name = team1Tokens[0]
            if (scoreTable[team1Name] == null) scoreTable[team1Name] = 0
            val team1Score = team1Tokens[1].toInt()

            val team2Name = team2Tokens[1]
            if (scoreTable[team2Name] == null) scoreTable[team2Name] = 0
            val team2Score = team2Tokens[0].toInt()

            if (team1Score == team2Score) {
                scoreTable[team1Name] = scoreTable[team1Name]!! + 1
                scoreTable[team2Name] = scoreTable[team2Name]!! + 1
            } else if (team1Score > team2Score) {
                scoreTable[team1Name] = scoreTable[team1Name]!! + 3
            } else {
                scoreTable[team2Name] = scoreTable[team2Name]!! + 3
            }
        } catch (ex: Exception) {
            throw IllegalArgumentException(scoreToken)
        }
    }

    return scoreTable
}

fun sortTeamsByScore(text: String, teams: List<String>): List<String> {
    val scoreTable = parseScoreTable(text).toMutableMap()

    for (team in teams) {
        if (scoreTable[team] == null) {
            scoreTable[team] = 0
        }
    }

    return scoreTable.filter { teams.contains(it.key) }
        .toList().sortedByDescending { (key, value) -> value }
        .map { it.first }
}

