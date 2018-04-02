package gdx.scala.demo

import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.{Color, GL20, Texture}
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.bullet.Bullet
import com.badlogic.gdx.{Gdx, Screen}

class GameScreen(game: PongGame) extends Screen {
  Bullet.init()

  val ballWidth: Int = 16
  val ballHeight: Int = 16
  val ballSpeed: Int = 200
  val ballImage = new Texture("ball.png")
  val ball = new Rectangle(game.width / 2 - ballWidth / 2, (game.height * 0.7).asInstanceOf[Int], ballWidth, ballHeight)

  val paddleWidth: Int = 80
  val paddleHeight: Int = 16
  val paddleSpeed: Int = 100
  val paddleImage = new Texture("paddle.png")
  val paddle = new Rectangle(game.width / 2 - paddleWidth / 2, 20, paddleWidth, paddleHeight)

  var paused: Boolean = false
  var ballUp: Boolean = false
  var ballDown: Boolean = true

  def render(delta: Float) {
    Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    game.batch.begin()
    game.batch.setColor(Color.BLACK)
    game.batch.draw(ballImage, ball.x, ball.y)
    game.batch.draw(paddleImage, paddle.x, paddle.y)
    game.batch.end()
    if (!paused) {
      advancePaddle()
      if (hitsCeiling || paddle.overlaps(ball)) {
        flipVerticalBallDirection()
      }
      if (hitsFloor) {
        repositionBallAtTop()
      }
      advanceBall()
    }
  }

  def repositionBallAtTop(): Unit = ball.y = game.height - ballWidth

  def hitsFloor: Boolean = ball.y < 0

  def hitsCeiling: Boolean = ball.y > game.height - ballWidth

  def flipVerticalBallDirection(): Unit = {
    ballUp = !ballUp
    ballDown = !ballDown
  }

  def advancePaddle(): Unit = {
    if (Gdx.input.isKeyPressed(Keys.LEFT))
      paddle.x -= paddleSpeed * Gdx.graphics.getDeltaTime
    if (Gdx.input.isKeyPressed(Keys.RIGHT))
      paddle.x += paddleSpeed * Gdx.graphics.getDeltaTime
  }

  def advanceBall(): Unit = {
    if (ballDown)
      ball.y -= ballSpeed * Gdx.graphics.getDeltaTime
    if (ballUp)
      ball.y += ballSpeed * Gdx.graphics.getDeltaTime
  }

  def show(): Unit = {
  }

  def resize(width: Int, height: Int): Unit = {
  }

  def pause(): Unit = {
    paused = true
  }

  def resume(): Unit = {
    paused = false
  }

  def hide(): Unit = {
  }

  def dispose(): Unit = {
    ballImage.dispose()
    paddleImage.dispose()
  }
}