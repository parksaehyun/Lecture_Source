package 연습문제Coffe;

import java.util.HashMap;
import java.util.Map;

public class CoffeBean extends AbstractCoffeeShop {

    private static Map<String, Integer> menus;

    static {
        menus = new HashMap<>();
        menus.put("아메리카노", 3800);
        menus.put("라떼", 4300);
    }

    public CoffeBean() {
        super("커피빈", menus);
    }
}
