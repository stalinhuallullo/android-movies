package peruapps.movies.data.exception

class UnauthorizedException : Exception("Your email or password is wrong")

class RequestTimeoutException : Exception("The request took too long to respond, please try again")

class UncaughtErrorException : Exception("An unexpected error occurred, please try again")