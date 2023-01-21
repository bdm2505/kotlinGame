package ru.lytvest.game

import kotlin.random.Random


class SimpleRandomHeroController(val percent: Float = 0.1f) : HeroController {

    var speedPercent = Random.nextFloat()
    var wayHero = Random.nextFloat() * 2 * Movies.pi - Movies.pi

    fun nextComm() {
        speedPercent = Random.nextFloat()
        wayHero = Random.nextFloat() * 2 * Movies.pi - Movies.pi
    }


    override fun control(world: World, hero: Hero) {

        if (Random.nextFloat() < percent) {
            nextComm()
        }

        hero.apply {
            speed = speedPercent * maxSpeed
            way = wayHero
        }
    }
}