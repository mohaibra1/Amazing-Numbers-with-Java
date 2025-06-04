package numbers;

public class Divisable {

    private int number;

    public Divisable(int number){
        this.number = number;
    }

    public int getNumber(){
        return number;
    }

    public boolean isDivisableBy7(int number) {
        return number % 7 == 0;
    }

    public boolean isLastNumber7(int number){
        String str = String.valueOf(number);
        int n = Integer.parseInt(String.valueOf(str.charAt(str.length()-1)));
        return  n == 7;
    }
}
