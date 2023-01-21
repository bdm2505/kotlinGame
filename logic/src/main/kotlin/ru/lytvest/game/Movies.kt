package ru.lytvest.game

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

interface Movies : Circle {
    override var x: Float
    override var y: Float
    val speed: Float
    val way: Float




    fun move(world: World, delta: Float): Boolean {
        val dis = speed * delta
        val next = nextPosition(dis)

        if (!world.isConflictPosition(next, this)) {
            x = next.x
            y = next.y
            return true
        }
        return false
    }

    private fun nextPosition(dis: Float): Circle {
        return Circle.create(x + dis * sin(way), y + dis * cos(way), r)
    }

    companion object {
        const val pi = PI.toFloat()

        const val LEFT = - pi / 2
        const val RIGHT = pi / 2
        const val DOWN = pi
        const val UP = 0f


        fun Float.equalsAngle(angle: Float, pr: Float = pi / 4f): Boolean {
            return abs(angle - this) <= pr
        }

        fun isRight(angle: Float) = RIGHT.equalsAngle(angle)
        fun isLeft(angle: Float) = LEFT.equalsAngle(angle)
        fun isDown(angle: Float) = DOWN.equalsAngle(angle)
        fun isUp(angle: Float) = UP.equalsAngle(angle)
    }
}