package com.ice.restring

import java.util.LinkedHashMap

/**
 * A StringRepository which keeps the strings ONLY in memory.
 *
 *
 * it's not ThreadSafe.
 */
class MemoryStringRepository : StringRepository {

    private val strings = LinkedHashMap<String, MutableMap<String, String?>>()

    override fun setStrings(language: String, newStrings: MutableMap<String, String?>) {
        strings.put(language, newStrings)
    }

    override fun setString(language: String, key: String, value: String) {
        if (!strings.containsKey(language)) {
            strings[language] =  LinkedHashMap()
        }
        strings[language]?.put(key, value)
    }

    override fun getString(language: String, key: String) = strings[language]?.get(key)
    override fun getStrings(language: String) = strings[language] ?: LinkedHashMap()
}