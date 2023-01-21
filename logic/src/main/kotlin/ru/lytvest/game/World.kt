package ru.lytvest.game

import kotlin.math.PI

class World {

    val all = mutableListOf<ID>()
    val pi = PI.toFloat()

    val heroes = mutableListOf<Hero>()

    val controllers = mutableMapOf<Long, HeroController>()
    val defaultController = SimpleRandomHeroController()

    fun allObjects(point: Point, distance: Float) : List<Circle> {
        return all.filter { it.distanceTo(point) <= distance && it != point }
    }

    fun isConflictPosition(circle: Circle, exclude: Point? = null): Boolean {
        return all.find { circle.distanceTo(it) < it.r + circle.r && it != exclude } != null
    }

    fun update(delta: Float) {

        for (hero in heroes) {
            if (!controllers.containsKey(hero.id)) {
                controllers[hero.id] = SimpleRandomHeroController()
            }
            controllers[hero.id]?.control(this, hero)
        }

        for (hero in heroes) {
            hero.move(this, delta)
        }
    }

    fun addHero(hero: Hero) {
        if(!isConflictPosition(hero)) {
            all += hero
            heroes += hero
        }
    }
}