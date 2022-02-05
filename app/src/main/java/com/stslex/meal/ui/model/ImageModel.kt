package com.stslex.meal.ui.model

interface ImageModel {

    fun url(): String

    data class Base(
        private val id: String,
        private val url: String
    ) : ImageModel {

        override fun url(): String = url
    }
}