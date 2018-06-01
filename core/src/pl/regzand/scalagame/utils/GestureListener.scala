package pl.regzand.scalagame.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.{InputEvent, InputListener}


trait GestureListener extends InputListener {

  var prevX: Float = 0
  var prevY: Float = 0

  def pan(event: InputEvent, x: Float, y: Float, dx: Float, dy: Float)

  override def touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean = {
    prevX = x
    prevY = y
    true
  }

  override def touchDragged(event: InputEvent, x: Float, y: Float, pointer: Int): Unit = {
    val X = Gdx.input.getX(pointer)
    val Y = Gdx.input.getY(pointer)
    pan(event, X, Y, X - prevX, Y - prevY)
    prevX = X
    prevY = Y
  }


}
