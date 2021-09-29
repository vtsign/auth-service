package tech.vtsign.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterServerRequestDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String organization;
    private String address;
}
