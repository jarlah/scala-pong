package com.github.jarlah.pong

import com.badlogic.gdx.math.Rectangle

case class Ball(x: Float, y: Float, width: Int, height: Int, speed: Int = 200, direction: BallDirection = BallDown) {
  def rectangle = new Rectangle(x, y, width, height)
  def overlaps(other: Rectangle) = rectangle.overlaps(other)
}

object Ball {
  val width: Int = 16
  val height: Int = 16
  val defaultPositionFactor = 0.7f

  def create(dimensions: Dimensions, initialPosition: Option[Float] = None) =
    Ball(dimensions.width / 2 - width / 2, initialPosition.getOrElse(dimensions.height * defaultPositionFactor), width, height)
}