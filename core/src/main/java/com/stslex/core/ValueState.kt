package com.stslex.core

sealed class ValueState<out T> {

    abstract fun <U> map(mapper: Mapper.ToUI<in T, U>): U

    data class Success<T>(val data: T) : ValueState<T>() {
        override fun <U> map(mapper: Mapper.ToUI<in T, U>): U = mapper.map(data)
    }

    data class Failure(val exception: Exception) : ValueState<Nothing>() {
        override fun <U> map(mapper: Mapper.ToUI<in Nothing, U>): U = mapper.map(exception)
    }

    object Loading : ValueState<Nothing>() {
        override fun <U> map(mapper: Mapper.ToUI<in Nothing, U>): U = mapper.map()
    }
}