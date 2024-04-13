package exam01;

public class Ex13 {
    public static void main(String[] args) {
        int num1 = 10;
        double num2 = 2.5;

        double num3 = num1 * num2;
        // 연산은 무족건 같은 자료형 끼리만 가능
        // int num1 -> double  (double이 int로 바뀌면 소수점이하가 날라가니까 비효율 적임ㅇㅇ)
        // 가장 손해 적고 효율적인걸로 변환
    }
}
