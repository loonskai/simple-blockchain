package blockchain;

import blockchain.entities.Blockchain;
import blockchain.services.TransactionsService;
import blockchain.services.MiningService;

public class Main {
    public static void main(String[] args) {
        Blockchain blockchain = Blockchain.createBlockchain();
        TransactionsService transactionsService = new TransactionsService(blockchain);
        transactionsService.start();
        MiningService miningService = new MiningService(blockchain);
        try {
            miningService.start();
        } catch (InterruptedException e) {
            System.out.println("Exception");
        }
    }
}
