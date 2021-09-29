package tech.vtsign.authservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RefreshTokenRequestDto {
    @JsonProperty("refresh_token")
    private String refreshToken;
}
