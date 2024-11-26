package com.example.Sekai.utils;

import com.example.Sekai.entity.block.EthWallet;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Sign.SignatureData;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

public class EthUtils {

    public static void main(String[] args) {
        try {
            // 1. 生成公钥和私钥对
            ECKeyPair keyPair = Keys.createEcKeyPair();
            BigInteger privateKey = keyPair.getPrivateKey();
            BigInteger publicKey = keyPair.getPublicKey();

            System.out.println("Private Key: " + privateKey.toString(16));
            System.out.println("Public Key: " + publicKey.toString(16));

            // 2. 计算以太坊地址
            String address = publicKeyToAddress(publicKey);
            System.out.println("Address: " + address);

            // 3. 签名和验证
            String message = "Hello blockchain";
            SignatureData signature = signMessage(message, privateKey);
            System.out.println("Signature: " +
                    bytesToHex(signature.getR()) +
                    bytesToHex(signature.getS()) +
                    String.format("%02x", signature.getV()[0])  // 处理 byte 数组的第一个字节
            );

            String signatureStr = bytesToHex(signature.getR()) + bytesToHex(signature.getS()) + String.format("%02x", signature.getV()[0]);
            System.out.println(signatureStr);

            boolean isValid = verifySignature(message, signature, address);
            System.out.println("Is signature valid: " + isValid);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getSignStr(SignatureData signature){
        return  bytesToHex(signature.getR()) + bytesToHex(signature.getS()) + String.format("%02x", signature.getV()[0]);
    }

    public static EthWallet getWallet() throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchProviderException {
        EthWallet wallet = new EthWallet();
        ECKeyPair keyPair = Keys.createEcKeyPair();
        BigInteger privateKey = keyPair.getPrivateKey();
        BigInteger publicKey = keyPair.getPublicKey();
        String address = publicKeyToAddress(publicKey);
        wallet.setPrivateKey(privateKey.toString(16));
        wallet.setPublicKey(publicKey.toString(16));
        wallet.setAddress(address);
        return wallet;
    }

    public static String publicKeyToAddress(BigInteger publicKey) {
        return "0x" + Keys.getAddress(publicKey);
    }


    public static SignatureData signMessage(String message, BigInteger privateKey) throws Exception {
        byte[] messageHash = Hash.sha3(message.getBytes());
        ECKeyPair keyPair = ECKeyPair.create(privateKey);
        return Sign.signMessage(messageHash, keyPair, false);
    }

    public static boolean verifySignature(String message, SignatureData signature, String address) {
        try {
            byte[] messageHash = Hash.sha3(message.getBytes());
            BigInteger recoveredPublicKey = Sign.signedMessageHashToKey(messageHash, signature);
            String keysAddress = publicKeyToAddress(recoveredPublicKey);
            System.out.println("keysAddress: " + keysAddress);
            return keysAddress.equals(address);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }


    public static SignatureData stringToSignatureData(String signatureStr) {
        // Ensure the string length is valid (r + s + v should be hex)
        if (signatureStr.length() < 130) {
            throw new IllegalArgumentException("Invalid signature string length.");
        }

        // Extract r, s, and v components
        String rHex = signatureStr.substring(0, 64); // First 64 characters for r
        String sHex = signatureStr.substring(64, 128); // Next 64 characters for s
        String vHex = signatureStr.substring(128, 130); // Last 2 characters for v

        // Convert hex to byte arrays
        byte[] r = hexToBytes(rHex);
        byte[] s = hexToBytes(sHex);
        byte[] v = new byte[]{(byte) Integer.parseInt(vHex, 16)}; // Convert to byte

        return new SignatureData(v, r, s);
    }

    // Helper method to convert hex string to byte array
    public static byte[] hexToBytes(String hex) {
        int length = hex.length();
        byte[] data = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }




}
