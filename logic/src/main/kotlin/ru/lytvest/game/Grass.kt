package ru.lytvest.game

import kotlin.random.Random

class Grass(override var x: Float, override var y: Float, override var r: Float) : Obj(), Circle {
    val number: Int = Random.nextInt(6)
}