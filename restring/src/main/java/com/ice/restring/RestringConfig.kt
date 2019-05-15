package com.ice.restring

/**
 * Contains configuration properties for initializing Restring.
 */
class RestringConfig private constructor() {

    var isPersist: Boolean = false
        private set
    var stringsLoader: Restring.StringsLoader? = null
        private set

    class Builder {
        private var persist: Boolean = false
        private var stringsLoader: Restring.StringsLoader? = null

        fun persist(persist: Boolean) = this.also {
            this.persist = persist
        }

        fun stringsLoader(loader: Restring.StringsLoader) = this.also {
            stringsLoader = loader
        }

        fun build(): RestringConfig {
            val config = RestringConfig()
            config.isPersist = persist
            config.stringsLoader = stringsLoader
            return config
        }
    }

    companion object {
        val default: RestringConfig
            get() = Builder()
                    .persist(true)
                    .build()
    }
}