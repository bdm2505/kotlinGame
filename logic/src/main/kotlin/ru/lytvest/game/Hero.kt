package ru.lytvest.game

class Hero : Movies, Circle, Obj(), WithHp {
    override var way: Float = 0f
    override var speed: Float = 1f
    override var speedMax: Float = 5f

    override var hp: Float = 10f
    override var hpMax: Float = 10f
    var attack: Float = 1f
    var attackDistance: Float = 5f
    var attackDuration = 0.5f
    override var armor: Float = 0f

    val baffs = mutableSetOf<String>()

    var attackedTime: Float? = null
    override fun toString(): String {
        return "Hero(id=$id, x=$x, y=$y, speed=$speed)"
    }

    fun isAttacked() = attackedTime != null

    fun startAttack(world: World) {
        if (isAttacked())
            return
        attackedTime = world.currentTime
        speed = 0f
    }

    fun attackedEnemies(world: World, func: (WithHp) -> Unit) {
        val enemies = world.allObjects(this, r + attackDistance)

        for (enemy in enemies) {
            if (enemy is WithHp && (rayCollisionWithCircle(way, r + attackDistance, this, enemy) ||
                        rayCollisionWithCircle(way + Circle.PI_NA_8, r + attackDistance, this, enemy) ||
                        rayCollisionWithCircle(way - Circle.PI_NA_8, r + attackDistance, this, enemy))
            ) {
                func(enemy)
            }
        }
    }

    fun attackedEnemiesList(world: World): List<WithHp> {
        val list = mutableListOf<WithHp>()
        attackedEnemies(world) {
            list += it
        }
        return list
    }

    fun endAttack(world: World) {
        attackedEnemies(world) { enemy ->
            enemy.blockedAttack(world, attack)
        }
    }


    fun update(world: World, delta: Float) {
        if (isDie()) {
            speed = 0f
            return
        }

        if (isAttacked()) {
            if (attackedTime!! + attackDuration < world.currentTime) {
                attackedTime = null
                endAttack(world)
            }
            return
        }

        val wayOld = way
        if (!move(world, delta)) {
            way = wayOld + Circle.PI_NA_4
            if (!move(world, delta)) {
                way = wayOld - Circle.PI_NA_4
                move(world, delta)

            }
        }
        way = wayOld

    }


}

fun main() {
    val hero = Hero()
    val hero2 = Hero()
    hero2.x = 5f
    hero2.y = 4f
    val world = World()
    world.add(hero)
    world.add(hero2)
    println(world.allObjects(hero, 10f).size)

    hero.way = Circle.RIGHT

    println(hero.attackedEnemiesList(world))
    println("dis = " + hero.distanceTo(hero2))
    println(world.withHps)

//    hero.move(world, 10f)
//    println(hero)
}