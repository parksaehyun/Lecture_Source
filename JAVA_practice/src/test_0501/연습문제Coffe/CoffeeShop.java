package test_0501.연습문제Coffe;

public interface CoffeeShop {
    CoffeeShop enter(Person person);
    CoffeeShop order();
    void exit();
    int getTotalSalePrice();
    String getName();

    public static void showSaleSummary(CoffeBean shop) {
        System.out.printf("%s의 촐 매출액은 %d원 입니다.%n", shop.getName(), shop.getTotalSalePrice());
    }
}
