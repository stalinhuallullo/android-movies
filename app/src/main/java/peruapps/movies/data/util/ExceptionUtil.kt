package peruapps.movies.data.util

import peruapps.movies.data.exception.RequestTimeoutException
import peruapps.movies.data.exception.UnauthorizedException
import peruapps.movies.data.exception.UncaughtErrorException
import java.lang.Exception

fun getExceptionByCode(status: Int): Exception {
    return when (status) {
        401 -> UnauthorizedException()
        408 -> RequestTimeoutException()
        else -> UncaughtErrorException()
    }
}