package extends_study;

public class SuperExample {
    public static void main(String[] args) {
        Child1 child = new Child1();
        child.accessParent(); // 자식 클래스에서 부모 클래스 멤버에 접근하는 메서드 호출
    }
}
