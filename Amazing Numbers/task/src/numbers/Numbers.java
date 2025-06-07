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

        }
    }

    private void handleInput(String[] strings) throws Exception {
        List<String> list = List.of("EVEN","ODD", "BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY",
                "SQUARE", "SUNNY", "JUMPING", "HAPPY", "SAD",
                "-EVEN","-ODD", "-BUZZ", "-DUCK", "-PALINDROMIC", "-GAPFUL", "-SPY",
                "-SQUARE", "-SUNNY", "-JUMPING", "-HAPPY", "-SAD");
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

            if ((mutual.contains("EVEN") && mutual.contains("ODD")) ||
                    (mutual.contains("DUCK") && mutual.contains("SPY"))
                    || (mutual.contains("SUNNY") && mutual.contains("SQUARE"))
                    ||(mutual.contains("HAPPY") && mutual.contains("SAD")
            || (mutual.contains("-EVEN") && mutual.contains("-ODD")) ||
                    (mutual.contains("-GAPFUL") && mutual.contains("GAPFUL"))
                    || (mutual.contains("ODD") && mutual.contains("-ODD"))
                    ||(mutual.contains("-HAPPY") && mutual.contains("-SAD")
             ||(mutual.contains("-EVEN") && mutual.contains("EVEN"))
            || (mutual.contains("-SUNNY") && mutual.contains("SUNNY")))
                || (mutual.contains("-SAD") && mutual.contains("SAD"))
                    || (mutual.contains("-BUCK") && mutual.contains("BUCK"))
                     || (mutual.contains("-HAPPY") && mutual.contains("HAPPY"))
                    || (mutual.contains("-DUCK") && mutual.contains("DUCK"))
                    || (mutual.contains("-PALINDROMIC") && mutual.contains("PALINDROMIC"))
                    || (mutual.contains("-SPY") && mutual.contains("SPY"))
                    || (mutual.contains("-SQUARE") && mutual.contains("SQUARE"))
                    || (mutual.contains("-JUMPING") && mutual.contains("JUMPING"))
            )){
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
            throw new Exception("The property " + properties + " is wrong. \nAvailable properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]");
            }
        }
        System.out.println();
    }

    private void processConsecutive(String[] inputs){
        System.out.println();
        long n = Long.parseLong(inputs[0]);
        String temp = inputs[1];
        long increment = Long.parseLong(temp);
        // add all the properties in a list
        List<String> wordsToFind = new ArrayList<>();
        List<String> wordsToIgnore = new ArrayList<>();
        if (!Arrays.toString(inputs).contains("-")){
            wordsToFind.addAll(Arrays.asList(inputs).subList(2, inputs.length));
        }else {
            for (int i = 2; i < inputs.length; i++){
                String s = inputs[i];
                if (s.contains("-")){
                    s = s.replace("-", "");
                    wordsToIgnore.add(s);
                }else {
                    wordsToFind.add(s);
                }
            }
        }

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
                        (isJumpingNumber(str) ? "jumping, " : "") +
                        (isHappyNumber(Long.parseLong(str)) ? "happy, " : "") +
                        (isSadNumber(str) ? "sad, " : "") +
                        (isEven(str)? "even" : "odd") +
                        ("\n");

                 if(inputs.length > 2){

                     boolean allExist = wordsToFind.stream()
                             .allMatch(word -> string.toLowerCase().contains(word.toLowerCase()));
                     boolean ignore = wordsToIgnore.stream()
                             .noneMatch(word -> string.toLowerCase().contains(word.toLowerCase()));

                     if (allExist && ignore){
                         stringBuilder.append(string);
                     }
                     else if (ignore && !wordsToIgnore.isEmpty() && wordsToFind.isEmpty()) {
                        stringBuilder.append(string);
                     }else if (allExist && !wordsToFind.isEmpty() && wordsToIgnore.isEmpty()) {
                         stringBuilder.append(string);
                     }else {
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
                "        jumping: " + isJumpingNumber(input) + "\n" +
                "        happy: " + isHappyNumber(Long.parseLong(input)) + "\n" +
                "        sad: " + isSadNumber(input) + "\n" +
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
 - two natural numbers and properties to search for;
 - a property preceded by minus must not be present in numbers;
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

    private boolean isJumpingNumber(String num){
        String[] split = num.split("");
        int diff = 0;
        for (int i = 0; i < split.length - 1; i++){
            int x = Integer.parseInt(split[i]);
            int y = Integer.parseInt(split[i+1]);
            diff = Math.abs(x - y);
            if (diff != 1){
                return false;
            }
        }
        return true;
    }

    public static boolean isHappyNumber(long n) {
        Set<Long> seen = new HashSet<>();

        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = sumOfSquares(n);
        }

        return n == 1;
    }

    private static long sumOfSquares(long n) {
        long sum = 0;
        while (n > 0) {
            long digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

    private boolean isSadNumber(String num){
        long n = Long.parseLong(num);
        return !isHappyNumber(n);
    }

}
