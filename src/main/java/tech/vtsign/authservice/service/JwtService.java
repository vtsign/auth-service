package tech.vtsign.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tech.vtsign.authservice.model.*;
import tech.vtsign.authservice.proxy.UserServiceProxy;
import tech.vtsign.authservice.util.JwtUtil;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtUtil utils;
    private final UserServiceProxy userServiceProxy;

    public RegisterClientResponseDto register(RegisterClientRequestDto registerClientRequestDto) {
        RegisterServerRequestDto registerServerRequestDto = new RegisterServerRequestDto();
        BeanUtils.copyProperties(registerClientRequestDto, registerServerRequestDto);
        RegisterServerResponseDto registerServerResponseDto = userServiceProxy.register(registerServerRequestDto);
        RegisterClientResponseDto registerClientResponseDto = new RegisterClientResponseDto();
        BeanUtils.copyProperties(registerServerResponseDto, registerClientResponseDto);
        return registerClientResponseDto;
    }

    public LoginClientResponseDto login(LoginClientRequestDto loginClientRequestDto) {
        LoginServerRequestDto loginServerRequestDto = new LoginServerRequestDto();
        BeanUtils.copyProperties(loginClientRequestDto, loginServerRequestDto);
        LoginServerResponseDto loginServerResponseDto = userServiceProxy.login(loginServerRequestDto);
        LoginClientResponseDto loginClientResponseDto = new LoginClientResponseDto();
        String accessToken = utils.generateAccessToken(loginServerResponseDto.getEmail());
        String refreshToken = utils.generateRefreshToken(loginServerResponseDto.getEmail());
        loginClientResponseDto.setAccessToken(accessToken);
        loginClientResponseDto.setRefreshToken(refreshToken);
        BeanUtils.copyProperties(loginServerResponseDto, loginClientResponseDto);
        return loginClientResponseDto;
    }

    public String createJwt(String accessToken) {
        String email = utils.getUsernameFromToken(accessToken);
        LoginServerResponseDto loginServerResponseDto = userServiceProxy.retrieveUser(email);
        return utils.generateAccessTokenObject(loginServerResponseDto);
    }

    public RefreshTokenResponseDto refreshToken(String refreshToken) {
        String email = utils.getUsernameFromToken(refreshToken);
        String newAccessToken = utils.generateAccessToken(email);
        String newRefreshToken = utils.generateRefreshToken(email);
        return RefreshTokenResponseDto.builder().accessToken(newAccessToken).refreshToken(newRefreshToken).build();
    }
}

