package collection.list.test;

import java.util.ArrayList;
import java.util.List;

public class ListEx1 {

    public static void main(String[] args) {
        List<Integer> students = new ArrayList<>();
        students.add(90);
        students.add(80);
        students.add(70);
        students.add(60);
        students.add(50);
        students.add(40);

        int total = 0;
        for (Integer student : students) {
            total += student;
        }
        System.out.println("total = " + total);
    }
}
