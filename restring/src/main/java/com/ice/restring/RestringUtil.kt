package com.ice.restring

import java.util.Locale

object RestringUtil {
    val currentLanguage: String
        get() = Locale.getDefault().language

    const val DEFAULT_LANGUAGE = "__DEFAULT"
}
