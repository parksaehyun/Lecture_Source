package exam01;

public class Ex09 {
    public static void main(String[] args) {
        long num1 = 1000000000000L;
        double num2 = num1; // 정수 -> 실수 : 자동 형변환(묵시적 형변환)
        float num3 = num1; //  정수 -> 실수 : 자동 형변환(묵시적 형변환)

        double num4 = 0.0;
        for (int i = 0; i < 1000; i++) {
            num4 += 0.1;
        }

        System.out.println(num4);
    }
}
