package pl.regzand.scalagame.actors.terrain

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType
import com.badlogic.gdx.math.{CatmullRomSpline, EarClippingTriangulator, Vector2}

class SplineTerrain(points: Array[Vector2], segments: Int) extends Terrain {

  private val renderer = new ShapeRenderer()

  private val curve = new CatmullRomSpline[Vector2](points(0) +: points :+ points.last, false)

  private val vertices = {
    val vertices = new Array[Float](2 * (segments+3))
    val v = new Vector2()

    for(i <- 0 to segments){
      curve.valueAt(v, i / segments.toFloat)
      vertices(2*i+0) = v.x
      vertices(2*i+1) = v.y

      Gdx.app.debug("FFS", s"${i / segments.toFloat} -> (${v.x}, ${v.y})")
    }

    vertices(2*(segments+1)+0) = points.last.x
    vertices(2*(segments+1)+1) = 0
    vertices(2*(segments+2)+0) = points(0).x
    vertices(2*(segments+2)+1) = 0

    vertices
  }

  private val triangles = new EarClippingTriangulator().computeTriangles(vertices).items

  Gdx.app.debug("Triangles", triangles.length.toString)

  override def draw(batch: Batch, parentAlpha: Float): Unit = {
    batch.end()

    renderer.setProjectionMatrix(batch.getProjectionMatrix)
    renderer.setTransformMatrix(batch.getTransformMatrix)
    renderer.translate(getX, getY, 0)

    renderer.begin(ShapeType.Filled)

    renderer.setColor(this.getColor)
    for(i <- triangles.indices by 3)
      renderer.triangle(vertices(2*triangles(i)), vertices(2*triangles(i)+1), vertices(2*triangles(i+1)), vertices(2*triangles(i+1)+1), vertices(2*triangles(i+2)), vertices(2*triangles(i+2)+1))

    if(this.getDebug) {
      renderer.setColor(Color.RED)
      for (p <- points)
        renderer.circle(p.x, p.y, 10)
      for (i <- vertices.indices by 2)
        renderer.circle(vertices(i), vertices(i+1), 5)
    }

    renderer.end()

    batch.begin()
  }

  override def heightAt(x: Float): Float = {
    if(x < minX) return 0
    for(i <- 2 until vertices.length by 2) {
      if (vertices(i) > x) {
        val x1 = vertices(i-2)
        val y1 = vertices(i-1)
        val x2 = vertices(i+0)
        val y2 = vertices(i+1)
        return y1 + (y2-y1)/(x2-x1)*(x-x1)
      }
    }
    return 0
  }

  override def minX: Float = points(0).x

  override def maxX: Float = points.last.x
}

