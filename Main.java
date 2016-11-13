
public class Main {
    public static void main(String[] args){
        FooClass foo = new FooClass("Lara", 28, false, 40);
        Serializer.serialize(foo, "c:/serializationTest.txt");

        FooClass foo2 = new FooClass();
        System.out.println("BEFORE");
        System.out.println(foo2);

        Serializer.deserialize(foo2, "c:/serializationTest.txt" );
        System.out.println("AFTER");
        System.out.println(foo2);

    }
}
