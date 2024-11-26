package com.example.Sekai.dao;

import com.example.Sekai.entity.TCode;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TCodeDao {


    void save(TCode code);


    List<TCode> queryByName(String musicName);

    TCode queryByCode(String code);
}
