package com.example.refactoring.ex01

import kotlin.math.floor
import kotlin.math.max

class ComedyCalculator(
    aPerformance: Invoice.Performance,
    override val play: Play
) : PerformanceCalculator {
    override val amount: Double
    override val volumeCredits = max(aPerformance.audience - 30, 0) + floor(aPerformance.audience / 5.0)

    init {
        var amount = 30000.0
        if (aPerformance.audience > 20) amount += 10000 + 500 * (aPerformance.audience - 20)
        this.amount = amount + 300 * aPerformance.audience
    }
}