package com.example.Sekai.controller;

import com.example.Sekai.dao.TChainDataDao;
import com.example.Sekai.dao.TCodeDao;
import com.example.Sekai.dao.TMusicDao;
import com.example.Sekai.entity.*;
import com.example.Sekai.entity.bo.ChainDataBo;
import com.example.Sekai.utils.DateUtils;
import com.example.Sekai.utils.EncryptUtil;
import com.example.Sekai.utils.EthUtils;
import com.example.Sekai.utils.PendingUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.web3j.crypto.Sign;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@RestController
public class ChainController {

    @Value("${node.ip}")
    private String ip;

    @Resource
    private TCodeDao codeDao;

    @Resource
    private TChainDataDao chainDataDao;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private TMusicDao musicDao;


    @RequestMapping("/toChain")
    public ResponseEntity<JSONObject> toChain(@RequestBody ChainDataBo chainDataBo) throws Exception {
        JSONObject jo = new JSONObject();

        if ("".equals(chainDataBo.getCode()) || chainDataBo.getCode() == null){
            jo.setCode("-1");
            jo.setMsg("认证码不能为空");
            return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
        }

        if ("".equals(chainDataBo.getPrivateKey()) || chainDataBo.getPrivateKey() == null){
            jo.setCode("-1");
            jo.setMsg("钱包私钥不能为空");
            return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
        }

//        if ("".equals(chainDataBo.getContent()) || chainDataBo.getContent() == null){
//            jo.setCode("-1");
//            jo.setMsg("内容不能为空");
//            return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
//        }
        List<TChainData> exist = chainDataDao.queryByCode(chainDataBo.getCode());
        if (!exist.isEmpty()){
            jo.setCode("-1");
            jo.setMsg("链上已存在");
            return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
        }

        TCode tCode = codeDao.queryByCode(chainDataBo.getCode());


        TChainData tChainData = new TChainData();
        tChainData.setFrom(tCode.getAddress());
        tChainData.setTo("system");
//        tChainData.setContent(chainDataBo.getContent());
        tChainData.setCreateTime(DateUtils.getTime());
        tChainData.setCode(tCode.getCode());
        tChainData.setMusicName(tCode.getMusicName());
//        tChainData.setProcessName(chainDataBo.getProcessName());
        tChainData.setChainStatus("0");
        tChainData.setBlockIndex("");
        tChainData.setStatus("1");

        //String jsonStr = new Gson().toJson(tChainData.toString());
        Gson gson = new GsonBuilder()
                .disableHtmlEscaping() // 禁用 HTML 转义
                .create();
        String jsonStr = gson.toJson(tChainData);


        BigInteger pri = new BigInteger(chainDataBo.getPrivateKey(), 16);

        TradeObject tradeObject = new TradeObject();
        tradeObject.setFrom(tCode.getAddress());
        tradeObject.setTo("system");
        tradeObject.setType("1");
        tradeObject.setContent(jsonStr);
        tradeObject.setJsoncreatetime(DateUtils.getTime());
        tradeObject.setObjToString(tradeObject.toString());

        Sign.SignatureData signatureData = EthUtils.signMessage(tradeObject.toString(),pri);
        String sign = EthUtils.getSignStr(signatureData);
        tradeObject.setSign(sign);

        String hashNo = PendingUtils.genTradeNo(tradeObject);
        tChainData.setHashNo(hashNo);
        //保存上链数据到数据库
        chainDataDao.save(tChainData);

        //上链发送交易
        String url = "http://" + ip + ":8001/data/trade";
        restTemplate.postForEntity(url, tradeObject, TradeObject.class);

        jo.setCode("1");
        jo.setMsg("提交交易成功");
        return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
    }


    @RequestMapping("/trade")
    public ResponseEntity<JSONObject> trade(@RequestBody ChainDataBo chainDataBo) throws Exception {
        JSONObject jo = new JSONObject();

        TCode tCode = codeDao.queryByCode(chainDataBo.getCode());

        TChainData tChainData = new TChainData();
        tChainData.setFrom(tCode.getAddress());
        tChainData.setTo(chainDataBo.getTo());
        tChainData.setContent(chainDataBo.getContent());
        tChainData.setCreateTime(DateUtils.getTime());
        tChainData.setCode(tCode.getCode());
        tChainData.setMusicName(tCode.getMusicName());
        tChainData.setChainStatus("0");
        tChainData.setBlockIndex("");
        tChainData.setStatus("1");

        Gson gson = new GsonBuilder()
                .disableHtmlEscaping() // 禁用 HTML 转义
                .create();
        String jsonStr = gson.toJson(tChainData);

        BigInteger pri = new BigInteger(chainDataBo.getPrivateKey(), 16);

        TradeObject tradeObject = new TradeObject();
        tradeObject.setFrom(tCode.getAddress());
        tradeObject.setTo(chainDataBo.getTo());
        tChainData.setContent(chainDataBo.getContent());
        tradeObject.setType("1");
        tradeObject.setContent(jsonStr);
        tradeObject.setJsoncreatetime(DateUtils.getTime());
        tradeObject.setObjToString(tradeObject.toString());

        Sign.SignatureData signatureData = EthUtils.signMessage(tradeObject.toString(), pri);
        String sign = EthUtils.getSignStr(signatureData);
        tradeObject.setSign(sign);

        String hashNo = PendingUtils.genTradeNo(tradeObject);
        tChainData.setHashNo(hashNo);

        // 保存上链数据到数据库
        chainDataDao.save(tChainData);

        // 上链发送交易
        String url = "http://" + ip + ":8001/data/trade";
        restTemplate.postForEntity(url, tradeObject, TradeObject.class);

        jo.setCode("1");
        jo.setMsg("提交交易成功");
        return new ResponseEntity<>(jo, HttpStatus.OK);
    }

    @RequestMapping("/queryCode")
    public ResponseEntity<JSONObject> queryCode(String code) {
        JSONObject jo = new JSONObject();

        if ("".equals(code) || code == null){
            jo.setCode("-1");
            jo.setMsg("认证码不能为空");
            return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
        }




        List<TChainData> tChainData = chainDataDao.queryByCode(code);
        if (tChainData.size() == 0){
            jo.setCode("-1");
            jo.setMsg("暂无数据");
            return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
        }

        String productName = tChainData.get(0).getMusicName();
        TMusic tMusic = musicDao.queryByName(productName);
        QueryCodeVo queryCodeVo = new QueryCodeVo();
        queryCodeVo.setList(tChainData);
        queryCodeVo.setMusic(tMusic);

        jo.setO(queryCodeVo);
        jo.setCode("1");
        jo.setMsg("查询成功");
        return new ResponseEntity<JSONObject>(jo, HttpStatus.OK);
    }

}
