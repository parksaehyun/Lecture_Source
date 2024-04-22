package exam02;

public abstract class Car {
    public abstract void start(); //추상메서드
    public abstract void press(); //추상메서드
    public abstract void turnoff(); //추상메서드

    public final void run() { //템플릿 메서드 패턴
        start();
        press();
        turnoff();
    }
}
