package practice_day02_2_0414;

public class Ex02 {
    public static void main(String[] args) {
        Student s1 = new Student( 1000, "학생1", "과목1");
        s1.showInfo();

        Student s2 = new Student(1001, "학생2", "과목2");
        s2.showInfo();
        System.out.println(s1 == s2);
    }
}
