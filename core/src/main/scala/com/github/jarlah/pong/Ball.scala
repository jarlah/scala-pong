package com.github.jarlah.pong

import com.badlogic.gdx.math.Rectangle

case class Ball(xPosition: Float,
                yPosition: Float,
                width: Int,
                height: Int,
                yVelocity: Float = 200,
                xVelocity: Float = 100) {
  def rectangle = new Rectangle(xPosition, yPosition, width, height)
  def overlaps(other: Rectangle) = rectangle.overlaps(other)
}

object Ball {
  val width: Int = 16
  val height: Int = 16
  val defaultPositionFactor = 0.7f

  def create(dimensions: Dimensions, initialPosition: Option[Float] = None) =
    Ball(dimensions.width / 2 - width / 2, initialPosition.getOrElse(dimensions.height * defaultPositionFactor), width, height)
}
