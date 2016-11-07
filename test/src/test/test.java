package test;
import java.util.*;
public class test {
    static public int count = 0;
    
    public static void main(String[] args){
      Dog dd = new Dog("Puppy");
      ShowAnimal(dd);
    }
    public static void ShowAnimal(Animal an){
      System.out.println(an.name);
    }
}
class Animal{
  public String name;
  public Animal(String an_name){
    name = an_name;
  }
}
//what about the constructor after inheritance
class Dog extends Animal{
  public Dog(String dog_name){
    super(dog_name);
  }
  public void Bite(){
    System.out.println("The dog can bite you");
  }
}