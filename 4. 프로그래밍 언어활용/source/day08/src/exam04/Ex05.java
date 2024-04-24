package exam04;

import java.util.Arrays;

//문제 : nums1, num2가 값이 같은지 체크하는 법
public class Ex05 {
    public static void main(String[] args) {
        int [] nums1 = { 10, 20, 30, 40 };
        int [] nums2 = { 10, 20, 30, 40 };

//        2번 풀이
        boolean isSame = Arrays.equals(nums1, nums2);
        System.out.println(isSame);


//        1번 풀이
//        boolean isSame = true;
//        first : for ( int i = 0; i < nums1.length; i++ ) {
//            if (nums1[i] != nums2[i]){
//                isSame = false;
//                break;
//            }
//        }
//        System.out.println(isSame);
    }
}
