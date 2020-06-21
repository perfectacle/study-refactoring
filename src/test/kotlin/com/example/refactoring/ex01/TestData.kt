package com.example.refactoring.ex01

fun invoice() = Invoice(
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

fun plays() = mapOf(
    "hamlet" to Play(
        "Hamlet",
        Play.Type.TRAGEDY
    ),
    "as-like" to Play(
        "As You Like It",
        Play.Type.COMEDY
    ),
    "othello" to Play(
        "Othello",
        Play.Type.TRAGEDY
    )
)