package com.github.jarlah.pong

sealed trait BallDirection

object BallUp extends BallDirection
object BallDown extends BallDirection
object BallLeft extends BallDirection
object BallRight extends BallDirection
object BallUpRight extends BallDirection
object BallDownRight extends BallDirection
object BallUpLeft extends BallDirection
object BallDownLeft extends BallDirection