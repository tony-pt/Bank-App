package eecs1022.lab7.bank.model;

public class Bank {
    private Client[] clients;
    private int clientIdx;
    private String status;

    public Bank() {
        clients = new Client[6];
        clientIdx = 0;
    }

    public String getStatus() {
        if (status != null) {
            return status;
        }
        String stat = "Accounts: {";
        if (clientIdx > 0) {
            stat += clients[0].getStatus();
        }
        for (int i = 1; i < clientIdx; i++) {
            stat += ", " + clients[i].getStatus();
        }
        stat += "}";
        return stat;
    }

    public String[] getStatement(String name) {
        Client client = findByName(name);
        if (client == null) {
            status = "Error: From-Account " + name + " does not exist";
            return null;
        }
        status = null;
        return client.getStatement();
    }

    public void deposit(String name, double amount) {
        Client client = findByName(name);
        if (client == null) {
            status = "Error: To-Account " + name + " does not exist";
        } else {
            if (amount <= 0) {
                status = "Error: Non-Positive Amount";
            } else {
                client.deposit(amount);
                status = null;
            }
        }
    }

    public void withdraw(String name, double amount) {
        Client client = findByName(name);
        if (client == null) {
            status = "Error: From-Account " + name + " does not exist";
        } else {
            if (amount <= 0) {
                status = "Error: Non-Positive Amount";
            } else if (amount > client.getBalance()) {
                status = "Error: Amount too large to withdraw";
            } else {
                client.withdraw(amount);
                status = null;
            }
        }
    }

    public void transfer(String from, String to, double amount) {
        Client clientFrom = findByName(from);
        Client clientTo = findByName(to);

        if (clientFrom == null) {
            status = "Error: From-Account " + from + " does not exist";
        } else if (clientTo == null) {
            status = "Error: To-Account " + to + " does not exist";
        } else {
            if (amount <= 0) {
                status = "Error: Non-Positive Amount";
            } else if (amount > clientFrom.getBalance()) {
                status = "Error: Amount too large to transfer";
            } else {
                clientFrom.withdraw(amount);
                clientTo.deposit(amount);
                status = null;
            }
        }
    }

    public void addClient(String name, double amount) {
        if (clientIdx == clients.length) {
            status = "Error: Maximum Number of Accounts Reached";
        } else {
            Client client = findByName(name);
            if (client != null) {
                status = "Error: Client " + name + " already exists";
            } else if (amount <= 0) {
                status = "Error: Non-Positive Initial Balance";
            } else {
                clients[clientIdx] = new Client(name, amount);
                clientIdx++;
                status = null;
            }
        }
    }

    private Client findByName(String name) {
        for (int i = 0; i < clientIdx; i++) {
            if (clients[i].getName().equalsIgnoreCase(name)) {
                return clients[i];
            }
        }
        return null;
    }
}

