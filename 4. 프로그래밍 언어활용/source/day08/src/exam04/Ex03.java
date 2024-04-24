package exam04;

import java.util.HashSet;

// 문제 : 1 ~ 43, 6개의 숫자 - 중복x

public class Ex03 {
    public static void main(String[] args) {
        // Set : 중복x
        HashSet<Integer> lotto = new HashSet<>();
        while(lotto.size() < 6){
            lotto.add(getNumber());
        }
        System.out.println(lotto);

    }

    public static int getNumber() {
        return (int)(Math.random() * 43) + 1; // 1 ~ 43
    }
}
