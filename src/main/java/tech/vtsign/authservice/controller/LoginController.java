package tech.vtsign.authservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.vtsign.authservice.exception.TokenMissingException;
import tech.vtsign.authservice.model.*;
import tech.vtsign.authservice.service.JwtService;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginClientRequestDto loginClientRequestDto) {
        LoginClientResponseDto loginClientResponseDto = jwtService.login(loginClientRequestDto);
        return ResponseEntity.ok(loginClientResponseDto);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterClientRequestDto registerClientRequestDto) {
        RegisterClientResponseDto register = jwtService.register(registerClientRequestDto);
        return ResponseEntity.ok(register);
    }

    @PostMapping("/jwt")
    public ResponseEntity<?> generateJwt(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new TokenMissingException("Must add header token Authorization Bearer: YOUR_TOKEN");

        }
        String accessToken = authHeader.substring("Bearer ".length());
        String jwt = jwtService.createJwt(accessToken);
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
        String refreshToken = refreshTokenRequestDto.getRefreshToken();
        RefreshTokenResponseDto responseDto = jwtService.refreshToken(refreshToken);
        return ResponseEntity.ok(responseDto);
    }
}
