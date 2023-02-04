package ru.lytvest.game

import kotlin.random.Random

class World {

    val all = mutableSetOf<ID>()

    val withHps = mutableSetOf<WithHp>()
    val heroes = mutableSetOf<Hero>()

    val fons = mutableListOf<Grass>()
    val removedSet = mutableSetOf<ID>()

    val controllers = mutableMapOf<Int, HeroController>()
    val defaultController = SimpleRandomHeroController()

    var currentTime: Float = 0f
    var isUpdated = false
    val waitedRemoves = mutableSetOf<ID>()

    init {

        initGrass()

    }

    fun allObjects(point: Point, distance: Float): List<Circle> {
        return all.filter { it.distanceTo(point) <= distance + it.r && it != point }
    }

    fun isConflictPosition(circle: Circle, exclude: Point? = null): Boolean {
        return all.find { circle.distanceTo(it) < it.r + circle.r && it != exclude } != null
    }

    fun update(delta: Float) {
        isUpdated = true
        for (removeId in removedSet) {
            all.remove(removeId)
            heroes.remove(removeId)
        }

        for (hero in heroes) {
            if (hero.isDie())
                continue

            if (!controllers.containsKey(hero.id)) {
                controllers[hero.id] = SimpleRandomHeroController()
            }
            controllers[hero.id]?.control(this, hero)
        }

        for (hero in heroes) {
            hero.update(this, delta)
        }

        currentTime += delta
        isUpdated = false
        for (id in waitedRemoves) {
            remove(id)
        }
        waitedRemoves.clear()
    }


    fun initGrass() {
        for (x in -100..100 step 4) {
            for (y in -100..100 step 4) {
                fons += Grass(x.toFloat(), y.toFloat(), 2f)
            }
        }
    }

    tailrec fun createRandomTrees(count: Int, rec: Int = 3) {

        if (rec <= 0)
            return
        var countSuccess = 0
        for (i in 1..count) {
            val r = Random.nextInt(80).toFloat() / 100f + 0.5f
            val x = (Random.nextInt(2000).toFloat() - 1000f) / 10f
            val y = (Random.nextInt(2000).toFloat() - 1000f) / 10f
            if (add(Tree.create(x, y, r))) {
                countSuccess++
            }
        }
        if (countSuccess < count) {
            createRandomTrees(count - countSuccess, rec - 1)
        }
    }

    fun add(obj: ID): Boolean {
        if (!isConflictPosition(obj)) {
            all += obj
            when (obj) {
                is Hero -> heroes += obj
                is WithHp -> withHps += obj
            }
            return true
        }
        return false
    }

    fun waitRemove(obj: ID) {
        waitedRemoves += obj
    }

    fun remove(obj: ID) {
        if (isUpdated) {
            waitRemove(obj)
            return
        }
        all -= obj
        if (obj is WithHp) {
            withHps -= obj
        }
        if (obj is Hero) {
            heroes -= obj
        }
    }
}