package blockchain.entities;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import blockchain.util.StringUtil;

public class Block {
    protected String hash;

    private int id;
    private int nonce;
    private long calculationTime;
    private long timestamp;
    private String previousHash;
    private String data;

    public static String createHash(Block block, String previousHash) {
        StringBuilder hashBuilder = new StringBuilder();
        hashBuilder.append(block.nonce);
        hashBuilder.append(block.id);
        hashBuilder.append(block.timestamp);
        hashBuilder.append(block.data);
        hashBuilder.append(previousHash);
        return StringUtil.applySha256(hashBuilder.toString());
    }

    public Block() {}

    public Block(Block prevBlock) {
        this.id = prevBlock.getId() + 1;
        this.timestamp = System.currentTimeMillis();
        this.previousHash = prevBlock.getHash();
        this.calculateHash(this.previousHash);
    }

    private void calculateHash(String previousHash) {
        long start = System.currentTimeMillis();
        Random nonceGuesser = new Random();
        while (true) {
            this.nonce = nonceGuesser.nextInt() & Integer.MAX_VALUE;
            this.hash = Block.createHash(this, previousHash);
            if (Blockchain.validateMiningDifficulty(hash)) {
                break;
            }
        }
        long finish = System.currentTimeMillis();
        this.calculationTime = TimeUnit.MILLISECONDS.toMillis(finish - start);
    }

    public void includeData(String data) {
        this.data = data;
    }

    public int getId() {
        return this.id;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public String getHash() {
        return this.hash;
    }

    public int getNonce() {
        return this.nonce;
    }

    public long getCalculationTime() {
        return this.calculationTime;
    }

    public Optional<String> getData() {
        return Optional.ofNullable(this.data);
    }

    public String getPreviousHash() {
        return this.previousHash;
    }
}
