package exam01;

public class ProxyCalculator implements Calculator {

    // ImplCalculator, ReCalculator 대신 수행해 주려면? 자료형은? 다형성

    private Calculator calculator;

    public ProxyCalculator(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public long factorial(long num) {

        long stime = System.nanoTime(); // 공통 기능

        try {
            // 다른 계산기의 핵심 기능을 대신 수행
            long result = calculator.factorial(num); // 다른 계산기의 factorial 연산을 대신 수행
            return result;
        } finally {
            long etime = System.nanoTime(); // 공통 기능
            System.out.printf("걸린 시간 : %d%n", etime - stime);
        }
    }
}
