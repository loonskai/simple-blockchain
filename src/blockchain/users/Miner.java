package blockchain.users;

import blockchain.entities.Block;
import blockchain.entities.Blockchain;

public class Miner implements Runnable {
    Blockchain blockchain;
    int maxBlocks;

    public Miner(Blockchain blockchain, int maxBlocks) {
        this.blockchain = blockchain;
        this.maxBlocks = maxBlocks;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Block prevBlock = blockchain.getLastBlock();
                Block newBlock = Blockchain.generateBlock(prevBlock);
                if (blockchain.getSize() < maxBlocks) {
                    synchronized (blockchain) {
                        if (blockchain.validateBlock(newBlock)) {
                            blockchain.addBlockToChain(newBlock, Thread.currentThread().getId());
                        }
                    }
                } else {
                    return;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return;
            }
        }
    }
}