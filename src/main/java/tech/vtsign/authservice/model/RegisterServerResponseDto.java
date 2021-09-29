package tech.vtsign.authservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterServerResponseDto {
    private UUID id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String organization;
    private String address;
    private boolean enabled;
}
