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

fun main() {
    val world = World()
    val hero = Hero()
    world.add(hero)

    val tree = Tree().apply {
        x = 2f
        r = 0.7f
    }
    world.add(tree)

    hero.way = Circle.RIGHT

    hero.startAttack(world)
    hero.endAttack(world)

    println("$hero $tree")
}