package exam03;

public class Ex01 {
    public static void main(String[] args) {
        Integer num1 = new Integer(10);
        double num2 = num1.doubleValue(); // 기본형에 기능부여

        String str = "1000";
        int num3 = Integer.parseInt(str);
        System.out.println(str+100);
        System.out.println(num3+100);
    }
}
