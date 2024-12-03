package com.app.campusconnect.ui.authentication.models

import androidx.annotation.StringRes
import com.app.campusconnect.R

enum class AuthScreen (@StringRes val title: Int){
    Welcome(title = R.string.app_name),
    Registration(title = R.string.registration),
    EmailCode(title = R.string.verification_code),
    NewPassword(title = R.string.new_password),
    EnterProfile(title = R.string.access),
}
