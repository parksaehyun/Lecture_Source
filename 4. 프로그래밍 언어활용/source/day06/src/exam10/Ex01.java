package exam10;

public class Ex01 {
    public static void main(String[] args) {
        Outer outer = new Outer(); // 외부클래스 객체를 생성
        Outer.Inner inner = outer.new Inner(); //Outer.Inner : 내부클래스의 자료형 // 내부 클래스 객체 생성
        inner.method();
        System.out.println(Outer.Inner.num3);
    }
}