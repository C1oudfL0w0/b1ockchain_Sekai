package com.example.Sekai.dao;

import com.example.Sekai.entity.block.BlockInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface TBlockDao {

    BlockInfo queryBlockInfo();

    void updateBlock(BlockInfo blockInfo);


}
