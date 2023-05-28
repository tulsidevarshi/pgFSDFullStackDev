package corejava.InnerClass;

public class AnonymousInnerClassEx {
    public static void main(String[] args){
        Bike b = new Bike(){
            // This is the class not having any name hence called anonymous calss
            void changeGear(){
                System.out.println("Your Bike Gear has been changed!!!");
            }
        };
        b.changeGear();
    }
}
abstract class Bike{
    abstract void changeGear();
}
