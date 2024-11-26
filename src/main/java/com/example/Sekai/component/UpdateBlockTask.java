package com.example.Sekai.component;

import com.example.Sekai.dao.TBlockDao;
import com.example.Sekai.dao.TChainDataDao;
import com.example.Sekai.entity.NoticeParams;
import com.example.Sekai.entity.TradeBodyPool;
import com.example.Sekai.entity.TradeObject;
import com.example.Sekai.entity.block.BlockDownLoad;
import com.example.Sekai.entity.block.BlockInfo;
import com.example.Sekai.utils.BlockBaseUtils;
import com.example.Sekai.utils.http.HttpHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class UpdateBlockTask {



    @Value("${node.ip}")
    private String ip;


    @Resource
    private TBlockDao tBlockDao;

    @Resource
    private TChainDataDao tChainDataDao;


    @Scheduled(cron = "*/5 * * * * ?")
    public void update() throws Exception {
        BlockInfo blockInfo = tBlockDao.queryBlockInfo();
        String nextIndex = String.valueOf(Integer.valueOf(blockInfo.getCurrentBlockIndex()) + 1);
        System.out.println("执行定时任务下载区块--------下载区块为：" + nextIndex);
        updateBlock(nextIndex);
    }


    public void updateBlock(String blockIndex) throws Exception {
        BlockDownLoad bdl = null;
        NoticeParams np = new NoticeParams(blockIndex.toString(), ip,"");
        bdl = HttpHelper.downLoadBlock(ip, 8001, np);//获取区块和区块内容

        if (bdl == null ||bdl.getBlock() ==null ||Integer.valueOf(bdl.getBlock().blockIndex) < Integer.valueOf(blockIndex)){
            return;
        }

        TradeBodyPool tbp = BlockBaseUtils.genTbp(bdl);
        List<TradeObject> list = new ArrayList<>();

        Map<String, TradeObject> tbMap = tbp.getTbMap();
        for (Map.Entry<String, TradeObject> entry : tbMap.entrySet()) {
            TradeObject tradeObject = entry.getValue();
            list.add(tradeObject);
        }

        for (TradeObject tradeObject : list) {
            tradeObject.setBlockIndex(blockIndex);
            tChainDataDao.updateData(tradeObject);
        }

        BlockInfo blockInfo = new BlockInfo();
        blockInfo.setBlockIndex(bdl.getMaxBlockIndex());
        blockInfo.setCurrentBlockIndex(String.valueOf(Integer.valueOf(blockIndex)));
        tBlockDao.updateBlock(blockInfo);
        System.out.println("更新完成-------");
    }

}
