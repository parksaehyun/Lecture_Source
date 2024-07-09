package exam01;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

public class Ex01 {
    @Test
    void test1() {
        Object obj = Proxy.newProxyInstance(
                Calculator.class.getClassLoader(),
                new Class[] {Calculator.class},
                new CalculatorHandler(new RecCalculator())
        );

        Calculator cal = (Calculator) obj;
        long result = cal.factorial(10L); // 바로 호출되는것이 아니라 invoke()를 거쳐서 호출됨
        System.out.println(result);
    }
}
