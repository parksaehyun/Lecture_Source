package exam04;

import java.util.Arrays;

// 문제 : 1 ~ 43, 6개의 숫자 - 중복x

public class Ex02 {
    public static void main(String[] args) {
        //int num = (int)Math.ceil(Math.random() * 43); // 1 ~ 43
        //int num = (int)(Math.random() * 43) + 1; // 1 ~ 43
        //System.out.println(num);
        int cnt = 0;
        int[] lotto = new int[6];

        while(cnt < 6){
            int num = getNumber();
            if (chkDuplicated(lotto, num)) {
                continue;
            }
            lotto[cnt] = num;
            cnt++;
        }
        System.out.println(Arrays.toString(lotto));

        //System.out.println(Math.random() * 3);  // 0, 1, 2 // 곱하는 수보다 작은 수가 정수부분에 나온다
    }

    public static int getNumber(){
        return(int)(Math.random() * 43) +1;
    }

    public static boolean chkDuplicated(int[] lotto, int num) {
        for (int n : lotto) {
            if (n == num)
            return true;
        }
        return false;
    }

}
