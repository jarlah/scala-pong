package com.github.jarlah.pong

import com.badlogic.gdx.math.Rectangle

case class Paddle(x: Float, y: Float, width: Int, height: Int, xVelocity: Int = 200) {
  def rectangle = new Rectangle(x, y, width, height)
  def overlaps(other: Rectangle) = rectangle.overlaps(other)
}

object Paddle {
  val width: Int = 80
  val height: Int = 16

  def create(dimensions: Dimensions, initialPosition: Option[Float] = None) =
    Paddle(dimensions.width / 2 - width / 2, initialPosition.getOrElse(20f), width, height)
}
