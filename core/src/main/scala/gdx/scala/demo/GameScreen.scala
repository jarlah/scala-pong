package gdx.scala.demo

import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.{Color, GL20, Texture}
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.bullet.Bullet
import com.badlogic.gdx.{Gdx, Screen}

class GameScreen(game: PongGame,
                 ballWidth: Int = 16,
                 ballHeight: Int = 16,
                 ballSpeed: Int = 200,
                 paddleWidth: Int = 80,
                 paddleHeight: Int = 16,
                 paddleSpeed: Int = 100
                ) extends Screen {
  Bullet.init()

  private val ballImage = new Texture("ball.png")
  private val paddleImage = new Texture("paddle.png")
  private val paddle = new Rectangle(game.width / 2 - paddleWidth / 2, 20, paddleWidth, paddleHeight)
  private val ball = new Rectangle(game.width / 2 - ballWidth / 2, (game.height * 0.7).asInstanceOf[Int], ballWidth, ballHeight)

  private var ballUp: Boolean = false
  private var ballDown: Boolean = true

  def render(delta: Float) {
    Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    game.batch.begin()
    game.batch.setColor(Color.BLACK)
    game.batch.draw(ballImage, ball.x, ball.y)
    game.batch.draw(paddleImage, paddle.x, paddle.y)
    game.batch.end()
    advancePaddle()
    if (hitsCeiling || paddle.overlaps(ball)) {
      flipVerticalBallDirection()
    }
    if (hitsFloor) {
      repositionBallAtTop()
    }
    advanceBall()
  }

  private def repositionBallAtTop(): Unit = ball.y = game.height - ballWidth

  private def hitsFloor = ball.y < 0

  private def hitsCeiling = ball.y > game.height - ballWidth

  private def flipVerticalBallDirection(): Unit = {
    ballUp = !ballUp
    ballDown = !ballDown
  }

  private def advancePaddle(): Unit = {
    if (Gdx.input.isKeyPressed(Keys.LEFT))
      paddle.x -= paddleSpeed * Gdx.graphics.getDeltaTime
    if (Gdx.input.isKeyPressed(Keys.RIGHT))
      paddle.x += paddleSpeed * Gdx.graphics.getDeltaTime
  }

  private def advanceBall(): Unit = {
    if (ballDown)
      ball.y -= ballSpeed * Gdx.graphics.getDeltaTime
    if (ballUp)
      ball.y += ballSpeed * Gdx.graphics.getDeltaTime
  }

  override def show(): Unit = {

  }

  override def resize(width: Int, height: Int): Unit = {

  }

  override def pause(): Unit = {

  }

  override def resume(): Unit = {

  }

  override def hide(): Unit = {

  }

  override def dispose(): Unit = {
    ballImage.dispose()
    paddleImage.dispose()
  }
}