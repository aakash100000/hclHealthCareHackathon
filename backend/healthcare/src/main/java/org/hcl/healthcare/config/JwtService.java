//package org.hcl.healthcare.config;
//
//import com.nimbusds.jose.*;
//import com.nimbusds.jose.crypto.MACSigner;
//import com.nimbusds.jose.crypto.MACVerifier;
//import com.nimbusds.jwt.JWTClaimsSet;
//import com.nimbusds.jwt.SignedJWT;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
//@Service
//public class JwtService {
//
//    @Value("${jwt.secret}")
//    private String secretKey;
//
//    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour
//
//    //using the Nimbus JOSE + JWT library to manually generate a JWT token signed using HMAC SHA-256 (HS256).
//    public String generateToken(String username, String role) throws JOSEException {
//        //Uses the secretKey (shared secret) to initialize a MAC-based signer.
//       // MACSigner uses HMAC-SHA256 (HS256).
//        JWSSigner signer = new MACSigner(secretKey.getBytes());
//
//        //Build the claims
//        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
//                .subject(username)//standard JWT claim (sub)
//                .claim("role", role)
//                .expirationTime(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .build();
//
//        JWSObject jwsObject = new JWSObject( //JWSObject is the actual JWT structure:
//                new JWSHeader(JWSAlgorithm.HS256),  //JWSHeader specifies the signing algorithm (HS256)
//                new Payload(claimsSet.toJSONObject())//Payload is the claims, serialized to JSON
//        );
//        jwsObject.sign(signer);//This calculates the HMAC SHA-256 signature over the header and payload using the secretKey.
//
//        return jwsObject.serialize();//Converts the JWSObject to a compact JWT string:
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            JWSObject jwsObject = JWSObject.parse(token);
//            JWSVerifier verifier = new MACVerifier(secretKey.getBytes());
//
//            if (!jwsObject.verify(verifier)) {
//                return false;
//            }
//
//            JWTClaimsSet claimsSet = JWTClaimsSet.parse(jwsObject.getPayload().toJSONObject());
//            return new Date().before(claimsSet.getExpirationTime());
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    public String extractUsername(String token) {
//        try {
//            return SignedJWT.parse(token).getJWTClaimsSet().getSubject();
//
////            JWSObject jwsObject = JWSObject.parse(token);
////            JWTClaimsSet claimsSet = JWTClaimsSet.parse(jwsObject.getPayload().toJSONObject());
////            return claimsSet.getSubject();
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    public String extractRole(String token) {
//        try {
//            JWSObject jwsObject = JWSObject.parse(token);
//            JWTClaimsSet claimsSet = JWTClaimsSet.parse(jwsObject.getPayload().toJSONObject());
//            return (String) claimsSet.getClaim("role");
//        } catch (Exception e) {
//            return null;
//        }
//    }
//}
