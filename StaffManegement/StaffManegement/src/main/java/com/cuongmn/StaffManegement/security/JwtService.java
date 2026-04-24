package com.cuongmn.StaffManegement.security;

import com.cuongmn.StaffManegement.exception.AppException;
import com.cuongmn.StaffManegement.exception.ErrorCode;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.signer-key}")
    private String signerKey;

    @Value("${jwt.expiration-seconds}")
    private long expirationSeconds;

    public String generateToken(UserDetails userDetails) {
        try {
            JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(userDetails.getUsername())
                    .issuer("company-api")
                    .issueTime(new Date())
                    .expirationTime(Date.from(Instant.now().plusSeconds(expirationSeconds)))
                    .claim("roles", userDetails.getAuthorities())
                    .build();

            SignedJWT signedJWT = new SignedJWT(header, claimsSet);

            signedJWT.sign(new MACSigner(signerKey.getBytes()));

            return signedJWT.serialize();

        } catch (JOSEException exception) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }
    }

    public String extractUsername(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getSubject();
        } catch (ParseException exception) {
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);

        return username.equals(userDetails.getUsername())
                && verifySignature(token)
                && !isTokenExpired(token);
    }

    private boolean verifySignature(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(signerKey.getBytes());

            return signedJWT.verify(verifier);

        } catch (Exception exception) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();

            return expirationTime.before(new Date());

        } catch (ParseException exception) {
            return true;
        }
    }
}