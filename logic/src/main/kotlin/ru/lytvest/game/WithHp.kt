package ru.lytvest.game

interface WithHp : ID {
    var hp: Float
    val hpMax: Float
    val armor: Float

    fun isDie() = hp <= 0
    fun isLive() = hp > 0

    fun blockedAttack(world: World, damage: Float) {
        if (isDie()) {
            world.remove(this)
        }

        val d = damage - armor
        if (d > 0) {
            hp -= d
        }
        if (hp < 0f) {
            hp = 0f
        }
    }
}