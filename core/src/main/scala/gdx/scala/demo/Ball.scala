package gdx.scala.demo

import com.badlogic.gdx.math.Rectangle

case class Ball(x: Float, y: Float, width: Int, height: Int, speed: Int = 200) {
  def asRect: Rectangle = new Rectangle(x, y, width, height)
}

object Ball {
  private[this] val width: Int = 16
  private[this] val height: Int = 16
  private[this] val defaultPositionFactor = 0.7f

  def create(dimensions: Dimensions, initialPosition: Option[Float] = None): Ball =
    Ball(dimensions.width / 2 - width / 2, initialPosition.getOrElse(dimensions.height * defaultPositionFactor), width, height)
}
