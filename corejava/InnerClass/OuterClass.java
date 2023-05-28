package corejava.InnerClass;

public class OuterClass {
    int a = 100;
    class InnerClass{
        int b = 200;
        public void display(){
            System.out.println("The valus of a = "+ a + " value of B= "+b);
        }
    }
    public static void main(String[] args){
        //WAY 1
        OuterClass oc = new OuterClass();
        OuterClass.InnerClass ic = oc.new InnerClass();
        ic.display();
        //WAY 2
        OuterClass.InnerClass ab = new OuterClass().new InnerClass();
        ab.display();
    }
}

