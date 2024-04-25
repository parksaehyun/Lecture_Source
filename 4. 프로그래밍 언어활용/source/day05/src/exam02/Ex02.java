package exam02;

public class Ex02 {
    public static void main(String[] args) {
        C c = new C();
        A a = c;
        B b = c;



        System.out.printf("c 주소: %s%n", System.identityHashCode(c));
        System.out.printf("b 주소: %s%n", System.identityHashCode(b));
        System.out.printf("a 주소: %s%n", System.identityHashCode(a));

        System.out.println(a == c);
        System.out.println(b == c);
        // 동질 이상





    }
}
