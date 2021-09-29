package tech.vtsign.authservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginClientResponseDto {
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("access_token")
    private String accessToken;
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String organization;
    private String address;
    private boolean enabled;
}
