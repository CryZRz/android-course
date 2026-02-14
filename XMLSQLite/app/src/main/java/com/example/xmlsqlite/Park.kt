package com.example.xmlsqlite

class Park(
    var id: Long = 0,
    val name: String,
    var isFav: Boolean
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Park

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}