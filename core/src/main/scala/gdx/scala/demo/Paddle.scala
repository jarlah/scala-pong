package gdx.scala.demo

import com.badlogic.gdx.math.Rectangle

case class Paddle(x: Float, y: Float, width: Int, height: Int, speed: Int = 200) {
  def asRect: Rectangle = new Rectangle(x, y, width, height)
}

object Paddle {
  private[this] val width: Int = 80
  private[this] val height: Int = 16

  def create(dimensions: Dimensions, initialPosition: Option[Float] = None): Paddle =
    Paddle(dimensions.width / 2 - width / 2, initialPosition.getOrElse(20f), width, height)
}


