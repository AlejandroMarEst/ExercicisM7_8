package cat.itb.m78.exercices.bd

import app.cash.sqldelight.db.SqlDriver
import cat.itb.m78.exercices.db.Database

expect fun createDriver(): SqlDriver

fun createDatabase(): Database {
    val driver = createDriver()
    return Database(driver)
}

val database by lazy { createDatabase() }

