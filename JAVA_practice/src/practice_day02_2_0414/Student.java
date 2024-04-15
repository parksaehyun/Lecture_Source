package practice_day02_2_0414;

public class Student {
    int id; // 학번

    String name; // 학생명

    String subject; // 전공과목
    public Student(){
    }
    public Student(int _id, String _name, String _subject) {
        // 반환값 정의x (반환값은 주소값으로 정해져 있기 때문에 굳이 정의하지x)
        // return x (무조건 반환값은 객체의 주소값)
        // 객체 생성 이후에 해야될 작업들만 명시할 수 있음
        // 멤버 변수의 초기화 작업을 주로 진행
        id = _id;
        name = _name;
        subject = _subject;
    }

    void showInfo() {
        System.out.printf("학번 : %d, 이름: %s, 전공과목: %s%n", id, name, subject);
    }
}
