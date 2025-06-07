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
            String input = scanner.nextLine().toUpperCase();

            if (Objects.equals(input, "0")){
                System.out.println("Goodbye!");
                break;
            }
            String[] strings = input.split(" ");
            try {
                handleInput(strings);
            }catch (Exception ex){
                System.out.println(ex.getMessage());
                continue;
            }

            if(strings.length == 1){
                properties(input);
            }else {
                processConsecutive(strings);
            }
//            else if(strings.length == 3){
//                processConsecutiveWithProperty(strings);
//            }
        }
    }

    private void handleInput(String[] strings) throws Exception {
        List<String> list = List.of("EVEN","ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE", "SUNNY");
        if (Long.parseLong(strings[0]) < 0) {
            throw new Exception("The first parameter should be a natural number or zero.");
        }if(strings.length > 1 && Long.parseLong(strings[1]) < 0) {
            throw new Exception("The second parameter should be a natural number");
        }if (strings.length > 2) {
            List<String> mutual = new ArrayList<>();
            List<String> properties = new ArrayList<>();
            for (String s: strings){
                if (!list.contains(s) && s.matches("[^0-9]*")){
                    properties.add(s);
                }
                if(list.contains(s)){
                    mutual.add(s);
                }
            }

            if ((mutual.contains("EVEN") && mutual.contains("ODD")) || (mutual.contains("DUCK") && mutual.contains("SPY")) || (mutual.contains("SUNNY") && mutual.contains("SQUARE"))){
                throw new Exception("The request contains mutually exclusive properties: " + mutual + "\nThere are no numbers with these properties.");
            }
            if (properties.isEmpty()){
                for (int i = 2; i < strings.length; i++){
                    if (strings[i].matches("\\d*")) {
                        properties.add(strings[i]);
                    }
                }
            }

            if (!properties.isEmpty()){
            throw new Exception("The property " + properties + " is wrong. \nAvailable properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]");
            }
        }
        System.out.println();
    }

    private void processConsecutive(String[] inputs){
            System.out.println();
            long n = Long.parseLong(inputs[0]);
            String temp = inputs[1];
            long increment = Long.parseLong(temp);

            StringBuilder stringBuilder = new StringBuilder();
            for (long i = 1; i <= increment; i++) {
                BuzzNumber buzzNumber = new BuzzNumber(n + "");
                buzzNumber.buzzNumber();

                String formatted = NumberFormat.getInstance(Locale.US).format(n);
                String str = n + "";

                String string = formatted + " is " + (buzzNumber.isBuzzNumber()? "buzz, ":"") +
                        (isDuckNumber(str) ? "duck, ": "") +
                        (isPalindromic(str)? "palindromic, ": "") +
                        (isGalpful(str)? "gapful, ": "") +
                        (isSPY(str)? "spy, ": "") +
                        (isPerfectSquare(Long.parseLong(str))? "square, ": "") +
                        (isSunnyNumber(Long.parseLong(str))? "sunny, ": "") +
                        (isEven(str)? "even" : "odd") +
                        ("\n");

                if (inputs.length == 3){
                    String s = inputs[2].toLowerCase();

                    if (string.contains(s)){
                        stringBuilder.append(string);
                    }else{
                        i--;
                    }
                }else if(inputs.length == 4){
                    String s = inputs[2].toLowerCase();
                    String s1 = inputs[3].toLowerCase();
                    if (string.contains(s) && string.contains(s1)){
                        stringBuilder.append(string);
                    }else{
                        i--;
                    }
                }else {
                stringBuilder.append(string);
                }

                n++;
            }


            System.out.println(stringBuilder);

    }


    private void properties(String input){
            //User input
            long check = Long.parseLong(input);
            BuzzNumber buzz = new BuzzNumber(input);
            buzz.buzzNumber();

            String formatted = NumberFormat.getInstance(Locale.US).format(check);

        String stringBuilder = "Properties of " + formatted + "\n" +
                "        even: " + isEven(check + "") + "\n" +
                "          odd: " + (check % 2 != 0) + "\n" +
                "        buzz: " + buzz.isBuzzNumber() + "\n" +
                "        duck: " + isDuckNumber(input) + "\n" +
                "        gapful: " + isGalpful(input) + "\n" +
                "        spy: " + isSPY(input) + "\n" +
                "        square: " + isPerfectSquare(Long.parseLong(input)) + "\n" +
                "        sunny: " + isSunnyNumber(Long.parseLong(input)) + "\n" +
                "palindromic: " + isPalindromic(input) + "\n";

        System.out.println(stringBuilder);
    }

    private void welcomeNote(){
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        System.out.println("""
Supported requests:
 - enter a natural number to know its properties;
 - enter two natural numbers to obtain the properties of the list:
   * the first parameter represents a starting number;
   * the second parameter shows how many consecutive numbers are to be printed;
 - two natural numbers and a property to search for;
 - two natural numbers and two properties to search for;
 - separate the parameters with one space;
 - enter 0 to exit.""");
    }


    private boolean isDuckNumber(String duckNumber){
        return duckNumber.matches(".*0.*");
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
        String disjointed = first + last;

        long x = Long.parseLong(gapful);
        long y = Long.parseLong(disjointed);

        return x % y == 0;
    }

    private boolean isSPY(String spy){
        String[] spys = spy.split("");
        long add = add(spys);
        long multiply = multiply(spys);

        return add == multiply;
    }

    private boolean isEven(String s){
        return Long.parseLong(s) % 2 == 0;
    }

    private long add(String[] spys){
        long result = 0;
        for (String s: spys){
            result += Long.parseLong(s);
        }
        return result;
    }

    private long multiply(String[] spys){
        long result = 1;
        for (String s: spys){
            result *= Long.parseLong(s);
        }
        return result;
    }
    private boolean isPerfectSquare(long num){
        long sqrt = (long) Math.sqrt(num);
        return sqrt * sqrt == num;
    }

    private boolean isSunnyNumber(long num){
        return isPerfectSquare(num + 1);
    }
}
