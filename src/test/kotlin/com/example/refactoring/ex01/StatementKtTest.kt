package com.example.refactoring.ex01

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class StatementKtTest {
    @Test
    internal fun `test statement`() {
        val expected = """
            청구 내역 (고객명: BigCo)
             Hamlet: ${'$'}650.00 (55석)
             As You Like It: ${'$'}580.00 (35석)
             Othello: ${'$'}500.00 (40석)
            총액: ${'$'}1,730.00
            적립 포인트: 47.0점
        """.trimIndent()

        val actual = statement(invoices()[0], plays())

        actual shouldBe expected
    }

    @Test
    internal fun `test html statement`() {
        val expected = """
            <h1>청구 내역 (고객명: BigCo)</h1>
            <table>
               <tr>
                   <th>연극</th>
                   <th>좌석수</th>
                   <th>금액</th>
               </tr>
               <tr>
                   <td>Hamlet</td>
                   <td>(55석)</td>
                   <td>${'$'}650.00</td>
               </tr>
               <tr>
                   <td>As You Like It</td>
                   <td>(35석)</td>
                   <td>${'$'}580.00</td>
               </tr>
               <tr>
                   <td>Othello</td>
                   <td>(40석)</td>
                   <td>${'$'}500.00</td>
               </tr>
            </table>
            <p>총액: <em>${'$'}1,730.00</em></p>
            <p>적립 포인트: <em>47.0</em>점</p>
        """.trimIndent()

        val actual = htmlStatement(invoices()[0], plays())

        println(actual)

        actual shouldBe expected
    }
}

private fun invoices(): List<Invoice> = listOf(
    Invoice(
        customer = "BigCo",
        performances = listOf(
            Invoice.Performance(
                playID = "hamlet",
                audience = 55
            ),
            Invoice.Performance(
                playID = "as-like",
                audience = 35
            ),
            Invoice.Performance(
                playID = "othello",
                audience = 40
            )
        )
    )
)

private fun plays() = mapOf(
    "hamlet" to Play("Hamlet", Play.Type.TRAGEDY),
    "as-like" to Play("As You Like It", Play.Type.COMEDY),
    "othello" to Play("Othello", Play.Type.TRAGEDY)
)