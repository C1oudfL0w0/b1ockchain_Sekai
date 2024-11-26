package com.example.Sekai.utils;

import com.example.Sekai.entity.TradeBodyPool;
import com.example.Sekai.entity.TradeObject;
import com.example.Sekai.entity.block.BlockDownLoad;
import com.example.Sekai.entity.block.BlockFile;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockBaseUtils {


    public static TradeBodyPool genTbp(BlockDownLoad bdl) throws Exception {
        TradeBodyPool tbp = new TradeBodyPool();
        String blockFileStr = bdl.getBlockFileStr();
        tbp.setTbMap(genTbm(blockFileStr));
        return tbp;
    }

    /**
     * 区块字符转map对象
     *
     * @param blockFileStr
     * @return
     * @throws Exception
     */
    public static Map<String, TradeObject> genTbm(String blockFileStr) throws Exception {
        Map<String, TradeObject> tbMap = new HashMap<String, TradeObject>();
        try {

            BlockFile blockFile = new Gson().fromJson(blockFileStr,BlockFile.class);
            tbMap = blockFile.getTbMap();

        } catch (Exception e) {
            throw new Exception(e);
        }
        return tbMap;
    }




    /**
     * map对象转交易体
     *
     * @param map
     * @return
     */

    public static TradeObject mapToTradeBody(Map map) {
        TradeObject tb = null;
        try {
            tb = new TradeObject();
            Field[] fields = TradeObject.class.getDeclaredFields();
            for (Field field : fields) {

                field.setAccessible(true);
                field.set(tb, map.get(field.getName()));

            }
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return tb;
    }

    public static byte[] listToByteArray(List<String> list) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(list);
        oos.flush();
        return bos.toByteArray();
    }

    public static <T> T mapToTrade(Map map, Class<T> clasz) {
        T tb = null;
        try {
            try {
                tb = clasz.newInstance();
            } catch (InstantiationException e) {
                e.getMessage();
                return null;
            }
            Field[] fields = clasz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(tb, map.get(field.getName()));
            }
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return tb;
    }

}
