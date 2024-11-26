package com.example.Sekai.service.impl;

import com.example.Sekai.entity.block.Block;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipInputStream;

@Service
public class BlockServiceImpl {


    @Resource
    private RestTemplate restTemplate;

    @Value("${node.ip}")
    private String ip;







    public static Block getBlockObeject(ZipInputStream zis) {
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[512];
            int len = 0;
            while ((len = zis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            String blockStr = new String(bos.toByteArray());//get block object
            Block block = new Gson().fromJson(blockStr, Block.class);
            return block;
            //}
        } catch (JsonSyntaxException | IOException e) {
            e.getMessage();

        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.getMessage();

                }
            }
        }
        return null;
    }

    public static String getBlockFileStr(ZipInputStream zis) {
        ByteArrayOutputStream bos = null;
        try {
            //if(zis.getNextEntry() != null) {
            bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[512];
            int len = 0;
            while ((len = zis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            String blockfileStr = new String(bos.toByteArray());//get block file str
            return blockfileStr;
            //}
        } catch (JsonSyntaxException | IOException e) {
            e.getMessage();

        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.getMessage();

                }
            }
        }
        return "";
    }


    public static String getMaxBlockIndexStr(ZipInputStream zis) {
        ByteArrayOutputStream bos = null;
        try {
            //if(zis.getNextEntry() != null) {
            bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = zis.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            String maxBlockIndex = new String(bos.toByteArray());//get block file str
            return maxBlockIndex;
            //}
        } catch (JsonSyntaxException | IOException e) {
            e.getMessage();

        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.getMessage();

                }
            }
        }
        return "";
    }


}
