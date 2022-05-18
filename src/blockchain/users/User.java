package blockchain.users;

import blockchain.entities.Blockchain;

public class User extends BlockchainUser {
    private Blockchain blockchain;

    public User(String name, Blockchain blockchain) {
        this.id = name;
        this.blockchain = blockchain;
    }

    public String getId() {
        return this.id;
    }
}
