package pl.regzand.scalagame.actors

import com.badlogic.gdx.math.Vector2
import pl.regzand.scalagame.actors.terrain.{FlatTerrain, SplineTerrain, Terrain}

class TestLevel extends Level {

  override val terrain = new SplineTerrain(Array(
    new Vector2(0, 100),
    new Vector2(500, 500),
    new Vector2(800, 100),
    new Vector2(1200, 50),
    new Vector2(1700, 500),
    new Vector2(2150, 100),
    new Vector2(2500, 200)
  ), 100)

//  override val terrain = new FlatTerrain()

  override val castles = Array(
    new Castle {},
    new Castle {}
  )

  addActor(terrain)
  for(c <- castles)
    addActor(c)

}
