package com.github.jarlah.pong

import com.badlogic.gdx.graphics.{Color, Texture}

class GameScreen(game: PongGame) extends AbstractScreen {

  val ballImage = new Texture("ball.png")
  var ball = Ball.create(game)

  val paddleImage = new Texture("paddle.png")
  var paddle = Paddle.create(game)

  def render(delta: Float) {
    game.clearScreen(Color.DARK_GRAY)
    game.batch.begin()
    game.batch.setColor(Color.BLACK)
    game.batch.draw(ballImage, ball.x, ball.y)
    game.batch.draw(paddleImage, paddle.x, paddle.y)
    game.batch.end()
    if (!paused) {
      advancePaddle(delta)
      advanceBall(delta)
      if (ballHitsCeiling || paddle.overlaps(ball.rectangle)) {
        flipVerticalBallDirection()
      }
      if (ballHitsFloor) {
        repositionBallAtTop()
      }
    }
  }

  def ballHitsFloor = ball.y < 0

  def ballHitsCeiling = ball.y > game.height - ball.width

  def repositionBallAtTop() = ball = ball.copy(y = game.height - ball.width)

  def flipVerticalBallDirection() = ball = ball.copy(direction = if (ball.direction == BallDown) BallUp else BallDown)

  def advanceBall(delta: Float) = {
    if (ball.direction == BallDown)
      ball = ball.copy(y = ball.y - ball.speed * delta)
    if (ball.direction == BallUp)
      ball = ball.copy(y = ball.y + ball.speed * delta)
  }

  def advancePaddle(delta: Float) = {
    if (game.isLeftKeyPressed)
      paddle = paddle.copy(x = paddle.x - paddle.speed * delta)
    if (game.isRightKeyPressed)
      paddle = paddle.copy(x = paddle.x + paddle.speed * delta)
  }

  override def dispose() = {
    ballImage.dispose()
    paddleImage.dispose()
  }
}