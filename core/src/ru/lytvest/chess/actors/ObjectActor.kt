package ru.lytvest.chess.actors

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import ru.lytvest.chess.scenes.SceneManager
import ru.lytvest.chess.scenes.WorldScene
import ru.lytvest.game.*

typealias Anims = Array<Animation<TextureRegion>>

open class ObjectActor(
    val sleepAnims: Anims,
    val goAnims: Anims = sleepAnims,
    val attackAnims: Anims = sleepAnims,
    val dieAnims: Anims = sleepAnims,
    val animX: Float = -1.1f,
    val animY: Float = -0.1f,
    val animScale: Float = 2.1f,
    val hpBarHeight: Float = 0.3f,
    val hpBarY: Float = 2.4f,

    ) : Actor() {

    val skin get() = SceneManager.skin

    val circle = skin.getRegion("circle")
    val hpBarRed = skin.getRegion("hp-bar-red")
    val hpBarGreen = skin.getRegion("hp-bar-green")
    var timer = 0f
    var angle = Circle.LEFT
    var speed = 0f
    var attackedTact = AnimationTact.NO_RUN
    var dieTact = AnimationTact.NO_RUN
    var sceneScale = 1f
    var animDuration = 1f
    var heroHp = 1f
    var heroMaxHp = 1f
    var showHpBar = false


    override fun act(delta: Float) {
        super.act(delta)
        timer += delta
    }

    fun update(obj: ID, scene: WorldScene): ObjectActor {
        scene.apply {
            setBounds(
                obj.x * scale - obj.r * scale - screenX,
                obj.y * scale - obj.r * scale - screenY,
                obj.r * 2 * scale,
                obj.r * 2 * scale
            )
        }
        sceneScale = scene.scale

        if (obj is WithHp) {
            showHpBar = true
            heroHp = obj.hp
            heroMaxHp = obj.hpMax
//            if (heroHp == heroMaxHp) {
//                showHpBar = false
//            }
        }

        if (obj is Movies) {
            angle = obj.way
            speed = obj.speed
        }

        if (obj is Hero) {

            if (attackedTact == AnimationTact.NO_RUN && obj.isAttacked()) {
                attackedTact = AnimationTact.START
            }

            if (attackedTact != AnimationTact.NO_RUN && !obj.isAttacked()) {
                // TODO
            }
            animDuration = obj.attackDuration


        }

        return this
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        if (width > 50) {
            batch.draw(circle, x, y, width, height)
        }

        if (width > 50 && heroHp > 0 && showHpBar) {

            batch.draw(hpBarRed, x, y + hpBarY * sceneScale, width, hpBarHeight * sceneScale)
            batch.draw(hpBarGreen, x, y + hpBarY * sceneScale, width * heroHp / heroMaxHp, hpBarHeight * sceneScale)
        }
        if (attackedTact == AnimationTact.START) {
            timer = 0f
            attackedTact = AnimationTact.RUN
        }
        if (heroHp <= 0 && dieTact == AnimationTact.NO_RUN) {
            timer = 0f
            dieTact = AnimationTact.RUN
        }

        val anims = if (dieTact == AnimationTact.RUN)
            dieAnims
        else if (attackedTact == AnimationTact.RUN) {
            for (anim in attackAnims) {
                anim.apply {
                    frameDuration = animDuration / keyFrames.size
                }
            }
            attackAnims
        } else if (speed > 0.01) {
//
            goAnims
        } else {
            sleepAnims
        }
        val anim = if (Circle.isUp(angle)) anims[1]
        else if (Circle.isDown(angle)) anims[2]
        else anims[0]


        if (attackedTact == AnimationTact.RUN && anim.isAnimationFinished(timer)) {
            attackedTact = AnimationTact.NO_RUN
        }

        val frame = anim.getKeyFrame(timer)
        val dl = frame.regionHeight.toFloat() / frame.regionWidth.toFloat()

        Circle.apply {
            val widthS = width * animScale
            if (angle.equalsAngle(LEFT, pi / 2)) {
                batch.draw(frame, x + widthS + animX * sceneScale, y + animY * sceneScale, -widthS, dl * widthS)
            } else {
                batch.draw(frame, x + animX * sceneScale, y + animY * sceneScale, widthS, dl * widthS)
            }
        }
    }

}