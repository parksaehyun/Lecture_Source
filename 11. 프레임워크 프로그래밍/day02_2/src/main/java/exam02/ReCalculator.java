package exam02;

import exam01.Calculator;

public class ReCalculator implements Calculator {

    @Override
    public long factorial(long num) {
        if(num < 1L) {
            return 1L;
        }

        return num * factorial(num - 1L);
    }
}
