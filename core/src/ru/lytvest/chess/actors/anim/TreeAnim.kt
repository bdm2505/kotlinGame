package ru.lytvest.chess.actors.anim

import com.badlogic.gdx.graphics.g2d.TextureRegion
import ru.lytvest.chess.actors.Anims
import ru.lytvest.chess.scenes.SceneManager

class TreeAnim {
    companion object {
        val tree: TextureRegion by lazy { SceneManager.skin.getRegion("tree") }
        val treeAnim: Anims by lazy {
            arrayOf(
                createAnimation(arrayOf(tree)),
                createAnimation(arrayOf(tree)),
                createAnimation(arrayOf(tree))
            )
        }

    }
}