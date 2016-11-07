/**
 * public abstract class P5Shape
 * The abstract class for every shape
 */
public abstract class P5Shape 
{
  /**
   * public abstract String toString()
   * An abstract method. Used to return the information
   * of the object in a string.
   */
  public abstract String toString();
  /**
   * public String getName()
   * Return the name of this class in a string.
   */
  public String getName()
  {
    return( this.getClass().getName() + ":");
  }
  /**
   * public abstract void setDim(double l)
   * public abstract void setDim(double l, double h)
   * public abstract void setDim(double l, double h, double d)
   * Assign the dimension of the shape.
   */
  public void setDim(double a){}
  public void setDim(double a, double b){}
  public void setDim(double a, double b, double c){}
  /**
   * public abstract double area()
   * Calculate and return the area(surface area) of a shape.
   */
  public double area()
  {
    return 0;
  }
  /**
   * public abstract double volume()
   * Calculate and return the volume of a shape.
   */
  public double volume()
  {
    return 0;
  }
}
