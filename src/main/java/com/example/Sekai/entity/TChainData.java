package com.example.Sekai.entity;

public class TChainData {

    private int id;
    private String from;
    private String to;
    private String content;
    private String hashNo;
    private String blockIndex;
    private String createTime;
    private String code;
    private String musicName;
    private String status;
    private String chainStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHashNo() {
        return hashNo;
    }

    public void setHashNo(String hashNo) {
        this.hashNo = hashNo;
    }

    public String getBlockIndex() {
        return blockIndex;
    }

    public void setBlockIndex(String blockIndex) {
        this.blockIndex = blockIndex;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChainStatus() {
        return chainStatus;
    }

    public void setChainStatus(String chainStatus) {
        this.chainStatus = chainStatus;
    }

    @Override
    public String toString() {
        return "TChainData{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                ", code='" + code + '\'' +
                ", musicName='" + musicName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
