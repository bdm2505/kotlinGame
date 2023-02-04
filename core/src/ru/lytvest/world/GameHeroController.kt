package ru.lytvest.world

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import ru.lytvest.game.Circle
import ru.lytvest.game.Hero
import ru.lytvest.game.HeroController
import ru.lytvest.game.World

class GameHeroController : HeroController {

    var wayHero = Circle.RIGHT
    var speedPercent = 0f
    var attack: Boolean = false

    var x = 0f
    var y = 0f

    override fun control(world: World, hero: Hero) {
        hero.apply {
            speed = speedPercent * speedMax
            way = wayHero
        }
        x = hero.x
        y = hero.y

        if (attack) {
            hero.startAttack(world)
        }
    }

    fun checkPressKeys() {
        if( Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            speedPercent = 1f
            wayHero = Circle.LEFT
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            speedPercent = 1f
            wayHero = Circle.RIGHT
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            speedPercent = 1f
            wayHero = Circle.UP
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            speedPercent = 1f
            wayHero = Circle.DOWN
        } else {
            speedPercent = 0f
        }

        attack = Gdx.input.isKeyPressed(Input.Keys.SPACE)
    }
}