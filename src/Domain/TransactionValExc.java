package Domain;

public class TransactionValExc extends RuntimeException {

    public TransactionValExc(String message){
        super(message);
    }

}