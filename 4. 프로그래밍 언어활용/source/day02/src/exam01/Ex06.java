package exam01;

public class Ex06 {
    public static void main(String[] args) {
        Student s1 = new Student(1000, "이이름", "자바");

        s1.study();

        Student s2 = new Student(1001, "김이름", "리액트");
        s2.study();

        System.out.println(s1 == s2);


    }
}
