package ru.lytvest.chess.scenes

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Image
import ru.lytvest.chess.actors.HeroActor
import ru.lytvest.world.GameHeroController
import ru.lytvest.game.Hero
import ru.lytvest.game.ID
import ru.lytvest.game.World

class WorldScene : Scene() {

    val playerTexture = TextureRegion(skin.getRegion("player"), 15,210, 20, 30)
    val world = World()
    val heroController = GameHeroController()
    var scale: Float = 50f
    var screenX = -500f
    var screenY = -500f

    val objects = mutableMapOf<Long, HeroActor>()


    init {
        val hero = Hero()
        world.addHero(hero)
        world.controllers[hero.id] = heroController
    }
    fun HeroActor.update(obj: ID): HeroActor {
        setBounds(
            obj.x * scale - obj.r * scale - screenX,
            obj.y * scale - obj.r * scale - screenY,
            obj.r * 2 * scale,
            obj.r * 2 * scale
        )
        return this
    }

    override fun update(delta: Float) {
        super.update(delta)
        world.update(delta)
        val ids = world.all.map { it.id }.toSet()

        for(id in objects.keys) {
            if (!ids.contains(id)) {
                objects[id]?.remove()
                objects.remove(id)
            }
        }

        for (obj in world.all) {
            if (objects.contains(obj.id)) {
                objects[obj.id]?.update(obj)
            } else {
                objects[obj.id] = add { HeroActor().update(obj) }
            }
        }

        heroController.checkPressKeys()
    }
}