package tech.vtsign.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginClientRequestDto {
    @Email(message = "Invalid email address")
    @NotBlank(message = "Missing Email ")
    private String email;
    @NotBlank(message = "Missing Password")
    private String password;
}
