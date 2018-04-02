package gdx.scala.demo

import com.badlogic.gdx.Screen

trait AbstractScreen extends Screen {
  def show(): Unit = {
  }

  def resize(width: Int, height: Int): Unit = {
  }

  def pause(): Unit = {
  }

  def resume(): Unit = {
  }

  def hide(): Unit = {
  }

  def dispose(): Unit = {
  }
}
