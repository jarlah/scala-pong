package gdx.scala.demo

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.{Color, GL20}

trait AbstractGL20 {
  def clearScreen(color: Color): Unit = {
    Gdx.gl.glClearColor(color.r, color.g, color.b, color.a)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
  }
}