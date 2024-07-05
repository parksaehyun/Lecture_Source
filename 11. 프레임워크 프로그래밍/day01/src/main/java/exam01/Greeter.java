package exam01;

public class Greeter {
    public void hello(String name) {
        // 스프링 컨테이너가 관리하는 객체
        System.out.printf("%s님 안녕하세요", name);
    }
}
