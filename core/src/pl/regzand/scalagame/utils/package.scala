package pl.regzand.scalagame

package object utils {

  def minMax(min: Float, value: Float, max: Float): Float = if(value < min) min else if(value > max) max else value

}
