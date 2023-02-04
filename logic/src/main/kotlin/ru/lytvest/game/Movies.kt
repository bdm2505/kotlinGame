package ru.lytvest.game

import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

interface Movies : Circle {
    override var x: Float
    override var y: Float
    var speed: Float
    var way: Float
    var speedMax: Float


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

    fun nextPosition(dis: Float, targetWay: Float = way): Circle {
        return Circle.create(x + dis * sin(targetWay), y + dis * cos(targetWay), r)
    }

    fun rayCollisionWithCircle(targetWay: Float, dis: Float, start: Point, end: Circle): Boolean {
        val d = start.distanceTo(end)

        if (d - end.r > dis)
            return false

        val next = nextPosition(min(d, dis), targetWay)

        return next.distanceTo(end) <= end.r
    }


}

fun main() {
    val world = World()
    val hero = Hero()
    hero.speed = 5f
    hero.way = Circle.LEFT
    world.add(hero)

    world.update(0.1f)
    world.update(0.1f)
    world.update(0.1f)
    world.update(0.1f)
}