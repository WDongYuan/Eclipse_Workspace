/**Assignment 5                 P5.java                Due August 26, 2015
 * This program is to calculate the area of an equilateral triangle (3 
 * equal length sides), surface area of a tetrahedron (4 equilateral 
 * triangles), volume of a tetrahedron, area of a rectangle, surface area
 * of a box, and volume of a box, using an abstract class, classes, 
 * constructors, arrays, and an inheritance hierarchy.
 **/

import java.util.Scanner;
/**
* public class P5
* instantiate 4 different shape and calculate the area of the equilateral
* triangle and rectangle and the surface area and volume of the
* tetrahedron and the box.
*/
public class P5 {
  private static final int SHAPES = 4; // 4 Shape objects
  private static P5Shape[] a; // Array of shapes

  public static void main(String[] args) {
    char choice; // Repeat program
    double width, height, depth; // Input Box/Rect
    double side; // Input Tetrahedron/Triangle
    String inputStr = null; // Input string
    Scanner scan = new Scanner(System.in);
    a = new P5Shape[SHAPES]; // Allocate 4 Shape ref
    a[0] = new P5EquiTriangle(); // Instantiate objects
    a[1] = new P5Tetrahedron(2.2);
    a[2] = new P5Rectangle();
    a[3] = new P5Box(3.3, 4.4, 5.5);
    prt(); // Display objects
    do {
      System.out.print("\nEnter TETRAHEDRON(side): ");
      side = scan.nextDouble();
      System.out.print("Enter BOX (width height depth): ");
      width = scan.nextDouble();
      height = scan.nextDouble();
      depth = scan.nextDouble();
      a[0].setDim(side); // Reassign
      a[1].setDim(side);
      a[2].setDim(width, height);
      a[3].setDim(width, height, depth);
      prt(); // Display objects
      System.out.print("\nWant to compute area (y/n)? ");
      inputStr = scan.next(); // Read, assign to string
      choice = inputStr.charAt(0); // Assign 1st character
    } while (choice != 'n' && choice != 'N'); // Loop until n or N
  }
/**
 * public static void prt()
 * Call the toString(), area(), and volume() methods to print the 
 * information of the shape in the array a[].
 */
  public static void prt() {
    int i;
    for (i = 0; i < SHAPES; ++i)
      if (i % 2 == 0) {
        System.out.print(a[i] + " has an area: ");
        System.out.printf("\t%.2f \n", a[i].area());
      } else {
        System.out.print(a[i] + "\t has a surface area: ");
        System.out.printf("\t%.2f \n", a[i].area());
        System.out.printf("\t\t\t\t and volume: %.2f\n", a[i].volume());
      }
  }
}
