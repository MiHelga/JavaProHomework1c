
public class FooClass {
    @Save
    private String name;
    @Save
    private int age;
    @Save
    private boolean isMale;
    @Save
    private double weight;

    public FooClass(){
        super();
    }

    public FooClass(String name, int age, boolean isMale, double weight){
        this.name = name;
        this.age = age;
        this.isMale = isMale;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString (){
        return "FooClass: \r\n name = " + name + "\r\n age = " + age + "\r\n isMale = " +isMale + "\r\n weight = " + weight;
    }
}
