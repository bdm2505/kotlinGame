package ru.lytvest.game

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class HeroTest {

    @Test
    fun testOne() {
        val hero = Hero()
        val world = World()
        world.addHero(hero)
        assertEquals(0, world.allObjects(hero, 10f).size)

    }
}