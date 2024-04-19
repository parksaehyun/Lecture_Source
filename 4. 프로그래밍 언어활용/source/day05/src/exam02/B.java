package exam02;

public class B  extends A { //B가 A를 상속
    int numB = 20;

    public B () {
        super(); // A 클래스에 정의된 A();
        System.out.println("B 생성자!");
    }
}
