package eecs1022.lab7.bank.model;
public class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getStatus() {
        return String.format("Transaction %s: $%.2f", type, amount);
    }

}
