package information_hiding;

public class Ex01 {
    public static void main(String[] args) {
        Schedule s1 = new Schedule();
        s1.year = 2024;
        s1.month = 2;
        s1.day = 31; // 지양, 통제가 불가 하므로

        System.out.printf("%d 년  %d 월  %d 일 %n", s1.year, s1.month, s1.day);
    }
}
