package com.example.refactoring.ex01

data class StatementData(
    val customer: String,
    val performances: List<EnrichPerformance>,
    val totalAmount: Double,
    val totalVolumeCredits: Double
) {
    data class EnrichPerformance(
        val playID: String,
        val audience: Int,
        val play: Play,
        val amount: Double,
        val volumeCredits: Double
    )
}
