package collection.list.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListEx2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> numbers = new ArrayList<>();

        while (true) {
            int input = sc.nextInt();
            if (input == 0) {
                break;
            }
            numbers.add(input);
        }

        for (Integer number : numbers) {
            System.out.println("number = " + number);
        }
    }
}
