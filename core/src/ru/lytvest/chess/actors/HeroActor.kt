package ru.lytvest.chess.actors

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.ui.Image
import ru.lytvest.chess.scenes.SceneManager

class HeroActor() : Actor() {
    val skin get() = SceneManager.skin

    val circle = skin.getRegion("circle")

    override fun act(delta: Float) {
        super.act(delta)
    }

    override fun draw(batch: Batch, parentAlpha: Float) {

        batch.draw(circle, x, y, width, height)

    }
}