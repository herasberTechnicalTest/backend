package com.herasber.TechnicalTest.platform.professional.interfaces;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;


@RestController
@RequestMapping("/api/imagekit")
public class ImageKitAuthController {

    @Value("${imagekit.privateKey}")
    private String privateKey;

    @Value("${imagekit.publicKey}")
    private String publicKey;

    @Value("${imagekit.urlEndpoint}")
    private String urlEndpoint;

    @GetMapping("/auth")
    public Map<String, Object> auth() throws Exception {
        String token = java.util.UUID.randomUUID().toString();
        long expire = (System.currentTimeMillis() / 1000L) + 300; // 5 min

        String message = token + expire;
        javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA1");
        mac.init(new javax.crypto.spec.SecretKeySpec(privateKey.getBytes(java.nio.charset.StandardCharsets.UTF_8), "HmacSHA1"));
        byte[] raw = mac.doFinal(message.getBytes(java.nio.charset.StandardCharsets.UTF_8));

        StringBuilder sb = new StringBuilder();
        for (byte b : raw) sb.append(String.format("%02x", b));
        String signature = sb.toString();

        Map<String,Object> body = new java.util.HashMap<>();
        body.put("token", token);
        body.put("expire", expire);
        body.put("signature", signature);
        body.put("publicKey", publicKey);
        body.put("urlEndpoint", urlEndpoint);
        return body;
    }
}