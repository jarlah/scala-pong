package gdx.scala.demo

import com.badlogic.gdx.Screen

trait AbstractScreen extends Screen {
  var paused = false

  def show(): Unit = {
  }

  def resize(width: Int, height: Int): Unit = {
  }

  def pause(): Unit = {
    paused = true
  }

  def resume(): Unit = {
    paused = false
  }

  def hide(): Unit = {
  }

  def dispose(): Unit = {
  }
}
