package com.example.reactchallengestudybackend2.common.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("jwt")
public class JwtProps {

    private String secret;
    private Long accessTokenExpirationPeriod;
    private Long refreshTokenExpirationPeriod;
}
