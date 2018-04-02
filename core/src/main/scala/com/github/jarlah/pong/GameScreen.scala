package com.github.jarlah.pong

import com.badlogic.gdx.graphics.{Color, Texture}

import scala.annotation.tailrec

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
      if (paddle.overlaps(ball.rectangle)) {
        fixStuckInPaddleOnTheWayDown()
        flipVerticalBallDirection()
      }
      advanceBall(delta)
      if (ballHitsCeiling) {
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

  def advanceBall(delta: Float, xVelocity: Float = ball.xVelocity, yVelocity: Float = ball.yVelocity) = {
    if (ball.direction == BallDown)
      ball = ball.copy(y = ball.y - yVelocity * delta)
    if (ball.direction == BallUp)
      ball = ball.copy(y = ball.y + yVelocity * delta)
    if (ball.direction == BallLeft)
      ball = ball.copy(x = ball.x - xVelocity * delta)
    if (ball.direction == BallRight)
      ball = ball.copy(x = ball.x + xVelocity * delta)
    if (ball.direction == BallUpLeft)
      ball = ball.copy(y = ball.y + yVelocity * delta, x = ball.x - xVelocity * delta)
    if (ball.direction == BallUpRight)
      ball = ball.copy(y = ball.y + yVelocity * delta, x = ball.x + xVelocity * delta)
    if (ball.direction == BallDownLeft)
      ball = ball.copy(y = ball.y - (yVelocity * delta), x = ball.x - xVelocity * delta)
    if (ball.direction == BallDownRight)
      ball = ball.copy(y = ball.y - (yVelocity * delta), x = ball.x + xVelocity * delta)
  }

  def advancePaddle(delta: Float) = {
    if (game.isLeftKeyPressed)
      paddle = paddle.copy(x = paddle.x - paddle.xVelocity * delta)
    if (game.isRightKeyPressed)
      paddle = paddle.copy(x = paddle.x + paddle.xVelocity * delta)
  }

  @tailrec
  private def fixStuckInPaddleOnTheWayDown(): Unit = {
    if (ball.direction == BallDownRight || ball.direction == BallDownLeft) {
      advanceBall(1, 0, -1) // in effect plus
      if (paddle.overlaps(ball.rectangle)) {
        fixStuckInPaddleOnTheWayDown()
      }
    }
  }

  override def dispose() = {
    ballImage.dispose()
    paddleImage.dispose()
  }
}