package ru.lytvest.game

abstract class ID : Circle{

    val id: Long = nextId()

    companion object {

        var allIds = 0L
        fun nextId(): Long = allIds++
    }
}