package tech.vtsign.authservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterClientRequestDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String organization;
    private String address;

}
