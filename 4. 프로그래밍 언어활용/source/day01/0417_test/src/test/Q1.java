package test;

public class Q1 {
    // 변수 두 개를 선언해서 20과 3.0을 대입하고 두 변수의 사칙연산 결과를 정수로 출력해 보시오
    public static void main(String[] args) {
        int num1 = 20;
        double num2 = 3.0;
        int result1 = (int)(num1 + num2);
        int result2 = (int)(num1 - num2);
        int result3 = (int)(num1 * num2);
        int result4 = (int)(num1 / num2);

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
    }
}
