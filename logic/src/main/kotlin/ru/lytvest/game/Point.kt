package ru.lytvest.game

import kotlin.math.sqrt

interface Point {
    val x: Float
    val y: Float

    fun distanceTo(point: Point): Float {
        return sqrt((x - point.x) * (x - point.x) + (y - point.y) * (y - point.y))
    }
}