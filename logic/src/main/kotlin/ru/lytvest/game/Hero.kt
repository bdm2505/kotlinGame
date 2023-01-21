package ru.lytvest.game

class Hero : Movies, Circle, ID() {
    override var x: Float = 0f
    override var y: Float = 0f
    override var r: Float = 1f
    override var way: Float = 0f
    override var speed: Float = 1f
    var maxSpeed: Float = 5f

    var hp: Float = 10f
    var attack: Float = 1f
    var attackDistance: Float = 5f
    var armor: Float = 0f
    override fun toString(): String {
        return "Hero(id=$id, x=$x, y=$y, speed=$speed)"
    }


}

fun main() {
    val hero = Hero()
    val world = World()
    world.addHero(hero)
    println(world.allObjects(hero, 10f).size)

    hero.move(world, 10f)
    println(hero)
}