package com.example.Sekai.controller;

import com.example.Sekai.dao.TMusicDao;
import com.example.Sekai.entity.JSONObject;
import com.example.Sekai.entity.TMusic;
import com.example.Sekai.entity.bo.MusicBo;
import com.example.Sekai.utils.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.Credentials;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class MusicController {

    @Resource
    private TMusicDao TMusicDao;

    @RequestMapping("/music/add")
    public ResponseEntity<JSONObject> add(@RequestBody MusicBo musicBo){
        JSONObject jo = new JSONObject();

        if ("".equals(musicBo.getMusicName()) || musicBo.getMusicName() == null){
            jo.setCode("-1");
            jo.setMsg("音乐名不能为空");
            return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
        }
        if ("".equals(musicBo.getAlbumName()) || musicBo.getAlbumName() == null){
            jo.setCode("-1");
            jo.setMsg("专辑名不能为空");
            return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
        }
        if ("".equals(musicBo.getArtistName()) || musicBo.getArtistName() == null){
            jo.setCode("-1");
            jo.setMsg("艺术家名不能为空");
            return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
        }
        if ("".equals(musicBo.getAddress()) || musicBo.getAddress() == null){
            jo.setCode("-1");
            jo.setMsg("钱包地址不能为空");
            return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
        }
        if ("".equals(musicBo.getPrivateKey()) || musicBo.getPrivateKey() == null){
            jo.setCode("-1");
            jo.setMsg("钱包私钥不能为空");
            return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
        }


        // 从私钥创建 Credentials 对象
        Credentials credentials = Credentials.create(musicBo.getPrivateKey());

        // 获取 Ethereum 地址
        String address = credentials.getAddress();
        if (!address.equals(musicBo.getAddress())){
            jo.setCode("-1");
            jo.setMsg("私钥与地址不匹配，禁止操作");
            return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
        }
        TMusic tMusic = new TMusic();
        tMusic.setMusicName(musicBo.getMusicName());
        tMusic.setAlbumName(musicBo.getAlbumName());
        tMusic.setArtistName(musicBo.getArtistName());
        tMusic.setAddress(musicBo.getAddress());
        tMusic.setCreateTime(DateUtils.getTime());
        TMusicDao.save(tMusic);

        jo.setO("");
        jo.setMsg("新增音乐成功");
        jo.setCode("1");
        return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
    }



    @RequestMapping("/queryMusics")
    public ResponseEntity<JSONObject> queryMusics(){
        JSONObject jo = new JSONObject();

        List<TMusic> music = TMusicDao.queryAll();
        if (music.size() == 0){
            jo.setO("");
            jo.setMsg("暂无数据");
            jo.setCode("-1");
            return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
        }

        jo.setO(music);
        jo.setMsg("查询成功");
        jo.setCode("1");
        return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
    }



}
