/**
 * public class P5Box extends P5Rectangle
 * Subclass of P5Rectangle. Can return the area 
 * and the volume of the box.
 */
public class P5Box extends P5Rectangle
{
  protected double depth; //The depth of the box
  protected double volume;//The volume of the box
  
  /**
   * public P5Box()
   * No argument constructor, initializing to zero.
   */
  public P5Box()
  {
    super.width = 0;
    super.height = 0;
    depth = 0;
  }
  
  /**
   * public P5Box(double w, double h, double d)
   * An overloaded constructor receiving three
   * parameters type double,initializing data to 
   * these parameters.
   */
  public P5Box(double w, double h,double d)
  {
    super(w,h);
    depth = d;
  }
  
  /**
   * public String toString()
   * Return a type "String" to format dimensions
   * for printing to be called in prt()
   */
  public String toString()
  {
    return (getName() +"          "+ super.width 
        + " x " + super.height + " x " + depth);
  }
  
  /**
   * public void setDim(double w, double h, double d)
   * Assign the data members with the parameters.
   */
  public void setDim(double w, double h, double d)
  {
    super.setDim(w, h);
    depth = d;
  }
  
  /**
   * public double area()
   * Calculate and return the surface area of the box.
   */
  public double area()
  {
    area = 2*super.area()+2*super.width*depth+2*super.height*depth;
    return area;
  }
  
  /**
   * public double volume()
   * Calculate and return the volume of the box.
   */
  public double volume()
  {
    volume = super.area()*depth;
    return volume;
  }
}
