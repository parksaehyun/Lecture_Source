package practice_day01_0413;

import java.util.Arrays;

public class Ex07 {
    public static void main(String[] args) {
        int [][] nums = { {10,20,30}, {40,50,60} };
        System.out.println(nums.length); //2행 (0, 1)
        System.out.println(nums[0].length); // 3열 (0,1,2)

        for (int i = 0; i < nums.length; i++) {
            System.out.println(Arrays.toString(nums[i]));
        }
    }
}
