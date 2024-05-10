package extends_study;

public class Child1 extends Parents1 {
    String childVariable = "자식 클래스 변수";

    void childMethod() {
        System.out.println("자식 클래스의 메서드");
    }

    void accessParent() {
        // super 키워드를 사용하여 부모 클래스의 변수와 메서드에 접근할 수 있습니다.
        System.out.println(super.parentVariable); // 부모 클래스의 변수 출력
        super.parentMethod(); // 부모 클래스의 메서드 호출
    }
}
