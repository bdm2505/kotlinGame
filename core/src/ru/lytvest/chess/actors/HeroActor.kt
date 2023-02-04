package ru.lytvest.chess.actors

import ru.lytvest.chess.actors.anim.HeroAnimations

class HeroActor : ObjectActor(
    HeroAnimations.heroStopAnimations,
    HeroAnimations.heroGoAnimations,
    HeroAnimations.heroAttackAnimations,
    HeroAnimations.heroDieAnimations
) {


}