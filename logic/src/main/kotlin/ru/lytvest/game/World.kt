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

    fun update(delta: Float) {

        for (hero in heroes) {
            (controllers[hero.id] ?: defaultController).control(this, hero)
        }

        for (hero in heroes) {
            hero.move(this, delta)
        }
    }

    fun addHero(hero: Hero) {
        all += hero
        heroes += hero
    }
}