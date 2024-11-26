package com.example.Sekai.dao;

import com.example.Sekai.entity.TChainData;
import com.example.Sekai.entity.TradeObject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TChainDataDao {


    void save(TChainData tChainData);

    List<TChainData> queryByCode(String code);

    void updateData(TradeObject tradeObject);
}
