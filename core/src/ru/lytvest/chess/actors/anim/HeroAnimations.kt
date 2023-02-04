package ru.lytvest.chess.actors.anim

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode
import com.badlogic.gdx.graphics.g2d.TextureRegion
import ru.lytvest.chess.actors.Anims
import ru.lytvest.chess.scenes.SceneManager


class HeroAnimations {

    companion object {

        val stHero = 4
        val widthHero = 41
        val heightHero = 27

        val rightHeroWalk: Animation<TextureRegion> by lazy {
            val reg = SceneManager.skin.getRegion("player")
            val arr = Array(6) { TextureRegion(reg, stHero + it * 48, 212, widthHero, heightHero) }
            val arrGdx = com.badlogic.gdx.utils.Array(arr)
            Animation<TextureRegion>(1f / 6f, arrGdx, PlayMode.LOOP)
        }

        val downHeroWalk: Animation<TextureRegion> by lazy {
            val reg = SceneManager.skin.getRegion("player")
            val arr = Array(6) { TextureRegion(reg, stHero + it * 48, 164, widthHero, heightHero) }
            val arrGdx = com.badlogic.gdx.utils.Array(arr)
            Animation<TextureRegion>(1f / 6f, arrGdx, PlayMode.LOOP)
        }

        val upHeroWalk: Animation<TextureRegion> by lazy {
            val reg = SceneManager.skin.getRegion("player")
            val arr = Array(6) { TextureRegion(reg, stHero + it * 48, 260, widthHero, heightHero) }
            val arrGdx = com.badlogic.gdx.utils.Array(arr)
            Animation<TextureRegion>(1f / 6f, arrGdx, PlayMode.LOOP)
        }

        val rightHeroStop: Animation<TextureRegion> by lazy {
            val reg = SceneManager.skin.getRegion("player")
            val arr = Array(6) { TextureRegion(reg, stHero + it * 48, 68, widthHero, heightHero) }
            val arrGdx = com.badlogic.gdx.utils.Array(arr)
            Animation<TextureRegion>(1f / 6f, arrGdx, PlayMode.LOOP)
        }

        val upHeroStop: Animation<TextureRegion> by lazy {
            val reg = SceneManager.skin.getRegion("player")
            val arr = Array(6) { TextureRegion(reg, stHero + it * 48, 116, widthHero, heightHero) }
            val arrGdx = com.badlogic.gdx.utils.Array(arr)
            Animation<TextureRegion>(1f / 6f, arrGdx, PlayMode.LOOP)
        }

        val downHeroStop: Animation<TextureRegion> by lazy {
            val reg = SceneManager.skin.getRegion("player")
            val arr = Array(6) { TextureRegion(reg, stHero + it * 48, 20, widthHero, heightHero) }
            val arrGdx = com.badlogic.gdx.utils.Array(arr)
            Animation<TextureRegion>(1f / 6f, arrGdx, PlayMode.LOOP)
        }

        val rightHeroAttack: Animation<TextureRegion> by lazy {
            val reg = SceneManager.skin.getRegion("player")
            val arr = Array(4) { TextureRegion(reg, stHero + it * 48, 355, widthHero, heightHero) }
            val arrGdx = com.badlogic.gdx.utils.Array(arr)
            Animation<TextureRegion>(1f / 4f, arrGdx, PlayMode.NORMAL)
        }

        val upHeroAttack: Animation<TextureRegion> by lazy {
            val reg = SceneManager.skin.getRegion("player")
            val arr = Array(4) { TextureRegion(reg, stHero + it * 48, 404, widthHero, heightHero) }
            val arrGdx = com.badlogic.gdx.utils.Array(arr)
            Animation<TextureRegion>(1f / 4f, arrGdx, PlayMode.NORMAL)
        }

        val downHeroAttack: Animation<TextureRegion> by lazy {
            val reg = SceneManager.skin.getRegion("player")
            val arr = Array(4) { TextureRegion(reg, stHero + it * 48, 310, widthHero, heightHero) }
            val arrGdx = com.badlogic.gdx.utils.Array(arr)
            Animation<TextureRegion>(1f / 4f, arrGdx, PlayMode.NORMAL)
        }

        val rightHeroDie: Animation<TextureRegion> by lazy {
            val reg = SceneManager.skin.getRegion("player")
            val arr = Array(3) { TextureRegion(reg, stHero + it * 48, 452, widthHero, heightHero) }
            val arrGdx = com.badlogic.gdx.utils.Array(arr)
            Animation<TextureRegion>(1f / 3f, arrGdx, PlayMode.NORMAL)
        }


        val grassTitles: Array<TextureRegion> by lazy {
            val reg = SceneManager.skin.getRegion("TexturedGrass")
            Array(3) { TextureRegion(reg, it * 16, 0, 16, 16) } +
                    Array(3) { TextureRegion(reg, it * 16, 16, 16, 16) }
        }

        val heroAttackAnimations: Anims by lazy { arrayOf(rightHeroAttack, upHeroAttack, downHeroAttack) }
        val heroGoAnimations: Anims by lazy { arrayOf(rightHeroWalk, upHeroWalk, downHeroWalk) }
        val heroDieAnimations: Anims by lazy { arrayOf(rightHeroDie, rightHeroDie, rightHeroDie) }
        val heroStopAnimations: Anims by lazy { arrayOf(rightHeroStop, upHeroStop, downHeroStop) }


    }
}

fun createAnimation(
    array: Array<TextureRegion>,
    duration: Float = 1f,
    playMode: PlayMode = PlayMode.NORMAL
): Animation<TextureRegion> {
    val arrGdx = com.badlogic.gdx.utils.Array(array)
    return Animation<TextureRegion>(duration / array.size.toFloat(), arrGdx, playMode)
}


