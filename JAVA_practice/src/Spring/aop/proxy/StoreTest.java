package Spring.aop.proxy;

public class StoreTest {

    public static void main(String[] args) {
        Payment cashProxy = new CashProxy(); // 프록시
        Store store = new Store(cashProxy);
        store.buySomthig(100);
        // store의 buySomethig() -> cashProxy.pay() 실행
        // 실제로는 CashBusinessLogic클래스가 아니라 프록시가 실행됨
        // (스프링에서 내부적으로 이러한 작업을 해주고 있다)
        // AOP -> 프록시 패턴 : 원래의 코드를 유지하면서 안보이지만 다른 객체(프록시) 실행
    }
}
