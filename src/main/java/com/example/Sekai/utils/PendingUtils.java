package com.example.Sekai.utils;

import com.example.Sekai.entity.TradeObject;

public class PendingUtils {

    public static String TRADE_NUMBER_PREFIX="Msg";

    public static String genTradeNo(TradeObject tb) {
        String a = tb.toString();
        return genTradeNo(a);
    }
    public static String genTradeNo(String tbstring) {
        return TRADE_NUMBER_PREFIX + EncryptUtil.encryptSHA1(tbstring);
    }


}
