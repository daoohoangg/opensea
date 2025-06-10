package com.example.authservice.service;

import org.springframework.stereotype.Service;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Sign;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SignatureException;
import java.util.Arrays;
@Service
public class VerifyServices {
    public boolean verify(String address, String signature, String message) {
        String prefixedMsg = "\u0019Ethereum Signed Message:\n" + message.length() + message;
        byte[] msgHash = Hash.sha3(prefixedMsg.getBytes(StandardCharsets.UTF_8));
        Sign.SignatureData sigData = parseSignature(signature);
        try {
            BigInteger publicKey = Sign.signedMessageToKey(msgHash, sigData);
            String recoveredAddress = "0x" + Keys.getAddress(publicKey);
            return recoveredAddress.equalsIgnoreCase(address);
        } catch (SignatureException e) {
            e.printStackTrace(); // hoặc log lỗi
            return false;
        }
    }

    private Sign.SignatureData parseSignature(String sig) {
        byte[] sigBytes = Numeric.hexStringToByteArray(sig);
        byte v = sigBytes[64];
        if (v < 27) v += 27;

        byte[] r = Arrays.copyOfRange(sigBytes, 0, 32);
        byte[] s = Arrays.copyOfRange(sigBytes, 32, 64);
        return new Sign.SignatureData(v, r, s);
    }

}
