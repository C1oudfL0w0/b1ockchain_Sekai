package com.example.Sekai.controller;

import com.example.Sekai.dao.TCodeDao;
import com.example.Sekai.dao.TMusicDao;
import com.example.Sekai.entity.JSONObject;
import com.example.Sekai.entity.TChainData;
import com.example.Sekai.entity.TCode;
import com.example.Sekai.entity.TMusic;
import com.example.Sekai.entity.bo.MusicBo;
import com.example.Sekai.utils.DateUtils;
import com.example.Sekai.utils.EncryptUtil;
import com.example.Sekai.utils.SnowflakeIdUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.Credentials;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
public class CodeController {

    @Value("${node.ip}")
    private String ip;

    @Resource
    private TMusicDao musicDao;

    @Resource
    private TCodeDao codeDao;


    @RequestMapping("/createCode")
    public ResponseEntity<JSONObject> createCode(@RequestBody MusicBo musicBo){
        JSONObject jo = new JSONObject();

        if ("".equals(musicBo.getPrivateKey()) || musicBo.getPrivateKey() == null){
            jo.setCode("-1");
            jo.setMsg("钱包私钥不能为空");
            return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
        }

        if ("".equals(musicBo.getId()) || musicBo.getId() == 0){
            jo.setCode("-1");
            jo.setMsg("id不能为空,不能为0");
            return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
        }

        TMusic tMusic = musicDao.queryById(musicBo.getId());
        // 从私钥创建 Credentials 对象
        Credentials credentials = Credentials.create(musicBo.getPrivateKey());

        // 获取 Ethereum 地址
        String address = credentials.getAddress();
        if (!address.equals(tMusic.getAddress())){
            jo.setCode("-1");
            jo.setMsg("私钥与地址不匹配，禁止操作");
            return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
        }

//        SnowflakeIdUtils idWorker = new SnowflakeIdUtils(3, 1);

        String code = EncryptUtil.encryptSHA256(tMusic.getMusicName()+tMusic.getArtistName()+tMusic.getAlbumName());

        TCode exist = codeDao.queryByCode(code);
        if (exist != null){
            jo.setCode("-1");
            jo.setMsg("重复操作");
            return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
        }


        TCode tCode = new TCode();
        tCode.setCode(code);
        tCode.setCreateTime(DateUtils.getTime());
        tCode.setMusicName(tMusic.getMusicName());
        tCode.setArtistName(tMusic.getArtistName());
        tCode.setAddress(tMusic.getAddress());
        codeDao.save(tCode);

        jo.setCode("1");
        jo.setMsg("新增认证码成功");
        return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
    }

    @RequestMapping("/queryAllCodeByMusicId")
    public ResponseEntity<JSONObject> queryAllCode(@RequestBody MusicBo musicBo){
        JSONObject jo = new JSONObject();


        if ("".equals(musicBo.getId()) || musicBo.getId() == 0){
            jo.setCode("-1");
            jo.setMsg("id不能为空,不能为0");
            return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
        }

        TMusic tMusic = musicDao.queryById(musicBo.getId());
        Credentials credentials = Credentials.create(musicBo.getPrivateKey());
        // 获取 Ethereum 地址
        String address = credentials.getAddress();
        if (!address.equals(tMusic.getAddress())){
            jo.setCode("-1");
            jo.setMsg("私钥与地址不匹配，禁止操作");
            return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
        }

        List<TCode> list = codeDao.queryByName(tMusic.getMusicName());

        if (list.size() == 0){
            jo.setCode("-1");
            jo.setMsg("暂无数据");
            return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
        }
        jo.setO(list);
        jo.setCode("1");
        jo.setMsg("查询成功");
        return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
    }



//    @RequestMapping(value = "/generateQRCode", produces = MediaType.IMAGE_PNG_VALUE)
//    @CrossOrigin
//    public void generateQRCode(HttpServletResponse response,@RequestParam String code) throws WriterException, IOException {
//
//
//        String url = "http://" + ip + ":8007/query?code=" + code;
//
//        // 设置响应类型
//        response.setContentType(MediaType.IMAGE_PNG_VALUE);
//
//        // 创建 QRCodeWriter 对象
//        QRCodeWriter qrCodeWriter = new QRCodeWriter();
//
//        // 设置编码提示
//        BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, 200, 200);
//
//        // 生成图像
//        int qrCodeSize = 200;
//        BufferedImage image = new BufferedImage(qrCodeSize, qrCodeSize, BufferedImage.TYPE_INT_RGB);
//        for (int x = 0; x < qrCodeSize; x++) {
//            for (int y = 0; y < qrCodeSize; y++) {
//                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
//            }
//        }
//
//        // 将图像写入响应输出流
//        try (ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream()) {
//            ImageIO.write(image, "PNG", pngOutputStream);
//            byte[] pngData = pngOutputStream.toByteArray();
//            response.getOutputStream().write(pngData);
//        }
//    }

}
