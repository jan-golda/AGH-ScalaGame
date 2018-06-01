package pl.regzand.scalagame.actors

import com.badlogic.gdx.scenes.scene2d.Group
import pl.regzand.scalagame.actors.terrain.Terrain

abstract class Level extends Group {

  val terrain: Terrain
  val castles: Array[Castle]

}
