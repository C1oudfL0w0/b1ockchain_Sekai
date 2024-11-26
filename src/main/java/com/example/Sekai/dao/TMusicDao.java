package com.example.Sekai.dao;

import com.example.Sekai.entity.TMusic;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TMusicDao {


    void save(TMusic TMusic);

    List<TMusic> queryAll();

    TMusic queryById(int id);

    TMusic queryByName(String name);
}
