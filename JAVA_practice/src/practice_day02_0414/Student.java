package practice_day02_0414;

public class Student {
    int id; // 학번

    String name; // 학생명

    String subject; // 전공과목

    void study () {
        System.out.printf("학번 : %d, 이름: %s, 전공과목: %s%n", id, name, subject);
    }
}
