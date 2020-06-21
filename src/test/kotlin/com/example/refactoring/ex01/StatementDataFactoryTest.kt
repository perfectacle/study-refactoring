package com.example.refactoring.ex01

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class StatementDataFactoryTest {
    @Test
    internal fun `test create statement data`() {
        // Given
        val expected = statementData()

        // When
        val actual = StatementDataFactory(PerformanceCalculatorFactory()).create(invoice(), plays())

        // Then
        actual shouldBe expected
    }
}

fun statementData() = StatementData(
    customer = "BigCo",
    performances = listOf(
        StatementData.EnrichPerformance(
            playID = "hamlet",
            audience = 55,
            play = Play(name = "Hamlet", type = Play.Type.TRAGEDY),
            amount = 65000.0,
            volumeCredits = 25.0
        ),
        StatementData.EnrichPerformance(
            playID = "as-like",
            audience = 35,
            play = Play(name = "As You Like It", type = Play.Type.COMEDY),
            amount = 58000.0,
            volumeCredits = 12.0
        ),
        StatementData.EnrichPerformance(
            playID = "othello",
            audience = 40,
            play = Play(name = "Othello", type = Play.Type.TRAGEDY),
            amount = 50000.0,
            volumeCredits = 10.0
        )
    ),
    totalAmount = 173000.0,
    totalVolumeCredits = 47.0
)