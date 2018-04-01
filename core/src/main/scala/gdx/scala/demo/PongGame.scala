package gdx.scala.demo

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.{Color, GL20, OrthographicCamera, Texture}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.bullet.Bullet

class PongGame(width: Int,
               height: Int,
               ballWidth: Int = 16,
               ballHeight: Int = 16,
               ballSpeed: Int = 200,
               paddleWidth: Int = 80,
               paddleHeight: Int = 16,
               paddleSpeed: Int = 100
              ) extends ApplicationAdapter {

  private var batch: SpriteBatch = _
  private var ballImage: Texture = _
  private var paddleImage: Texture = _
  private var camera: OrthographicCamera = _
  private var paddle: Rectangle = _
  private var ball: Rectangle = _
  private var ballUp: Boolean = false
  private var ballDown: Boolean = false

  override def create() {
    Bullet.init()
    camera = new OrthographicCamera
    camera.setToOrtho(false, width, height)
    batch = new SpriteBatch
    ballImage = new Texture("ball.png")
    paddleImage = new Texture("paddle.png")
    paddle = new Rectangle(width / 2 - paddleWidth / 2, 20, paddleWidth, paddleHeight)
    ball = new Rectangle(width / 2 - ballWidth / 2, (height * 0.7).asInstanceOf[Int], ballWidth, ballHeight)
    ballDown = true
  }

  override def render() {
    Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    batch.begin()
    batch.setColor(Color.BLACK)
    batch.draw(ballImage, ball.x, ball.y)
    batch.draw(paddleImage, paddle.x, paddle.y)
    batch.end()
    advancePaddle()
    if (hitsCeiling || paddle.overlaps(ball)) {
      flipBallDirection()
    }
    if (hitsFloor) {
      ball.y = height - ballWidth
    }
    advanceBall()
  }

  private def hitsFloor = ball.y < 0

  private def hitsCeiling = ball.y > height - ballWidth

  private def flipBallDirection(): Unit = {
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
}