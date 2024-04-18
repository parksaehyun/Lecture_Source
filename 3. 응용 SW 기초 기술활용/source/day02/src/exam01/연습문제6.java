package exam01;

import java.util.Arrays;
import java.util.Collections;

public class 연습문제6 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5,10,20,30};

        int[] answer = new int[nums.length];
        //System.out.println(Arrays.toString(answer));

        for(int i=0;i<nums.length;i++){
            answer[i]=nums[(nums.length)-i-1];
        }
        System.out.println(Arrays.toString(answer));
        //Collections.reverse(nums);
    }
}