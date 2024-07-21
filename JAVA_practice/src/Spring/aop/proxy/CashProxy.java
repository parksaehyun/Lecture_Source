package Spring.aop.proxy;

// 프록시
public class CashProxy implements Payment{
    // 비지니스 로직클래스 객체 생성
    Payment cashBusinessLogic = new CashBusinessLogic();

    @Override
    public void pay(int amount) {
        StopWatch stopWatch = new StopWatch(); // 성능체크 클래스
        //stopWatch.startTime; // 공통기능
        System.out.println("시작시간");

        cashBusinessLogic.pay(amount); // 비지니스 로직 (Cash 클래스의 메서드)

        //stopWatch.stopTime; // 공통기능
        System.out.println("끝난시간");
        System.out.println("시작시간 - 끝난시간");
    }

    private class StopWatch { // 성능체크 클래스
        private long startTime;
        private long stopTime;
    }
}
