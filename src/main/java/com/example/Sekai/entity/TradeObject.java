package com.example.Sekai.entity;

public class TradeObject {

    private String from;
    private String to;
    private String hashNo;
    private String type;
    private String content;
    private String blockIndex;
    private String contentjson;
    private String jsoncreatetime;
    private String uId;
    private String annexPath;
    private String filePath;
    private String blockHash;
    private String createTime;
    private String sign;
    private String contractContent;
    private String paramStr;
    private String lastData;
    private String objToString;
    private String dataStr;


    @Override
    public String toString() {
        return "TradeObject{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", blockIndex='" + blockIndex + '\'' +
                ", contentjson='" + contentjson + '\'' +
                ", jsoncreatetime='" + jsoncreatetime + '\'' +
                ", uId='" + uId + '\'' +
                ", annexPath='" + annexPath + '\'' +
                ", filePath='" + filePath + '\'' +
                ", contractContent='" + contractContent + '\'' +
                ", paramStr='" + paramStr + '\'' +
                ", lastData='" + lastData + '\'' +
                '}';
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


    public String getHashNo() {
        return hashNo;
    }

    public void setHashNo(String hashNo) {
        this.hashNo = hashNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBlockIndex() {
        return blockIndex;
    }

    public void setBlockIndex(String blockIndex) {
        this.blockIndex = blockIndex;
    }


    public String getContentjson() {
        return contentjson;
    }

    public void setContentjson(String contentjson) {
        this.contentjson = contentjson;
    }

    public String getJsoncreatetime() {
        return jsoncreatetime;
    }

    public void setJsoncreatetime(String jsoncreatetime) {
        this.jsoncreatetime = jsoncreatetime;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getAnnexPath() {
        return annexPath;
    }

    public void setAnnexPath(String annexPath) {
        this.annexPath = annexPath;
    }


    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getContractContent() {
        return contractContent;
    }

    public void setContractContent(String contractContent) {
        this.contractContent = contractContent;
    }

    public String getParamStr() {
        return paramStr;
    }

    public void setParamStr(String paramStr) {
        this.paramStr = paramStr;
    }

    public String getLastData() {
        return lastData;
    }

    public void setLastData(String lastData) {
        this.lastData = lastData;
    }

    public String getObjToString() {
        return objToString;
    }

    public void setObjToString(String objToString) {
        this.objToString = objToString;
    }

    public String getDataStr() {
        return dataStr;
    }

    public void setDataStr(String dataStr) {
        this.dataStr = dataStr;
    }
}

