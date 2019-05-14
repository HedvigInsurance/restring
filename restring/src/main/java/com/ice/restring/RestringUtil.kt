package com.ice.restring

import java.util.Locale

object RestringUtil {
    val currentLanguage: String
        get() = Locale.getDefault().getLanguage()
}
