package com.github.jarlah.pong

import com.badlogic.gdx.graphics.Color

class GameOverScreen(game: PongGame) extends AbstractScreen {
  def render(delta: Float): Unit = {
    game.clearScreen(Color.DARK_GRAY)
    game.batch.begin()
    game.font.draw(game.batch, "Game over...", 300, 350)
    game.font.draw(game.batch, "Tap ESCAPE key to return to main menu!", 300, 300)
    game.batch.end()
    if (game.isEscapeKeyPressed) {
      game.setScreen(new MainMenu(game))
      dispose()
    }
  }
}
