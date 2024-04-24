package exam03;

public class Ex03 {
    public static void main(String[] args) {
        //자바 9버전부터는 자원낭비 방지 위해 integer사용 지양(굳이 객체 여러개 만들지 말자), valueOf 사용 권장
        Integer num1 = Integer.valueOf(10);
        Integer num2 = Integer.valueOf(10);

        //Integer num1 = Integer.valueOf(10000); // 바이트 범위를 넘어가면 주소값이 달라짐
        //Integer num2 = Integer.valueOf(10000);

        System.out.printf("num1 주소: %d%n", System.identityHashCode(num1));
        System.out.printf("num2 주소: %d%n", System.identityHashCode(num2));
    }
}
