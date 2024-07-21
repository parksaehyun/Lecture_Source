package Spring.aop.proxy;

public class Store {
    Payment payment;

    public Store(Payment payment) { // 의존성
        this.payment = payment;
    }

    public void buySomthig(int amount){
        payment.pay(amount);
    }
}
