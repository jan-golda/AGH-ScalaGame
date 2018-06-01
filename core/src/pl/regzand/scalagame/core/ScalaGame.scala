package pl.regzand.scalagame.core

import com.badlogic.gdx.{Application, Game, Gdx}
import pl.regzand.scalagame.screens.{GameScreen, LoadingScreen, MenuScreen, SettingsScreen}

class ScalaGame extends Game {

  // screens
  lazy val loadingScreen = new LoadingScreen(this)
  lazy val menuScreen = new MenuScreen(this)
  lazy val settingsScreen = new SettingsScreen(this)

  override def create(): Unit = {

    // set instance
    if(ScalaGame._instance != null)
      throw new IllegalStateException("ScalaGame cannot be created multiple times, only one instance is allowed")
    ScalaGame._instance = this

    // set logging lvl
    Gdx.app.setLogLevel(Application.LOG_DEBUG) // FIXME: change to info

    // set first screen
    this.setScreen(new GameScreen(this)) // FIXME: change to loading screen

  }

}

object ScalaGame {

  private var _instance: ScalaGame = null

  def instance: ScalaGame = _instance

}
