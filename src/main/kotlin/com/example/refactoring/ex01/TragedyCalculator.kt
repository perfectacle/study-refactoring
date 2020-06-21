package com.example.refactoring.ex01

import kotlin.math.max

class TragedyCalculator(
    aPerformance: Invoice.Performance,
    override val play: Play
) : PerformanceCalculator {
    override val amount = 40000.0 + 1000 * (aPerformance.audience - 30)
    override val volumeCredits = max(aPerformance.audience - 30, 0).toDouble()
}