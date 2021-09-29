package tech.vtsign.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginServerResponseDto {
    private UUID id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String organization;
    private String address;
    private boolean enabled;
    private List<Role> roles;
    private List<Permission> permissions;
}
