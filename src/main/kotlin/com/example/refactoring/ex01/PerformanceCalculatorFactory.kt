package com.example.refactoring.ex01

class PerformanceCalculatorFactory {
    fun create(
        aPerformance: Invoice.Performance,
        play: Play
    ) = when (play.type) {
        Play.Type.TRAGEDY -> TragedyCalculator(aPerformance, play)
        Play.Type.COMEDY -> ComedyCalculator(aPerformance, play)
    }
}