package com.example.Sekai.entity.block;

import com.example.Sekai.entity.TradeObject;

import java.util.Map;

public class BlockFile {


    private Map<String, TradeObject> tbMap;

    private String blockIndex;

    private String blockHash;

    private String preBlockHash;

    public Map<String, TradeObject> getTbMap() {
        return tbMap;
    }

    public void setTbMap(Map<String, TradeObject> tbMap) {
        this.tbMap = tbMap;
    }

    public String getBlockIndex() {
        return blockIndex;
    }

    public void setBlockIndex(String blockIndex) {
        this.blockIndex = blockIndex;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String getPreBlockHash() {
        return preBlockHash;
    }

    public void setPreBlockHash(String preBlockHash) {
        this.preBlockHash = preBlockHash;
    }

    @Override
    public String toString() {
        return "BlockFile{" +
                "tbMap=" + tbMap +
                ", blockIndex='" + blockIndex + '\'' +
                ", blockHash='" + blockHash + '\'' +
                ", preBlockHash='" + preBlockHash + '\'' +
                '}';
    }
}
