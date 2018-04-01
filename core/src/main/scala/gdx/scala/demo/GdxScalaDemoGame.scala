package gdx.scala.demo

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.{Color, GL20, OrthographicCamera, Texture}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.bullet.Bullet

class GdxScalaDemoGame extends ApplicationAdapter {
  var batch: SpriteBatch = _
  var ballImage: Texture = _
  var paddleImage: Texture = _
  var camera: OrthographicCamera = _
  var paddle: Rectangle = _
  var ball: Rectangle = _

  override def create() {
    Bullet.init()
    camera = new OrthographicCamera
    camera.setToOrtho(false, 800, 480)
    batch = new SpriteBatch
    ballImage = new Texture("ball.png")
    paddleImage = new Texture("paddle.png")
    paddle = new Rectangle(800 / 2 - 80 / 2, 20, 80, 16)
    ball = new Rectangle(800 / 2 - 16 / 2, 300, 16, 16)
  }

  override def render() {
    Gdx.gl.glClearColor(135 / 255f, 206 / 255f, 235 / 255f, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    batch.begin()
    batch.setColor(Color.BLACK)
    batch.draw(ballImage, ball.x, ball.y)
    batch.draw(paddleImage, paddle.x, paddle.y)
    batch.end()
    if(Gdx.input.isKeyPressed(Keys.LEFT))
      paddle.x -= 200 * Gdx.graphics.getDeltaTime
    if(Gdx.input.isKeyPressed(Keys.RIGHT))
      paddle.x += 200 * Gdx.graphics.getDeltaTime
  }
}