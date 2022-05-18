package blockchain.services;

import blockchain.entities.Blockchain;
import blockchain.users.Miner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MiningService implements BlockchainService {
    public static final int MINERS_NUM = 10;
    public static final int MAX_BLOCKS = 15;
    Blockchain blockchain;

    public MiningService(Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    @Override
    public void start() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(MINERS_NUM);
        for (int i = 0; i < MINERS_NUM; i++) {
            executorService.submit(new Miner(blockchain, MAX_BLOCKS));
        }
        executorService.shutdown();
        executorService.awaitTermination(15, TimeUnit.SECONDS);
    }
}
