package peruapps.movies.presentation.auth

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class AuthModel : BaseObservable() {
    var email: String = ""
        @Bindable get
        set(email) {
            field = email
            notifyPropertyChanged(BR.email)
        }
    var password: String = ""
        @Bindable get
        set(password) {
            field = password
            notifyPropertyChanged(BR.password)
        }
}