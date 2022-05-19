package eecs1022.lab7.bank.model;
public class Client {
    private String name;
    private double balance;
    private Transaction[] transactions;
    private int transacIdx;

    public Client(String name, double balance) {
        this.name = name;
        this.balance = balance;
        transactions = new Transaction[100];
        transacIdx = 0;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public String getStatus() {
        return String.format("%s: $%.2f", name, balance);
    }

    public void deposit(double amount) {
        this.balance += amount;
        transactions[transacIdx] = new Transaction("DEPOSIT", amount);
        transacIdx++;
    }

    public String[] getStatement() {
        String[] statement = new String[transacIdx+1];
        statement[0] = getStatus();

        for (int i = 0; i < transacIdx; i++) {
            statement[i+1] = transactions[i].getStatus();
        }
        return statement;
    }

    public void withdraw(double amount) {
        this.balance -= amount;
        transactions[transacIdx] = new Transaction("WITHDRAW", amount);
        transacIdx++;
    }
}
