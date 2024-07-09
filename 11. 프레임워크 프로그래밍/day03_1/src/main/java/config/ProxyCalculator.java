package config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect // 공통기능을 정의한 클래스
public class ProxyCalculator {

    @Pointcut("execution(* exam01..*(..))") // exam01쪽에 있는 모든 클래스와 메서드
    public void publicTarget() {}

    @Before("publicTarget()")
    public void before(JoinPoint joinPoint) {
        System.out.println("Before..");
    }

    @After("publicTarget()")
    public void After(JoinPoint joinPoint) {
        System.out.println("After..");
    }

    @AfterReturning(value = "publicTarget()", returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) {
        System.out.println("AfterReturning.." + returnValue);
    }

    @AfterThrowing(value = "publicTarget()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        System.out.println("afterThrowing..");
        e.printStackTrace();
    }

    @Around("publicTarget()") // @Around : 메서드 호출 전, 호출 후 공통 기능
    // publicTarget() 정해져 있는거라 이렇게 넣는 이유에 의문가지지 말기
    public Object process(ProceedingJoinPoint joinPoint) throws Throwable {
        // 반환값은 정해져 있지만 메서드명은 내가 지정할 수 있음
        // 매개변수도 정해져 있음
        // ProceedingJoinPoint joinPoint : 핵심기능을 대신 수행해줄 메서드
        /*
        Signature sig = joinPoint.getSignature(); // 호출된 메서드 정의 정보
        System.out.println(sig.toLongString());

        Object[] args = joinPoint.getArgs(); // 인수 정보
        System.out.println(Arrays.toString(args));

        Object obj = joinPoint.getTarget(); // 호출한 메서드를 가지고 있는 객체
        System.out.println(obj.getClass());
         */

        long stime = System.nanoTime(); //  공통기능
        boolean re = false;
        if (!re) {
            throw new RuntimeException("예외 테스트...");
        }
        try {

            Object result = joinPoint.proceed(); // 핵심기능 대신 실행 - factorial

            return result;
        } finally {
            long etime = System.nanoTime(); // 공통기능
            System.out.printf("걸린시간:%d%n", etime - stime);
        }
    }
}
