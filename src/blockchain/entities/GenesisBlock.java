package blockchain.entities;

import java.util.Optional;
import java.util.Random;

public class GenesisBlock extends Block {
    private int id = 1;
    private long timestamp = System.currentTimeMillis();
    private int nonce = new Random().nextInt();
    private String previousHash = "0";

    public GenesisBlock() {
        this.calculateHash();
    }

    private void calculateHash() {
        this.hash = Block.createHash(this, this.previousHash);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public int getNonce() {
        return nonce;
    }

    @Override
    public Optional<String> getData() {
        return Optional.ofNullable(null);
    }

    @Override
    public String getPreviousHash() {
        return previousHash;
    }
}
