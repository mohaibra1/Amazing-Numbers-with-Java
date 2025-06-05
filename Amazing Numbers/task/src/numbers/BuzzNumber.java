package numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BuzzNumber {

    private final String userInput;
    private boolean buzzNumber;
    private final List<Long> lst = new ArrayList<>();

    public BuzzNumber(String userInput){
        this.userInput = userInput;
    }

    public String getNumber(){
        return userInput;
    }

    public boolean isBuzzNumber(){
        return buzzNumber;
    }

    public boolean isDivisableBy7(long number) {
        if (number % 7 == 0){
            buzzNumber = true;
            return true;
        }
        return false;
    }

    public boolean isLastNumber7(long number){
        String str = String.valueOf(number);
        int n = Integer.parseInt(String.valueOf(str.charAt(str.length()-1)));
        if (n == 7){
            buzzNumber = true;
            return  true;
        }
        return false;
    }

    public void buzzNumber(){
        //User input
        long check = Long.parseLong(userInput);

        //check if odd or even
        //checkIfOddOrEven(check);
        processInput(userInput);
        processAction(check);
    }

    private void processAction(long check){
        //checkIfOddOrEven(check);
        // Get and remove the last integer
        long lastInteger = lst.getLast();
        long substitute = lastInteger * 2;
//        String joined = lst.stream()
//                .map(String::valueOf)
//                .collect(Collectors.joining());
        lst.removeLast();  // More direct than passing the value
        // If the list is now empty, the concatenated number is treated as 0
        long concatenatedNumber = 0;
        if (!lst.isEmpty()) {
            StringBuilder tempBuilder = new StringBuilder();
            for (Long n : lst) {
                tempBuilder.append(n);
            }
            concatenatedNumber = Long.parseLong(tempBuilder.toString());
        }

        // Compute absolute difference
        long result = Math.abs(concatenatedNumber - substitute);


        boolean isDivisable = isDivisableBy7(result);
        boolean lastNumber = isLastNumber7(lastInteger);

//        if (isDivisable || lastNumber) {
//            System.out.println("It is a Buzz number.");
//        }else {
//            System.out.println("It is not Buzz number.");
//        }
//
//        System.out.println("Explanation:");
//        if (isDivisable && lastNumber){
//            System.out.println(joined + " is divisible by 7 and ends with 7.");
//        }else if(isDivisable){
//            System.out.println(joined + " is divisible by 7.");
//        }else if(lastNumber){
//            System.out.println(joined + " ends with 7.");
//        }else{
//            System.out.println(joined + " is neither divisible by 7 nor does it end with 7.");
//        }

    }

//    public void processList(){
//        // Get and remove the last integer
//        lastInteger = lst.getLast();
//        int substitute = lastInteger * 2;
//        lst.removeLast();  // More direct than passing the value
//
//        // If the list is now empty, the concatenated number is treated as 0
//        int concatenatedNumber = 0;
//        if (!lst.isEmpty()) {
//            StringBuilder tempBuilder = new StringBuilder();
//            for (Integer n : lst) {
//                tempBuilder.append(n);
//            }
//            concatenatedNumber = Integer.parseInt(tempBuilder.toString());
//        }
//
//        // Compute absolute difference
//        result = Math.abs(concatenatedNumber - substitute);
//
//    }

    private void processInput(String input){
        String[] input1 = input.split("");
        for (String str : input1) {
            lst.add(Long.parseLong(str));
        }
    }

    private void checkIfOddOrEven(long check){
        if(check % 2 == 0){
            System.out.println("This number is even.");
        }else if (check % 2 != 0){
            System.out.println("This number is odd.");
        }
    }
}
