package com.example.refactoring.ex01

data class Play(
    val name: String,
    val type: Type
) {
    enum class Type {
        COMEDY,
        TRAGEDY
    }
}