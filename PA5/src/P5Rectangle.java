/**
 * public class P5Rectangle extends P5Shape
 * Subclass of P5Shape. Can return the area of
 * the rectangle.
 */
public class P5Rectangle extends P5Shape
{
  protected double width; //The width of the rectangle
  protected double height;//The height of the rectangle
  protected double area;  //The area of the rectangle
  /**
   * public P5Rectangle()
   * No argument constructor, initializing to one.
   */
  public P5Rectangle()
  {
    width = 1;
    height = 1;
  }
  
  /**
   * P5Rectangle(double w, double h)
   * an overloaded constructor receiving two 
   * parameters type double, initializing data
   * to these parameters.
   */
  public P5Rectangle(double w, double h)
  {
    width = w;
    height = h;
  }
  
  /**
   * public String toString()
   * Return a type "String" to format dimensions
   * for printing to be called prt().
   */
  public String toString()
  {
    return (getName()+"    " + width + " x " + height);
  }
  
  /**
   * public void setDim(double w)
   * Assign the data members with the ONE
   * parameter to implement from P5Shape 
   * (width is equal to height)
   */
  public void setDim(double w)
  {
    width = w;
    height = w;
  }
  
  /**
   * public void setDim(double w, double h)
   * Assign the data members with TWO parameters.
   */
  public void setDim(double w, double h)
  {
    width = w;
    height = h;
  }
  /**
   * public double area()
   * Calculate and return the area of the rectangle. 
   */
  
  public double area()
  {
    area = width * height;
    return area;
  }
}
