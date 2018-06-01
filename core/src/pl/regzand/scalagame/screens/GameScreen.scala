package pl.regzand.scalagame.screens

import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.{Gdx, InputMultiplexer, Screen}
import pl.regzand.scalagame.core.ScalaGame
import pl.regzand.scalagame.stages.{GameStage, GuiStage}


class GameScreen(val game: ScalaGame) extends Screen  {

  // stages
  val gameStage = new GameStage
  val guiStage = new GuiStage

  // setup input processor
  val multiplexer = new InputMultiplexer
  multiplexer.addProcessor(guiStage)
  multiplexer.addProcessor(gameStage)
  Gdx.input.setInputProcessor(multiplexer)

  override def render(delta: Float): Unit = {
    Gdx.gl.glClearColor(.9f, .9f, .9f, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT)

    gameStage.act(delta)
    guiStage.act(delta)

    gameStage.draw()
    guiStage.draw()
  }

  override def resize(width: Int, height: Int): Unit = {
    gameStage.resize(width, height)
    guiStage.resize(width, height)
  }

  override def dispose(): Unit = {
    gameStage.dispose()
    guiStage.dispose()
  }

  override def show(): Unit = {}

  override def hide(): Unit = {}

  override def resume(): Unit = {}

  override def pause(): Unit = {}

}
