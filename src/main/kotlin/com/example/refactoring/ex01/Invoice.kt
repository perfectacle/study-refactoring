package com.example.refactoring.ex01

class Invoice(
    val customer: String,
    val performances: List<Performance>
) {
    class Performance(
        val playID: String,
        val audience: Int
    )
}
