package com.example.Sekai.entity.bo;

import com.example.Sekai.entity.TChainData;

public class ChainDataBo extends TChainData {

    private String privateKey;

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
