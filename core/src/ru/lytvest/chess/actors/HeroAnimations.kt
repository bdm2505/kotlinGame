package ru.lytvest.chess.actors

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import ru.lytvest.chess.scenes.SceneManager


class HeroAnimations {

    companion object {
        val rightHeroWalk: Animation<TextureRegion> by lazy {
            val reg = SceneManager.skin.getRegion("player")
            val arr = Array(6) { TextureRegion(reg, 17 + it * 48, 212, 15, 24) }
            val arrGdx = com.badlogic.gdx.utils.Array(arr)
            Animation<TextureRegion>(1f / 6f, arrGdx, Animation.PlayMode.LOOP)
        }

        val downHeroWalk: Animation<TextureRegion> by lazy {
            val reg = SceneManager.skin.getRegion("player")
            val arr = Array(6) { TextureRegion(reg, 17 + it * 48, 164, 15, 24) }
            val arrGdx = com.badlogic.gdx.utils.Array(arr)
            Animation<TextureRegion>(1f / 6f, arrGdx, Animation.PlayMode.LOOP)
        }

        val upHeroWalk: Animation<TextureRegion> by lazy {
            val reg = SceneManager.skin.getRegion("player")
            val arr = Array(6) { TextureRegion(reg, 17 + it * 48, 260, 15, 24) }
            val arrGdx = com.badlogic.gdx.utils.Array(arr)
            Animation<TextureRegion>(1f / 6f, arrGdx, Animation.PlayMode.LOOP)
        }

        val rightHeroStop: Animation<TextureRegion> by lazy {
            val reg = SceneManager.skin.getRegion("player")
            val arr = Array(6) { TextureRegion(reg, 17 + it * 48, 68, 15, 24) }
            val arrGdx = com.badlogic.gdx.utils.Array(arr)
            Animation<TextureRegion>(1f / 6f, arrGdx, Animation.PlayMode.LOOP)
        }

        val upHeroStop: Animation<TextureRegion> by lazy {
            val reg = SceneManager.skin.getRegion("player")
            val arr = Array(6) { TextureRegion(reg, 17 + it * 48, 116, 15, 24) }
            val arrGdx = com.badlogic.gdx.utils.Array(arr)
            Animation<TextureRegion>(1f / 6f, arrGdx, Animation.PlayMode.LOOP)
        }

        val downHeroStop: Animation<TextureRegion> by lazy {
            val reg = SceneManager.skin.getRegion("player")
            val arr = Array(6) { TextureRegion(reg, 17 + it * 48, 20, 15, 24) }
            val arrGdx = com.badlogic.gdx.utils.Array(arr)
            Animation<TextureRegion>(1f / 6f, arrGdx, Animation.PlayMode.LOOP)
        }



    }
}


