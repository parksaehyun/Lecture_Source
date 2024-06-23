package list;

import java.util.ArrayList;
import java.util.List;

public class toArray2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        Object[] array = list.toArray();
        for (Object element : array) {
            System.out.println((String) element);
        }
    }
}
