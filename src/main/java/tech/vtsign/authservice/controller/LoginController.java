package tech.vtsign.authservice.controller;


import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import tech.vtsign.authservice.exception.ExceptionResponse;
import tech.vtsign.authservice.exception.TokenMissingException;
import tech.vtsign.authservice.model.*;
import tech.vtsign.authservice.service.JwtService;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final JwtService jwtService;


    @Operation(summary = "Login account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login successfully",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = LoginClientResponseDto.class))
                    }),
            @ApiResponse(responseCode = "423", description = "User inactive",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
                    }),
            @ApiResponse(responseCode = "401", description = "Invalid email password",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
                    }),
            @ApiResponse(responseCode = "419", description = "Invalid email format",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
                    }),
            @ApiResponse(responseCode = "419", description = "Email or password missing",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
                    }),
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginClientRequestDto loginClientRequestDto) {
        LoginClientResponseDto loginClientResponseDto = jwtService.login(loginClientRequestDto);
        return ResponseEntity.ok(loginClientResponseDto);
    }


    @Operation(summary = "Register account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Success, user registered",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = RegisterClientResponseDto.class))
                    }),
            @ApiResponse(responseCode = "419", description = "Missing require field see message for more details",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
                    }),
            @ApiResponse(responseCode = "409", description = "Email is already in use",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
                    })
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterClientRequestDto registerClientRequestDto) {
        RegisterClientResponseDto register = jwtService.register(registerClientRequestDto);
        return ResponseEntity.ok(register);
    }

    @Hidden
    @PostMapping("/jwt")
    public ResponseEntity<?> generateJwt(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new TokenMissingException("Must add header token Authorization Bearer: YOUR_TOKEN");

        }
        String accessToken = authHeader.substring("Bearer ".length());
        String jwt = jwtService.createJwt(accessToken);
        return ResponseEntity.ok(jwt);
    }

    @Operation(summary = "Refresh token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = RefreshTokenResponseDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Token invalid",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
                    }),
            @ApiResponse(responseCode = "401", description = "Token expired",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))
                    }),
    })
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
        String refreshToken = refreshTokenRequestDto.getRefreshToken();
        RefreshTokenResponseDto responseDto = jwtService.refreshToken(refreshToken);
        return ResponseEntity.ok(responseDto);
    }
}
