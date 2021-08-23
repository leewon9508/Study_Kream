package com.icia.kream.common;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class KreamJWT {

    public static void main(String[] args) {

        String email = "gabrielle8@naver.com"; // ${KreamMail.mid}
//        String jwt = KreamJWT.createToken(email);
//        System.out.println(jwt);
        System.out.println(KreamJWT.verifyJWT(email, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyIiwiZGF0YSI6Ik15IEZpcnN0IEpXVCAhISIsImV4cCI6NTIyNzEzMDYwOH0._RbDFNd0H2SyLkkS-FVY1_RkXwaf7sVQOmYPQFRql9k")); // 토큰이 만료되었거나 문제가있으면 null
    }

    //토큰 생성
    public static String createToken(String key) {

        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("data", "My First JWT !!");

        // 토큰 유효 시간 (100시간)
        long expiredTime = 1000 * 60L * 60L * 100L;

        // 토큰 만료 시간
        Date ext = new Date();
        ext.setTime(ext.getTime() + expiredTime);

        // 토큰 Builder
        String jwt = Jwts.builder()
                .setHeader(headers) // Headers 설정
                .setClaims(payloads) // Claims 설정
                .setSubject("user") // 토큰 용도
                .setExpiration(ext) // 토큰 만료 시간 설정
                .signWith(SignatureAlgorithm.HS256, key.getBytes()) // HS256과 Key로 Sign
                .compact(); // 토큰 생성
        System.out.println("토큰 : " + payloads);
        return jwt;
    }

    //토큰 검증
    public static boolean verifyJWT(String key, String jsonWebToken) {
        Map<String, Object> claimMap = null;
        try {
            claimMap = Jwts.parser()
                    .setSigningKey(key.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(jsonWebToken)
                    .getBody();

        } catch (Exception e) { // 토큰이 만료되었을 경우
        	return false;
        }
        
        if (claimMap.isEmpty())
        	return false;
        return true;
    }
}
