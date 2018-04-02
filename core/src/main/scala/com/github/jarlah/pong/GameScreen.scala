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
    game.batch.draw(ballImage, ball.xPosition, ball.yPosition)
    game.batch.draw(paddleImage, paddle.xPosition, paddle.yPosition)
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

  def ballHitsFloor = ball.yPosition < 0

  def ballHitsCeiling = ball.yPosition > game.height - ball.width

  def repositionBallAtTop() = ball = ball.copy(yPosition = game.height - ball.width)

  def flipVerticalBallDirection() = ball = ball.copy(direction = if (ball.direction == BallDown) BallUp else BallDown)

  def advanceBall(delta: Float, xVelocity: Float = ball.xVelocity, yVelocity: Float = ball.yVelocity) = {
    ball.direction match {
      case BallDown =>
        ball = ball.copy(yPosition = ball.yPosition - yVelocity * delta)
      case BallUp =>
        ball = ball.copy(yPosition = ball.yPosition + yVelocity * delta)
      case BallLeft =>
        ball = ball.copy(xPosition = ball.xPosition - xVelocity * delta)
      case BallRight =>
        ball = ball.copy(xPosition = ball.xPosition + xVelocity * delta)
      case BallUpLeft =>
        ball = ball.copy(yPosition = ball.yPosition + yVelocity * delta,
          xPosition = ball.xPosition - xVelocity * delta)
      case BallUpRight =>
        ball = ball.copy(yPosition = ball.yPosition + yVelocity * delta,
          xPosition = ball.xPosition + xVelocity * delta)
      case BallDownLeft =>
        ball = ball.copy(yPosition = ball.yPosition - (yVelocity * delta),
          xPosition = ball.xPosition - xVelocity * delta)
      case BallDownRight =>
        ball = ball.copy(yPosition = ball.yPosition - (yVelocity * delta),
          xPosition = ball.xPosition + xVelocity * delta)
    }
  }

  def advancePaddle(delta: Float) = {
    if (game.isLeftKeyPressed)
      paddle = paddle.copy(xPosition = paddle.xPosition - paddle.xVelocity * delta)
    if (game.isRightKeyPressed)
      paddle = paddle.copy(xPosition = paddle.xPosition + paddle.xVelocity * delta)
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