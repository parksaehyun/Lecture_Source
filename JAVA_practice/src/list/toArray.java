package list;

import java.util.ArrayList;
import java.util.List;

public class toArray {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        // 빈 배열을 전달하면, toArray는 올바른 크기의 배열을 생성합니다.
        String[] array = list.toArray(new String[0]);
        for (String element : array) {
            System.out.println(element);
        }

        // 지정된 크기의 배열을 전달하면, 해당 배열에 요소가 저장됩니다.
        String[] anotherArray = new String[list.size()];
        list.toArray(anotherArray);
        for (String element : anotherArray) {
            System.out.println(element);
        }
    }
}

