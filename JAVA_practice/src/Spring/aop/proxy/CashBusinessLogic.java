package Spring.aop.proxy;

public class CashBusinessLogic implements Payment{
    @Override
    public void pay(int amount) {
        System.out.println(amount + "현금 결제"); // 비지니스 로직
    }
}
