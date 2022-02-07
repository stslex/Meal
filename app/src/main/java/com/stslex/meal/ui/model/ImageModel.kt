package com.stslex.meal.ui.model

interface ImageModel {

    fun url(): String
    fun description(): String

    data class Base(
        private val id: String,
        private val url: String,
        private val description: String
    ) : ImageModel {

        override fun url(): String = url
        override fun description(): String = description
    }
}