package com.nhvzg.test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by NHVZG on 2018/3/7.
 */

public class JWTtest {
    public String createJWT(String id, String subject, long ttlMillis) throws Exception {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256 ;
        long nowMillis = System. currentTimeMillis();
        Date now = new Date( nowMillis);
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("NHVZG");
        SecretKey key = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        Map map=new HashMap();
        map.put("name","NHVZG");
        map.put("age","23");
        JwtBuilder builder = Jwts. builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .claim("data",map)
                .signWith(signatureAlgorithm, key);
        if (ttlMillis >= 0){
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date( expMillis);
            builder.setExpiration( exp);
        }
        return builder.compact();
    }
    public Claims parseJWT(String jwt) throws Exception{
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256 ;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("NHVZG");
        SecretKey key = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        Claims claims = Jwts. parser()
                .setSigningKey( key)
                .parseClaimsJws( jwt).getBody();
        return claims;
    }

    @Test
    public void Token() throws Exception {
        String token=createJWT("2341236","Coder",8000000);
        System.out.println(token);
        System.out.println(parseJWT(token));
        Map map= (Map) parseJWT(token).get("data");
        System.out.println(map.get("name"));
    }
}
