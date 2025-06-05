package numbers;

import java.util.Scanner;

public class Numbers {

    public void action(){
        //        write your code here
        System.out.println("Enter a natural number:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {
            //User input
            int check = Integer.parseInt(input);

            if (check <= 0){
                throw new Exception("");
            }

            BuzzNumber buzz = new BuzzNumber(input);
            buzz.buzzNumber();

            DuckNumber duckNumber = new DuckNumber(input);

            System.out.println("Properties of " + check);
            System.out.println("        even: " +  (check % 2 == 0));
            System.out.println("          odd: " +  (check % 2 != 0));
            System.out.println("        buzz: " +  buzz.isBuzzNumber());
            System.out.println("        duck: " + duckNumber.isDuckNumber());

        }catch (Exception ex){
            System.out.println("this number is not natural!");
        }
    }
}
