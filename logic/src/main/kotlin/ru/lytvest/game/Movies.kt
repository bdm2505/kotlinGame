package ru.lytvest.game

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

interface Movies : Circle {
    override var x: Float
    override var y: Float
    val speed: Float
    val way: Float




    fun move(world: World, delta: Float) {
        val dis = speed * delta
        val objs = world.allObjects(this, dis + r)
        if (objs.isEmpty()) {
            move(dis)
        }
    }

    private fun move(dis: Float) {
        x += dis * sin(way)
        y += dis * cos(way)
    }

    companion object {
        const val pi = PI.toFloat()

        const val LEFT = - pi / 2
        const val RIGHT = pi / 2
        const val DOWN = pi
        const val UP = 0f
    }
}