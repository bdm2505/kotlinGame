package ru.lytvest.game

interface ID : Circle {

    val id: Int
}

open class Obj(override val id: Int = nextId()) : ID {

    override var x: Float = 0f
    override var y: Float = 0f
    override var r: Float = 1f
    override fun hashCode(): Int {
        return id;
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Obj

        if (id != other.id) return false

        return true
    }


    companion object {
        var allIds = 0;

        fun nextId(): Int = allIds++
    }
}

fun main() {
    println(Obj().hashCode())
}