package ru.lytvest.game

import kotlin.random.Random


class SimpleRandomHeroController(val percent: Float = 0.01f) : HeroController {

    var speedPercent = Random.nextFloat()
    var wayHero = Random.nextFloat() * 2 * Circle.pi - Circle.pi

    fun nextComm() {
        speedPercent = Random.nextFloat()
        wayHero = Random.nextFloat() * 2 * Circle.pi - Circle.pi
    }


    override fun control(world: World, hero: Hero) {

        if (Random.nextFloat() < percent) {
            nextComm()
        }
        if (hero.isDie()) {
            return
        }

        hero.apply {
            speed = speedPercent * speedMax
            way = wayHero
        }
    }
}