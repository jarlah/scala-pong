package com.github.jarlah.pong

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.{BitmapFont, SpriteBatch}

class PongGame(val width: Int, val height: Int) extends Game with Dimensions with AbstractGdx {

  var batch: SpriteBatch = _
  var font: BitmapFont = _

  override def create(): Unit = {
    batch = new SpriteBatch
    font = new BitmapFont
    this.setScreen(new MainMenu(this))
  }
}
