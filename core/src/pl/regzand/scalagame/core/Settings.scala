package pl.regzand.scalagame.core

import com.badlogic.gdx.{Gdx, Input, Preferences}

object Settings {

  val appName = "ScalaGame"

  lazy val preferences: Preferences = Gdx.app.getPreferences(appName)

  def flush(): Unit = preferences.flush()

  /**
    * Indicates if audio is muted
    * @return if audio is muted; false by default
    */
  def muted: Boolean = preferences.getBoolean("audio-muted", false)
  def muted_= (x: Boolean): Unit = preferences.putBoolean("audio-muted", x)


  /**
    * Returns volume of sounds
    * @return float in range [0, 1]; 1 by default
    */
  def soundsVolume: Float = if (muted) 0f else preferences.getFloat("audio-sounds-volume", 1f)
  def soundsVolume_= (x: Float): Unit = preferences.putFloat("audio-sounds-volume", x)


  /**
    * Returns volume of music
    * @return float in range [0, 1]; 1 by default
    */
  def musicVolume: Float = if (muted) 0f else preferences.getFloat("audio-music-volume", 1f)
  def musicVolume_= (x: Float): Unit = preferences.putFloat("audio-music-volume", x)

  /**
    * Returns game world height
    * @return game world height
    */
  def worldHeight = 1000

  /**
    * Returns game world width
    * @return game world width
    */
  def worldWidth = 2500

  /**
    * Returns code of key that toggles debug mode
    * @return key code
    */
  val keyDebug = Input.Keys.F3

}
