package com.github.jarlah.pong

import com.badlogic.gdx.math.Rectangle

case class Paddle(xPosition: Float,
                  yPosition: Float,
                  width: Int,
                  height: Int,
                  xVelocity: Int = 200) {
  def rectangle = new Rectangle(xPosition, yPosition, width, height)
  def overlaps(other: Rectangle) = rectangle.overlaps(other)
}

object Paddle {
  val width: Int = 80
  val height: Int = 16

  def create(dimensions: Dimensions, initialPosition: Option[Float] = None) =
    Paddle(dimensions.width / 2 - width / 2, initialPosition.getOrElse(20f), width, height)
}
