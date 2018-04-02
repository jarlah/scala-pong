package gdx.scala.demo

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.{BitmapFont, SpriteBatch}

class PongGame(val width: Int, val height: Int) extends Game with Dimensions {

  var batch: SpriteBatch = _
  var font: BitmapFont = _

  override def create(): Unit = {
    batch = new SpriteBatch
    font = new BitmapFont
    this.setScreen(new GameScreen(this))
  }
}
