package com.example.refactoring.ex01

import kotlin.math.floor
import kotlin.math.max

class StatementData(
    val customer: String,
    val performances: List<EnrichPerformance>,
    val totalAmount: Double,
    val totalVolumeCredits: Double
) {

    class EnrichPerformance(
        val playID: String,
        val audience: Int,
        val play: Play,
        val amount: Double,
        val volumeCredits: Double
    )
}

fun createStatementData(invoice: Invoice, plays: Map<String, Play>): StatementData {
    fun playFor(aPerformance: Invoice.Performance) = plays[aPerformance.playID] ?: throw RuntimeException("can not found play")
    fun amountFor(aPerformance: Invoice.Performance): Double {
        var result = 0.0

        when (playFor(aPerformance).type) {
            Play.Type.TRAGEDY -> {
                result = 40000.0
                if (aPerformance.audience > 30) result += 1000 * (aPerformance.audience - 30)
            }
            Play.Type.COMEDY -> {
                result = 30000.0
                if (aPerformance.audience > 20) result += 10000 + 500 * (aPerformance.audience - 20)
                result += 300 * aPerformance.audience
            }
        }

        return result
    }
    fun totalAmount() = invoice.performances.sumByDouble { amountFor(it) }
    fun volumeCreditsFor(aPerformance: Invoice.Performance): Double {
        var result = 0.0

        result += max(aPerformance.audience - 30, 0)
        if (playFor(aPerformance).type == Play.Type.COMEDY) result += floor(
            aPerformance.audience / 5.0
        )

        return result
    }
    fun totalVolumeCredits() = invoice.performances.sumByDouble { volumeCreditsFor(it) }
    fun enrichPerformance(aPerformance: Invoice.Performance) =
        StatementData.EnrichPerformance(
            playID = aPerformance.playID,
            audience = aPerformance.audience,
            play = playFor(aPerformance),
            amount = amountFor(aPerformance),
            volumeCredits = volumeCreditsFor(aPerformance)
        )

    return StatementData(
        customer = invoice.customer,
        performances = invoice.performances.map { enrichPerformance(it) },
        totalAmount = totalAmount(),
        totalVolumeCredits = totalVolumeCredits()
    )
}
