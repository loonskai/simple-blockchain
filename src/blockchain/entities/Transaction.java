package blockchain.entities;

public class Transaction {
    private String sender;
    private String receiver;
    private long amount;

    public Transaction(String sender, String receiver, long amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return this.sender + " sent " + this.amount + "VC to " + this.receiver;
    }
}
