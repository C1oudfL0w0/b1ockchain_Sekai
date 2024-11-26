package com.example.Sekai.entity;

import java.util.List;

public class QueryCodeVo {

    private TMusic music;
    private List<TChainData> list;

    public TMusic getMusic() {
        return music;
    }

    public void setMusic(TMusic music) {
        this.music = music;
    }

    public List<TChainData> getList() {
        return list;
    }

    public void setList(List<TChainData> list) {
        this.list = list;
    }
}
