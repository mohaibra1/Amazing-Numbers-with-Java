package numbers;

import java.text.NumberFormat;
import java.util.*;

public class Numbers {

    public void action(){
        welcomeNote();
        //        write your code here
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Enter a request: ");
            String input = scanner.nextLine();
            if (Objects.equals(input, "0")){
                System.out.println("Goodbye!");
                break;
            }
            String[] splitted = input.split(" ");
            if(splitted.length == 1){
                properties(input);
            }else if(splitted.length == 2){
                processConsecutive(splitted);
            }
        }
    }

    private void processConsecutive(String[] inputs){
        try {
            System.out.println();
            long n = Long.parseLong(inputs[0]);
            String temp = inputs[1];
            long increment = Long.parseLong(temp);
            if (n < 0 || increment < 0){
                throw new Exception("");
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (long i = 1; i <= increment; i++) {
                BuzzNumber buzzNumber = new BuzzNumber(n + "");
                buzzNumber.buzzNumber();
                DuckNumber duckNumber = new DuckNumber(n + "");
                boolean isdDuckNumber = duckNumber.isDuckNumber();

                stringBuilder.append(n).append(" is ");
                if (buzzNumber.isBuzzNumber()) {
                    stringBuilder.append("buzz, ");
                }
                if (isdDuckNumber) {
                    stringBuilder.append("duck, ");
                }
                if (isPalindromic(n + "")) {
                    stringBuilder.append("palindromic, ");
                }
                if (isGalpful(n + "")) {
                    stringBuilder.append("gapful, ");
                }
                stringBuilder.append((n % 2 == 0 ? "even" : "odd"));
                stringBuilder.append("\n");
                n++;
            }

            System.out.println(stringBuilder);
        }catch (Exception ex){
            System.out.println("second parameter should be a natural number");
        }

    }

    private void properties(String input){
        try {
            //User input
            long check = Long.parseLong(input);
            if (check < 0) {
                throw new Exception("");
            }
            BuzzNumber buzz = new BuzzNumber(input);
            buzz.buzzNumber();
            DuckNumber duckNumber = new DuckNumber(input);

            String formatted = NumberFormat.getInstance(Locale.US).format(check);

            System.out.println("Properties of " + formatted);
            System.out.println("        even: " + (check % 2 == 0));
            System.out.println("          odd: " + (check % 2 != 0));
            System.out.println("        buzz: " + buzz.isBuzzNumber());
            System.out.println("        duck: " + duckNumber.isDuckNumber());
            System.out.println("        gapful: " + isGalpful(input));
            System.out.println("palindromic: " + isPalindromic(input));
            System.out.println();
        } catch (Exception ex) {
            System.out.println("The first parameter should be a natural number or zero.");
            System.out.println();
        }
    }

    private void welcomeNote(){
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        System.out.println("""
    Supported requests:
    -enter a natural number to know its properties;
    -enter two natural numbers to obtain the properties of the list:
        * the first parameter represents a starting number;
        * the second parameter shows how many consecutive numbers are to be printed;
    -separate the parameters with one space;
    -enter 0 to exit.""");
    }

    private boolean isPalindromic(String palindorom){
        List<String> lst = List.of(palindorom.split(""));
        List<String> str = lst.reversed();
        return str.equals(lst);
    }

    private boolean isGalpful(String gapful){
        if (gapful.length() == 2){
            return false;
        }
        String first = String.valueOf(gapful.charAt(0));
        String last = String.valueOf(gapful.charAt(gapful.length()-1));
        String joinedstr = first + last;

        long x = Long.parseLong(gapful);
        long y = Long.parseLong(joinedstr);

        return x % y == 0;
    }
}
