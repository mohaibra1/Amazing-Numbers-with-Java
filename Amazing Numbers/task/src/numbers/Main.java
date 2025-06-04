package numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static List<Integer> lst = new ArrayList<>();
    public static void main(String[] args) {
//        write your code here
        System.out.println("Enter a natural number:");
        Scanner scanner = new Scanner(System.in);


        try {
            //User input
            String input = scanner.nextLine();
            int check = Integer.parseInt(input);

            if (check <= 0){
                throw new Exception("");
            }
            //check if odd or even
            //checkIfOddOrEven(check);
            proccessInput(input);
            processAction(check);


        }catch (Exception ex){
            System.out.println("this number is not natural!");
        }
    }

    private static void processAction(int check){
        checkIfOddOrEven(check);
        // Get and remove the last integer
        int lastInteger = lst.getLast();
        int substitute = lastInteger * 2;
        String joined = lst.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());

        lst.removeLast();  // More direct than passing the value

        // If the list is now empty, the concatenated number is treated as 0
        int concatenatedNumber = 0;
        if (!lst.isEmpty()) {
            StringBuilder tempBuilder = new StringBuilder();
            for (Integer n : lst) {
                tempBuilder.append(n);
            }
            concatenatedNumber = Integer.parseInt(tempBuilder.toString());
        }

        // Compute absolute difference
        int result = Math.abs(concatenatedNumber - substitute);
        Divisable divisable = new Divisable(result);

        boolean isDivisable = divisable.isDivisableBy7(result);
        boolean lastNumber = divisable.isLastNumber7(lastInteger);

        if (isDivisable || lastNumber) {
            System.out.println("It is a Buzz number.");
        }else {
            System.out.println("It is not Buzz number.");
        }

        System.out.println("Explanation:");
        if (isDivisable && lastNumber){
            System.out.println(joined + " is divisible by 7 and ends with 7.");
        }else if(isDivisable){
            System.out.println(joined + " is divisible by 7.");
        }else if(lastNumber){
            System.out.println(joined + " ends with 7.");
        }else{
            System.out.println(joined + " is neither divisible by 7 nor does it end with 7.");
        }

    }

    private static void proccessInput(String input){
        String[] input1 = input.split("");
        for (String str : input1) {
            lst.add(Integer.parseInt(str));
        }
    }

    private static void checkIfOddOrEven(int check){
        if(check % 2 == 0){
            System.out.println("This number is even.");
        }else if (check % 2 != 0){
            System.out.println("This number is odd.");
        }
    }
}
