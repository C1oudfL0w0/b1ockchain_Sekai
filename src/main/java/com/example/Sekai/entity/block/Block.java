package com.example.Sekai.entity.block;

import org.springframework.stereotype.Component;

@Component
public class Block
{


    public String blockIndex;
    public String blockHash;
    public String preBlockHash;
    public String workLoad;
    public String createTime;
    public String path;
    public String dataJson;
    public String tradeIds;
    public int id;

    public String workString() {
        return "Block{" +
                "blockIndex='" + blockIndex + '\'' +
                ", blockPreHash='" + preBlockHash + '\'' +
                ", workLoad='" + workLoad + '\'' +
                ", dataJson='" + dataJson + '\'' +
                ", createTime='" + createTime + '\'' +
                ", path='" + path + '\'' +
                ", randomNumber='" + randomNumber + '\'' +
                ", tradeIds='" + tradeIds + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "Block{" +
                "blockIndex='" + blockIndex + '\'' +
                ", blockHash='" + blockHash + '\'' +
                ", blockPreHash='" + preBlockHash + '\'' +
                ", workLoad='" + workLoad + '\'' +
                ", dataJson='" + dataJson + '\'' +
                ", createTime='" + createTime + '\'' +
                ", path='" + path + '\'' +
                ", tradeIds='" + tradeIds + '\'' +
                ", randomNumber='" + randomNumber + '\'' +
                '}';
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

    public String getWorkLoad() {
        return workLoad;
    }

    public String randomNumber;


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setWorkLoad(String workLoad) {
        this.workLoad = workLoad;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(String randomNumber) {
        this.randomNumber = randomNumber;
    }

    public String getDataJson() {
        return dataJson;
    }

    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }

    public String getTradeIds() {
        return tradeIds;
    }

    public void setTradeIds(String tradeIds) {
        this.tradeIds = tradeIds;
    }
}
