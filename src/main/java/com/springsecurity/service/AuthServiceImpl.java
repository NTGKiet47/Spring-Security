package com.springsecurity.service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.springsecurity.dto.request.AuthRequestDto;
import com.springsecurity.dto.request.IntrospectTokenRequestDto;
import com.springsecurity.dto.response.AuthResponseDto;
import com.springsecurity.dto.response.IntrospectTokenResponseDto;
import com.springsecurity.entity.Role;
import com.springsecurity.entity.User;
import com.springsecurity.exception.AppException;
import com.springsecurity.exception.ErrorCode;
import com.springsecurity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    // in order to protect the signature key, which is the main thing to generate the jwt, the signer key
    // should be stored in the application.yaml file. Hence, when deploying the server, the devops team will
    // create another key
    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    public IntrospectTokenResponseDto introspectToken(IntrospectTokenRequestDto introspectTokenRequestDto)
            throws JOSEException, ParseException {

        String token = introspectTokenRequestDto.getToken();

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        boolean isVarified = signedJWT.verify(verifier);

        return (expiryTime.after(new Date()) && isVarified)
                ? IntrospectTokenResponseDto.builder().valid(true).build()
                : IntrospectTokenResponseDto.builder().valid(false).build();

    }

    @Override
    public AuthResponseDto authenticate(AuthRequestDto authRequestDto) {
        User user = userRepository.findByUserName(authRequestDto.getUserName())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND));

        boolean authenticated = passwordEncoder.matches(authRequestDto.getPassWord(), user.getPassWord());

        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        String token = generateToken(user.getUserName(), user.getRoles());

        return AuthResponseDto.builder()
                .token(token)
                .build();
    }

    private String generateToken(String userName, Set<Role> roles) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(userName)
                .issuer("giakiet Company")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope", buildScope(roles))
                .build();

        Payload payload = new Payload(claimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.info("---------- Could not create token: " + e);
            throw new RuntimeException(e);
        }
    }

    private String buildScope(Set<Role> roles){
        StringJoiner stringJoiner = new StringJoiner(" ");
        if(!CollectionUtils.isEmpty(roles)){
            roles.forEach(role -> stringJoiner.add(role.getRoleName()));
        }
        return stringJoiner.toString();
    }

}
