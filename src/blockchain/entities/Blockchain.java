package blockchain.entities;

import blockchain.util.StringUtil;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class Blockchain {
    private static final ArrayList<Block> chain = new ArrayList<>();
    private static final ArrayDeque<Transaction> transactions = new ArrayDeque<>();
    private static final int MAX_CALC_TIME = 1;
    private static final int MIN_CALC_TIME = 0;
    private static int reward = 100;
    public static int miningDifficulty = 0;

    public static Blockchain createBlockchain() {
        return new Blockchain();
    }

    public static Block generateBlock(Block lastBlock) {
        Block newBlock = lastBlock == null
            ? new GenesisBlock()
            : new Block(lastBlock);

        return newBlock;
    }

    public static String getJournal() {
        return transactions.size()  == 0
            ? null
            : transactions.stream()
                .map(Transaction::toString)
                .collect(Collectors.joining("\n"));
    }

    private static void clearJournal() {
        transactions.clear();
    }

    public static boolean validateMiningDifficulty(String hash) {
        return hash.matches(String.format("^0{%d}.*", miningDifficulty));
    }

    public static void log(Block block, long minerId, long reward, String additionalInfo) {
        String minerName = "miner" + minerId;

        System.out.println("Block:");
        System.out.println(String.format("Created by: " + minerName));
        System.out.println(String.format("%s gets %d VC", minerName, reward));
        System.out.println(String.format("Id: %d", block.getId()));
        System.out.println(String.format("Timestamp: %d", block.getTimestamp()));
        System.out.println(String.format("Magic number: %d", block.getNonce()));
        System.out.println("Hash of the previous block:");
        System.out.println(block.getPreviousHash());
        System.out.println("Hash of the block:");
        System.out.println(block.getHash());
        System.out.println("Block data:");
        block.getData().ifPresentOrElse(System.out::println, () -> System.out.println("No transactions"));
        System.out.println(String.format("Block was generating for %d seconds", block.getCalculationTime()));
        System.out.println(additionalInfo);
        System.out.println();
    }

    private String revaluateProofOfWork(long lastCalculationTime) {
        if (lastCalculationTime <= MIN_CALC_TIME) {
            Blockchain.miningDifficulty += 1;
            return String.format("N was increased to %d", Blockchain.miningDifficulty);
        }
        if (lastCalculationTime >= MAX_CALC_TIME) {
            Blockchain.miningDifficulty -= 1;
            return String.format("N was decreased to %d", Blockchain.miningDifficulty);
        }
        return String.format("N stays the same");
    }

    public void addBlockToChain(Block newBlock, long minerId) {
//        String data = Blockchain.getJournal();
//        newBlock.includeData(data);
//        clearJournal();
        chain.add(newBlock);
        String proofOfWorkInfo = this.revaluateProofOfWork(newBlock.getCalculationTime());
        Blockchain.log(newBlock, minerId, reward, proofOfWorkInfo);
    }

    public static void addTransaction(Transaction msg) {
        transactions.addLast(msg);
    }

    public boolean validateBlock(Block newBlock) {
        Block lastBlock = this.getLastBlock();
        if (lastBlock == null) {
            return true;
        }
        String expectedHash = Block.createHash(newBlock, lastBlock.getHash());
        return newBlock.getHash().equals(expectedHash);
    }

    public int getSize() {
        return chain.size();
    }

    public Block getLastBlock() {
        int size = this.getSize();
        return size == 0 ? null : chain.get(size - 1);
    }
}
