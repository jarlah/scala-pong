package gdx.scala.demo

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.{Color, Texture}

class GameScreen(game: PongGame) extends AbstractScreen {

  val ballImage = new Texture("ball.png")
  var ball: Ball = Ball.create(game)

  val paddleImage = new Texture("paddle.png")
  var paddle: Paddle = Paddle.create(game)

  // Mutable state variables
  var ballUp: Boolean = false
  var ballDown: Boolean = true
  var ballLeft: Boolean = false
  var ballRight: Boolean = false

  def render(delta: Float) {
    game.clearScreen(Color.DARK_GRAY)
    game.batch.begin()
    game.batch.setColor(Color.BLACK)
    game.batch.draw(ballImage, ball.x, ball.y)
    game.batch.draw(paddleImage, paddle.x, paddle.y)
    game.batch.end()
    if (!paused) {
      advancePaddle()
      if (ballHitsCeiling || paddle.overlaps(ball.rectangle)) {
        flipVerticalBallDirection()
      }
      if (ballHitsFloor) {
        repositionBallAtTop()
      }
      advanceBall()
    }
  }

  def repositionBallAtTop(): Unit = ball = ball.copy(y = game.height - ball.width)

  def ballHitsFloor: Boolean = ball.y < 0

  def ballHitsCeiling: Boolean = ball.y > game.height - ball.width

  def flipVerticalBallDirection(): Unit = {
    ballUp = !ballUp
    ballDown = !ballDown
  }

  def advancePaddle(): Unit = {
    if (Gdx.input.isKeyPressed(Keys.LEFT))
      paddle = paddle.copy(x = paddle.x - paddle.speed * Gdx.graphics.getDeltaTime)
    if (Gdx.input.isKeyPressed(Keys.RIGHT))
      paddle = paddle.copy(x = paddle.x + paddle.speed * Gdx.graphics.getDeltaTime)
  }

  def advanceBall(): Unit = {
    if (ballDown)
      ball = ball.copy(y = ball.y - ball.speed * Gdx.graphics.getDeltaTime)
    if (ballUp)
      ball = ball.copy(y = ball.y + ball.speed * Gdx.graphics.getDeltaTime)
  }

  override def dispose(): Unit = {
    ballImage.dispose()
    paddleImage.dispose()
  }
}