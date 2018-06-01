package pl.regzand.scalagame.stages

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.{InputEvent, InputListener, Stage}
import com.badlogic.gdx.utils.viewport.ExtendViewport
import pl.regzand.scalagame.actors.TestLevel
import pl.regzand.scalagame.core.Settings
import pl.regzand.scalagame.utils
import pl.regzand.scalagame.utils.GestureListener

class GameStage extends Stage(new ExtendViewport(1, Settings.worldHeight)) {

  // handling screen panning
  this.addListener(new GestureListener{
    override def pan(event: InputEvent, x: Float, y: Float, dx: Float, dy: Float): Unit = {
      moveCamera(-dx)

      Gdx.app.debug("GameStage", s"Camera moved by $dx to ${getCamera.position}")
    }
  })

  // handling special input
  this.addCaptureListener(new InputListener{
    override def keyTyped(event: InputEvent, character: Char): Boolean = event.getKeyCode match {
      case Settings.keyDebug => setDebugAll(!isDebugAll); true
      case _ => false
    }
  })

  def moveCamera(dx: Float): Unit = getCamera.position.set(utils.minMax(getWidth / 2, getCamera.position.x + dx, Settings.worldWidth - getWidth / 2), Settings.worldHeight / 2, 0)

  def resize(width: Int, height: Int): Unit = {
    getViewport.update(width, height, false)
    moveCamera(0)

    Gdx.app.debug("Screen", s"Screen resized to ($width x $height) => viewport (${getWidth} x ${getHeight}) camera at ${getCamera.position}")
  }

  // FIXME: ONLY FOR TESTS
  val lvl = new TestLevel()
  addActor(lvl)

}
