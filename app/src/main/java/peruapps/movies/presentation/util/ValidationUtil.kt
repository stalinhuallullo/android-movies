package peruapps.movies.presentation.util

import android.util.Patterns

fun CharSequence?.isNotValidEmail() =
    isNullOrEmpty() || !Patterns.EMAIL_ADDRESS.matcher(this).matches()