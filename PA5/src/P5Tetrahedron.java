/**
 * public class P5Tetrahedron extends P5EquiTriangle
 * Subclass of P5EquiTriangle. Can return the area 
 * and the volume of the tetrahedron.
 */
public class P5Tetrahedron extends P5EquiTriangle
{
  protected double volume;//The volume of the tetrahedron
  
  /**
   * public P5Tetrahedron()
   * No argument constructor, initializing data to one.
   */
  public P5Tetrahedron()
  {
    super();
  }
  
  /**
   * public P5Tetrahedron(double s)
   * An overloaded constructor receiving one
   * parameter type double,initializing side to 
   * this parameter.
   */
  public P5Tetrahedron(double s)
  {
    super(s);
  }
  
  /**
   * public String toString()
   * Return a type "String" to format dimensions
   * for printing to be called in prt()
   */
  public String toString()
  {
    return (getName()+"  " + super.side 
        + " x " + super.side + " x "+super.side);
  }
  
  /**
  * public void setDim(double s)
  * Assign the data member with the parameter.
  */
  public void setDim(double s)
  {
    super.setDim(s);
  }
  
  /**
   * public double area()
   * Calculate and return the area of the tetrahedron.
   */
  public double area()
  {
    area = super.side*super.side*Math.sqrt(3);
    return area;
  }
  
  /**
   * public double volume()
   * Calculate and return the volume of the tetrahedron.
   */
  public double volume()
  {
    volume = Math.sqrt(2)*super.side*super.side*super.side/12;
    return volume;
  }
}
