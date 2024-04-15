package practice_day02_2_0414;

public class Ex03 {
    public static void main(String[] args) {
        int result1 = add(10,20);
        System.out.println(result1);

        int result2 = add(10, 20, 30);
        System.out.println(result2);

        double result3 = add(10.0, 20.0);
        System.out.println(result3);
    }

    static  int add(int num1, int num2) {
        int result = num1 + num2;
        return result;
    }

    static  int add(int num1, int num2, int num3) {
        int result = num1 + num2 + num3;
        return result;
    }

    static  double add(double num1, double num2) {
        double result = num1 + num2;
        return result;
    }
}
