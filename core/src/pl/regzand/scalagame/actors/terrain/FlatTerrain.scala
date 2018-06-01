package pl.regzand.scalagame.actors.terrain

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import pl.regzand.scalagame.core.Settings

class FlatTerrain(height: Float = Settings.worldHeight / 3) extends Terrain {

  private val renderer = new ShapeRenderer()

  override def heightAt(x: Float): Float = height

  override def minX: Float = 0

  override def maxX: Float = Settings.worldWidth

  override def draw(batch: Batch, parentAlpha: Float): Unit = {
    batch.end()

    renderer.setProjectionMatrix(batch.getProjectionMatrix)
    renderer.setTransformMatrix(batch.getTransformMatrix)
    renderer.translate(getX, getY, 0)

    renderer.begin(ShapeType.Filled)

    renderer.setColor(getColor)
    renderer.rect(0, 0, Settings.worldWidth, height)

    if(this.getDebug) {
      renderer.setColor(Color.RED)
      renderer.circle(0, 0, 10)
      renderer.circle(0, height, 10)
      renderer.circle(Settings.worldWidth, 0, 10)
      renderer.circle(Settings.worldWidth, height, 10)
    }

    renderer.end()

    batch.begin()
  }

}
