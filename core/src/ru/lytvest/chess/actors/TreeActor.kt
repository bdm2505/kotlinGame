package ru.lytvest.chess.actors

import ru.lytvest.chess.actors.anim.TreeAnim

class TreeActor : ObjectActor(
    TreeAnim.treeAnim, animScale = 1.4f, animX = -0.3f, animY = 0.1f, hpBarY = -0.2f
) {

}