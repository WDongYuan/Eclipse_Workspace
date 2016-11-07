/**
 * public class P5EquiTriangle extends P5Shape
 * Subclass of P5Shape. Can return the area of
 * the EquiTriangle.
 */
public class P5EquiTriangle extends P5Shape
{
  protected double side;//The side of the EquiTriangle
  protected double area;//The area of the EquiTriangle
  /**
   * public P5EquiTriangle()
   * Constructor(s) to initialize data to one.
   */
  public P5EquiTriangle()
  {
    side = 1;
  }
  
  /**
   * public P5EquiTriangle(double s)
   * An overloaded constructor receiving one
   * parameter type double, initializing side
   * to this parameter.
   */
  public P5EquiTriangle(double s)
  {
    side = s;
  }
  
  /**
   * public String toString()
   * Return a type "String" to format dimensions
   * for printing to be called prt().
   */
  public String toString()
  {
    return (getName()+" side " + side );
  }
  
  /**
   * public void setDim(double s)
   * Assign the data member with the parameter.
   */
  public void setDim(double s)
  {
    side = s;
  }
  
  /**
   * public double area()
   * Calculate and return the area of the equitriangle.
   */
  public double area()
  {
    area = side*side*Math.sqrt(3)/4;
    return area;
  }
}
