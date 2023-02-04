package ru.lytvest.game

class Tree : Obj(), WithHp, Circle {

    override var hp: Float = 10f
    override var hpMax: Float = 10f
    override var armor: Float = 0f

    companion object {
        fun create(x: Float, y: Float, r: Float): Tree {
            return Tree().let {
                it.x = x
                it.y = y
                it.r = r
                it
            }
        }
    }

    override fun toString(): String {
        return "Tree(x=$x, y=$y, r=$r, hp=$hp)"
    }


}