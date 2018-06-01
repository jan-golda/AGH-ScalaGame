package pl.regzand.scalagame.actors.terrain

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor

abstract class Terrain extends Actor {

  // default terrain color is back
  setColor(Color.BLACK)

  def heightAt(x: Float): Float
  def minX: Float
  def maxX: Float

}
