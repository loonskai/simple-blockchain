package blockchain.services;

import blockchain.entities.Blockchain;
import blockchain.users.User;

import java.util.HashMap;

public class TransactionsService implements BlockchainService {
    private Blockchain blockchain;
    private HashMap<String, User> users = new HashMap<String, User>();

    public TransactionsService(Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    @Override
    public void start() {
        this.initializeUsers();
        this.processTransactions();
    }

    private void initializeUsers() {
        this.users.put("Tom", new User("Tom", blockchain));
        this.users.put("Sarah", new User("Sarah", blockchain));
        this.users.put("Nick", new User("Nick", blockchain));
    }

    private void processTransactions() {
        User user1 = this.users.get("Tom");
        User user2 = this.users.get("Sarah");
        User user3 = this.users.get("Nick");

        // TODO: Sign and validate transactions
        user1.sendTransaction(user2.getId(), 10);
        user2.sendTransaction(user1.getId(), 10);
        user3.sendTransaction(user1.getId(), 10);
    }
}
