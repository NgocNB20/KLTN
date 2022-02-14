package com.ngocnb20.travel.util;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class TokenUtil {
    static final  String SECRET_KEY="ABCABCABCABCABCABCABCABCABCABCABCABCABCABCABC";



    public static String encode(Map<String,Object> params) throws ParseException, JOSEException {
        Date expiredTime = Date.from(
                LocalDateTime.now().plusHours(1).atZone(ZoneId.systemDefault()).toInstant()
        );
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder(JWTClaimsSet.parse(params))
                .issuer("http://fa-author-server.com")//đại diện cho server
                .expirationTime(expiredTime).build();

        Payload payload = new Payload(claimsSet.toJSONObject());

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        JWSObject jwsObject = new JWSObject(header,payload);
        jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));

        return jwsObject.serialize();
    }


    //kiểm tra token đúng không ??
    public static boolean verifier(String token) throws JOSEException, ParseException {
        JWSObject jwsObject = JWSObject.parse(token);
        JWSVerifier verifier = new MACVerifier(SECRET_KEY.getBytes());
        return jwsObject.verify(verifier);

    }
    public static JWSObject decodeToken(String token)   {
        try {
            JWSObject jwsObject = JWSObject.parse(token);
            JWSVerifier verifier = new MACVerifier(SECRET_KEY.getBytes());
            boolean valid = jwsObject.verify(verifier);
            return valid ? jwsObject : null;
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void main(String[] args) throws ParseException, JOSEException {
        Map<String,Object> param = new HashMap<>();
        param.put("username","NguyenBaNgoc");
        param.put("Date","19/11/2000");
        param.put("Id",3L);
        System.out.println(new TokenUtil().encode(param));

    }
}
