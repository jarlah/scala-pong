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
      if (ballHitsLeftWall || ballHitsRightWall) {
        flipHorizontalBallDirection()
      }
      if (ballHitsFloor) {
        game.setScreen(new GameOverScreen(game))
        dispose()
      }
    }
  }

  def ballHitsFloor = ball.yPosition < 0

  def ballHitsLeftWall = ball.xPosition < 0

  def ballHitsRightWall = ball.xPosition > game.width - ball.width

  def ballHitsCeiling = ball.yPosition > game.height - ball.width

  def flipHorizontalBallDirection() = ball = ball.copy(xVelocity = -ball.xVelocity)

  def flipVerticalBallDirection() = ball = ball.copy(yVelocity = -ball.yVelocity)

  def advanceBall(delta: Float, xVelocity: Float = ball.xVelocity, yVelocity: Float = ball.yVelocity) = {
      ball = ball.copy(
        yPosition = ball.yPosition + yVelocity * delta,
        xPosition = ball.xPosition + xVelocity * delta
      )
  }

  def advancePaddle(delta: Float) = {
    if (game.isLeftKeyPressed)
      paddle = paddle.copy(xPosition = paddle.xPosition - paddle.xVelocity * delta)
    if (game.isRightKeyPressed)
      paddle = paddle.copy(xPosition = paddle.xPosition + paddle.xVelocity * delta)
  }

  @tailrec
  private def fixStuckInPaddleOnTheWayDown(): Unit = {
    if (paddle.overlaps(ball.rectangle)) {
      advanceBall(1, 0, 1)
      fixStuckInPaddleOnTheWayDown()
    }
  }

  override def dispose() = {
    ballImage.dispose()
    paddleImage.dispose()
  }
}