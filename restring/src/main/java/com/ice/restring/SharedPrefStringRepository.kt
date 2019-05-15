package com.ice.restring

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import org.json.JSONObject

import java.util.LinkedHashMap

/**
 * A StringRepository which saves/loads the strings in Shared Preferences.
 * it also keeps the strings in memory by using MemoryStringRepository internally for faster access.
 *
 *
 * it's not ThreadSafe.
 */
class SharedPrefStringRepository(context: Context) : StringRepository {

    private val sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    private val memoryStringRepository = MemoryStringRepository()

    init {
        loadStrings()
    }

    override fun setStrings(language: String, strings: MutableMap<String, String?>) {
        memoryStringRepository.setStrings(language, strings)
        saveStrings(language, strings)
    }

    override fun setString(language: String, key: String, value: String) {
        memoryStringRepository.setString(language, key, value)

        val keyValues = memoryStringRepository.getStrings(language)
        keyValues[key] = value
        saveStrings(language, keyValues)
    }

    override fun getString(language: String, key: String): String? =
            memoryStringRepository.getString(language, key)

    override fun getStrings(language: String): MutableMap<String, String?> =
            memoryStringRepository.getStrings(language)

    private fun loadStrings() {
        val strings = sharedPreferences.all
        for (entry in strings.entries) {
            if (entry.value !is String) {
                continue
            }

            val value = entry.value as String
            val keyValues = deserializeKeyValues(value)
            val language = entry.key
            memoryStringRepository.setStrings(language, keyValues)
        }
    }

    private fun saveStrings(language: String, strings: MutableMap<String, String?>) {
        val content = serializeKeyValues(strings)
        sharedPreferences.edit()
                .putString(language, content)
                .apply()
    }

    private fun deserializeKeyValues(content: String): MutableMap<String, String?> = Gson().fromJson(content)

    private fun serializeKeyValues(keyValues: MutableMap<String, String?>) = Gson().toJson(keyValues)

    companion object {
        private const val SHARED_PREF_NAME = "Restrings"
    }
}
