package blockchain.users;

import blockchain.entities.Blockchain;
import blockchain.entities.Transaction;

public abstract class BlockchainUser {
    private Blockchain blockchain;
    protected String id;

    public void sendTransaction(String receiver, long amount) {
        this.blockchain.addTransaction(new Transaction(this.id, receiver, amount));
    }
}
