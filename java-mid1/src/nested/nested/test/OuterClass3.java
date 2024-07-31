package nested.nested.test;

public class OuterClass3 {


    public void myMethod(){

        class LocalClass {
            public void hello(){
                System.out.println("LcoalClass.hello");
            }
        }
        LocalClass obj = new LocalClass();
        obj.hello();
    }


}
