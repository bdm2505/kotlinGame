package ru.lytvest.game

interface Circle : Point {
    val r: Float

    companion object {
        fun create(_x: Float, _y: Float, _r: Float): Circle {
            return object : Circle {
                override val r: Float
                    get() = _r
                override val x: Float
                    get() = _x
                override val y: Float
                    get() = _y
            }
        }
    }
}


