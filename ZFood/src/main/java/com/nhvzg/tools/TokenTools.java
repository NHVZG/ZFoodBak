package com.nhvzg.tools;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.Map;

/**
 * Created by NHVZG on 2018/3/7.
 */
public class TokenTools {
    private  static  SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256 ;
    private static byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("NHVZG");//·þÎñ¶ËÃÜÔ¿±àÂë
    public static String generToken(Map data, long ttlMillis){
        long nowMillis = System. currentTimeMillis();
        Date now = new Date(nowMillis);
        SecretKey key = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        JwtBuilder builder = Jwts. builder()
                .setIssuedAt(now)
                .claim("X-data",data)
                .signWith(signatureAlgorithm, key);
        if (ttlMillis >= 0){
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date( expMillis);
            builder.setExpiration( exp);
        }
        return builder.compact();
    }

    public static Map parseJWT(String jwt) throws Exception{
        SecretKey key = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        Claims claims = Jwts. parser()
                .setSigningKey( key)
                .parseClaimsJws( jwt).getBody();
        return (Map) claims.get("X-data");
    }
}
