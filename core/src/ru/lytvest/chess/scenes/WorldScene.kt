package ru.lytvest.chess.scenes

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import ru.lytvest.chess.actors.HeroActor
import ru.lytvest.chess.actors.ObjectActor
import ru.lytvest.chess.actors.TreeActor
import ru.lytvest.chess.actors.anim.HeroAnimations
import ru.lytvest.game.Hero
import ru.lytvest.game.ID
import ru.lytvest.game.Tree
import ru.lytvest.game.World
import ru.lytvest.world.GameHeroController

class WorldScene : Scene() {

    val playerTexture = TextureRegion(skin.getRegion("player"), 15, 210, 20, 30)
    val world = World()
    val heroController = GameHeroController()
    var scale: Float = 50f
    var screenX = -500f
    var screenY = -500f

    val objects = mutableMapOf<Int, ObjectActor>()

    val grasses = mutableMapOf<Int, Image>()


    init {
        val hero = Hero()
        world.add(hero)
        world.createRandomTrees(200)
        world.controllers[hero.id] = heroController

        add {
            Button(skin, "plus").apply {
                setBounds(10f, 120f, 100f, 100f)
                addListener(object : ClickListener() {
                    override fun clicked(event: InputEvent?, x: Float, y: Float) {
                        scale += 0.8f
                    }

                    var time = System.currentTimeMillis()

                    override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {

                        val touch = super.touchDown(event, x, y, pointer, button)
                        if (touch) {
                            time = System.currentTimeMillis();
                        }
                        return touch
                    }

                    override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
                        super.touchDragged(event, x, y, pointer)
                        if (System.currentTimeMillis() - time > 500) {
                            scale += 0.3f
                        }
                    }
                })
            }
        }
        add {
            Button(skin, "minus").apply {
                setBounds(10f, 10f, 100f, 100f)
                addListener(object : ClickListener() {
                    override fun clicked(event: InputEvent?, x: Float, y: Float) {
                        if (scale < 0.5) {
                            scale *= 0.7f
                        } else {
                            scale -= 0.8f
                        }
                    }
                    var time = System.currentTimeMillis()

                    override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {

                        val touch = super.touchDown(event, x, y, pointer, button)
                        if (touch) {
                            time = System.currentTimeMillis();
                        }
                        return touch
                    }

                    override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
                        super.touchDragged(event, x, y, pointer)
                        if (System.currentTimeMillis() - time > 500) {
                            if (scale < 0.5) {
                                scale *= 0.9f
                            } else {
                                scale -= 0.3f
                            }
                        }
                    }
                })
            }
        }
    }


    override fun update(delta: Float) {
        super.update(delta)
        world.update(delta)

        screenX = heroController.x * scale - width() / 2
        screenY = heroController.y * scale - height() / 2

        for (grass in world.fons) {
            (grasses[grass.id] ?: grasses.put(
                grass.id,
                add { Image(HeroAnimations.grassTitles[grass.number]) }))?.apply {
                setBounds(
                    grass.x * scale - grass.r * scale - screenX,
                    grass.y * scale - grass.r * scale - screenY,
                    grass.r * 2 * scale,
                    grass.r * 2 * scale
                )
                toBack()
            }
        }

        val ids = world.all.map { it.id }.toSet()

        val removedIds = mutableSetOf<Int>()

        for (id in objects.keys) {
            if (!ids.contains(id)) {
                objects[id]?.remove()
                removedIds += id
            }
        }
        for (id in removedIds) {
            objects.remove(id)
        }

        for (obj in world.all) {
            if (objects.contains(obj.id)) {
                objects[obj.id]?.update(obj, this)
            } else {
                objects[obj.id] = add { createActor(obj).update(obj, this) }
            }
        }

        for (act in objects.values.sortedBy { -it.y }) {
            act.toFront()
        }

        heroController.checkPressKeys()

        world.add(Hero())

    }

    fun createActor(obj: ID): ObjectActor {
        return when (obj) {
            is Tree -> TreeActor()
            is Hero -> HeroActor()
            else -> throw Exception("no display $obj : ${obj.javaClass.name}")
        }
    }
}