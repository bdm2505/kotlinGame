package ru.lytvest.chess.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import ru.lytvest.chess.scenes.SceneManager
import ru.lytvest.chess.scenes.WorldScene
import ru.lytvest.game.Hero
import ru.lytvest.game.ID
import ru.lytvest.game.Movies

class HeroActor() : Actor() {

    val skin get() = SceneManager.skin

    val circle = skin.getRegion("circle")

    val animRight = HeroAnimations.rightHeroWalk
    val animUp = HeroAnimations.upHeroWalk
    val animDown = HeroAnimations.downHeroWalk
    val animRightStop = HeroAnimations.rightHeroStop
    val animUpStop = HeroAnimations.upHeroStop
    val animDownStop = HeroAnimations.downHeroStop

    var timer = 0f
    var angle = Movies.LEFT
    var speed = 0f


    override fun act(delta: Float) {
        super.act(delta)
        timer += delta
    }

    fun update(obj: ID, scene: WorldScene): HeroActor {
        scene.apply {
            setBounds(
                obj.x * scale - obj.r * scale - screenX,
                obj.y * scale - obj.r * scale - screenY,
                obj.r * 2 * scale,
                obj.r * 2 * scale
            )
        }
        if (obj is Hero) {
            angle = obj.way
            speed = obj.speed
        }
        return this
    }

    override fun draw(batch: Batch, parentAlpha: Float) {

        if (width > 50) {

            batch.draw(circle, x, y, width, height)
        }
        val anim = if (speed > 0.01) {
            if (Movies.isUp(angle)) animUp
            else if (Movies.isDown(angle)) animDown
            else animRight
        } else {
            if (Movies.isUp(angle)) animUpStop
            else if (Movies.isDown(angle)) animDownStop
            else animRightStop
        }

        val frame = anim.getKeyFrame(timer)
        val dl = frame.regionHeight.toFloat() / frame.regionWidth.toFloat()

        Movies.apply {
            if (angle.equalsAngle(LEFT, pi / 2)) {
                batch.draw(frame, x + width, y, -width, dl * width)
            } else {
                batch.draw(frame, x, y, width, dl * width)
            }
        }


    }
}