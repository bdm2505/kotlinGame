package ru.lytvest.world

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import ru.lytvest.game.Hero
import ru.lytvest.game.HeroController
import ru.lytvest.game.Movies
import ru.lytvest.game.World

class GameHeroController : HeroController {

    var wayHero = Movies.RIGHT
    var speedPercent = 0f

    override fun control(world: World, hero: Hero) {
        hero.apply {
            speed = speedPercent * maxSpeed
            way = wayHero
        }
    }

    fun checkPressKeys() {
        if( Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            speedPercent = 1f
            wayHero = Movies.LEFT
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            speedPercent = 1f
            wayHero = Movies.RIGHT
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            speedPercent = 1f
            wayHero = Movies.UP
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            speedPercent = 1f
            wayHero = Movies.DOWN
        } else {
            speedPercent = 0f
        }
    }
}