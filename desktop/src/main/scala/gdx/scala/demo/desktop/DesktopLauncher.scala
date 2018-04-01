package gdx.scala.demo.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import gdx.scala.demo.PongGame

object DesktopLauncher extends App {
  val width = 800
  val height = 480
  val config = new LwjglApplicationConfiguration()
  config.title = "Pong"
  config.width = width
  config.height = height
  new LwjglApplication(new PongGame(width, height), config)
}
