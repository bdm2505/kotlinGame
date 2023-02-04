package ru.lytvest.game

import kotlin.math.PI
import kotlin.math.abs

interface Circle : Point {
    val r: Float

    companion object {
        fun create(_x: Float, _y: Float, _r: Float): Circle {
            return C(_x, _y, _r)
        }


        const val pi = PI.toFloat()

        const val LEFT = -pi / 2
        const val RIGHT = pi / 2
        const val DOWN = pi
        const val UP = 0f
        const val PI_NA_8 = pi / 8
        const val PI_NA_6 = pi / 6
        const val PI_NA_4 = pi / 4


        fun Float.equalsAngle(angle: Float, pr: Float = pi / 4f): Boolean {
            return abs(angle - this) <= pr
        }

        fun isRight(angle: Float) = RIGHT.equalsAngle(angle)
        fun isLeft(angle: Float) = LEFT.equalsAngle(angle)
        fun isDown(angle: Float) = DOWN.equalsAngle(angle)
        fun isUp(angle: Float) = UP.equalsAngle(angle)


        data class C(override val x: Float, override val y: Float, override val r: Float) : Circle
    }
}


