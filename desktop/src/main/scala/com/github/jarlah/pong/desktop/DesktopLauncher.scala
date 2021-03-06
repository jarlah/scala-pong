package com.github.jarlah.pong.desktop

import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}
import com.github.jarlah.pong.PongGame

object DesktopLauncher extends App {
  val config = new LwjglApplicationConfiguration()
  config.title = "Pong"
  config.width = 800
  config.height = 480
  val game = new PongGame(config.width, config.height)
  val app = new LwjglApplication(game, config)
}
