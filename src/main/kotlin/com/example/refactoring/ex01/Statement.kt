package com.example.refactoring.ex01

import java.text.NumberFormat
import java.util.*

fun usd(aNumber: Double): String = NumberFormat.getCurrencyInstance(Locale.US).format(aNumber / 100)

fun statement(invoice: Invoice, plays: Map<String, Play>): String {
    return renderPlainText(createStatementData(invoice, plays))
}

fun renderPlainText(data: StatementData): String {
    var result = "청구 내역 (고객명: ${data.customer})\n"
    data.performances.forEach { performance ->
        result += " ${performance.play.name}: ${usd(performance.amount)} (${performance.audience}석)\n"
    }

    result += "총액: ${usd(data.totalAmount)}\n"
    result += "적립 포인트: ${data.totalVolumeCredits}점"

    return result
}

fun htmlStatement(invoice: Invoice, plays: Map<String, Play>): String {
    return renderHtml(createStatementData(invoice, plays))
}

fun renderHtml(data: StatementData): String {
    var result = """
        |<h1>청구 내역 (고객명: ${data.customer})</h1>
        |<table>
        |   <tr>
        |       <th>연극</th>
        |       <th>좌석수</th>
        |       <th>금액</th>
        |   </tr>
    """.trimMargin()

    data.performances.forEach { performance ->
        result += """
            |
            |   <tr>
            |       <td>${performance.play.name}</td>
            |       <td>(${performance.audience}석)</td>
            |       <td>${usd(performance.amount)}</td>
            |   </tr>
        """.trimMargin()
    }

    result += """
        |
        |</table>
        |<p>총액: <em>${usd(data.totalAmount)}</em></p>
        |<p>적립 포인트: <em>${data.totalVolumeCredits}</em>점</p>
    """.trimMargin()

    return result
}
