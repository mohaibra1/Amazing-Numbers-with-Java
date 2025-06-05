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
                System.out.println("palindromic: " + isPalindromic(input));
                System.out.println();
            } catch (Exception ex) {
                System.out.println("The first parameter should be a natural number or zero.");
                System.out.println();
            }
        }
    }

    private void welcomeNote(){
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        System.out.println("""
    Supported requests:
    -enter a natural number to know its properties;
    -enter 0 to exit.""");
    }

    private boolean isPalindromic(String palindorom){
        List<String> lst = List.of(palindorom.split(""));
        List<String> str = lst.reversed();
        return str.equals(lst);
    }
}
