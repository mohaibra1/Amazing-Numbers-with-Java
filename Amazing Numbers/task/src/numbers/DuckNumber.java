package numbers;

public class DuckNumber {

    private final String number;

    public DuckNumber(String number){
        this.number = number;
    }

    public boolean isDuckNumber(){
        return number.matches(".*0.*");
    }

}
