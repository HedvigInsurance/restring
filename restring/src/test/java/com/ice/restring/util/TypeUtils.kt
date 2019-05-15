package com.ice.restring.util

inline fun <reified T: Any> javaClass(): Class<T> = T::class.java