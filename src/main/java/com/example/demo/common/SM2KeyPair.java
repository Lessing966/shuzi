package com.example.demo.common;

import lombok.Data;

@Data
public class SM2KeyPair {
    public SM2KeyPair(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    /**公钥*/
    private String publicKey;
    /**私钥*/
    private String privateKey;

}
