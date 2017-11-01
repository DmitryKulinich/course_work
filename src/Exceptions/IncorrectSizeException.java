package Exceptions;

public class IncorrectSizeException extends IllegalArgumentException {
    private String massage;

    public IncorrectSizeException(String massage){
        this.massage = massage;
    }

    public IncorrectSizeException(){
        this.massage = "Incorrect size for this operation";
    }

    public String getMassage(){
        return massage;
    }
}
