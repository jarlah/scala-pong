package com.github.jarlah.pong

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.{Color, GL20}

trait AbstractGdx {
  def clearScreen(color: Color): Unit = {
    Gdx.gl.glClearColor(color.r, color.g, color.b, color.a)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
  }
  def isKeyPressed(key: Int): Boolean = Gdx.input.isKeyPressed(key)
  def isLeftKeyPressed: Boolean = isKeyPressed(Keys.LEFT)
  def isRightKeyPressed: Boolean = isKeyPressed(Keys.RIGHT)
  def isUpKeyPressed: Boolean = isKeyPressed(Keys.UP)
  def isDownKeyPressed: Boolean = isKeyPressed(Keys.DOWN)
}