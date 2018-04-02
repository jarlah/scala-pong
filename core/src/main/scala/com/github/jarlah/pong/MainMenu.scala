package com.github.jarlah.pong

import com.badlogic.gdx.graphics.Color

class MainMenu(game: PongGame) extends AbstractScreen {

  override def render(delta: Float): Unit = {
    game.clearScreen(Color.DARK_GRAY)
    game.batch.begin()
    game.font.draw(game.batch, "Welcome to Pong! ", 300, 350)
    game.font.draw(game.batch, "Tap ENTER key to begin!", 300, 300)
    game.batch.end()
    if (game.isEnterKeyPressed) {
      game.setScreen(new GameScreen(game))
      dispose()
    }
  }
}
