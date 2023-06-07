package edu.fpm.reddittopposts.utils

import edu.fpm.reddittopposts.utils.Constants.ONE_DAY_IN_SEC
import edu.fpm.reddittopposts.utils.Constants.ONE_HOUR_IN_SEC
import edu.fpm.reddittopposts.utils.Constants.ONE_MINUTE_IN_SEC
import java.util.concurrent.TimeUnit

fun formatCreation(created: Long): String {
    val currentTimeMillis = System.currentTimeMillis()
    val elapsedTimeMillis = currentTimeMillis - (created * 1000)
    val elapsedTimeSeconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTimeMillis)

    return when {
        elapsedTimeSeconds < ONE_MINUTE_IN_SEC -> "$elapsedTimeSeconds seconds ago"
        elapsedTimeSeconds < ONE_HOUR_IN_SEC -> {
            val elapsedMinutes = TimeUnit.SECONDS.toMinutes(elapsedTimeSeconds)
            "$elapsedMinutes minutes ago"
        }

        elapsedTimeSeconds < ONE_DAY_IN_SEC -> {
            val elapsedHours = TimeUnit.SECONDS.toHours(elapsedTimeSeconds)
            "$elapsedHours hours ago"
        }

        else -> {
            val elapsedDays = TimeUnit.SECONDS.toDays(elapsedTimeSeconds)
            "$elapsedDays days ago"
        }
    }
}