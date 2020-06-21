package com.example.refactoring.ex01

class StatementDataFactory(
    private val calculatorFactory: PerformanceCalculatorFactory
) {
    fun create(invoice: Invoice, plays: Map<String, Play>): StatementData {
        fun playFor(aPerformance: Invoice.Performance) = plays[aPerformance.playID] ?: throw RuntimeException("can not found play")
        fun totalAmount() = invoice.performances.sumByDouble { calculatorFactory.create(it, playFor(it)).amount }
        fun totalVolumeCredits() = invoice.performances.sumByDouble { calculatorFactory.create(it, playFor(it)).volumeCredits }
        fun enrichPerformance(aPerformance: Invoice.Performance): StatementData.EnrichPerformance {
            val calculator = calculatorFactory.create(aPerformance, playFor(aPerformance))
            return StatementData.EnrichPerformance(
                playID = aPerformance.playID,
                audience = aPerformance.audience,
                play = calculator.play,
                amount = calculator.amount,
                volumeCredits = calculator.volumeCredits
            )
        }

        return StatementData(
            customer = invoice.customer,
            performances = invoice.performances.map { enrichPerformance(it) },
            totalAmount = totalAmount(),
            totalVolumeCredits = totalVolumeCredits()
        )
    }
}