package test_0501.Q09;

public class MyDog {
    String name;
    String type;

    public MyDog(String name, String type){
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return "MyDog{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
