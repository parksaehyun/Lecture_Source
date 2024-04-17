package test;

import java.util.Arrays;

public class Q5 {
    // 다음 배열의 순서를 반대로 정렬해 보세요. int[] nums = { 21, 22, 30, 11, 99, 31 };
    public static void main(String[] args) {
        int[] nums = { 21, 22, 30, 11, 99, 31 };

        for( int i=0; i < nums.length; i++) {
            int [] reverse = {nums[nums.length - i - 1]};
            System.out.println(Arrays.toString(reverse));
            }
        }
    }


