package blockchain.services;

import blockchain.entities.Blockchain;

public interface BlockchainService {
    public Blockchain blockchain = null;
    public void start() throws InterruptedException;
}
