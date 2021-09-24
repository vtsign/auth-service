package tech.vtsign.authservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class User {
    private String username;
    private String password;
    private String roles;
    public List<String> getListRoles(){
        if(this.roles.length()>0)
            return Arrays.asList(roles.split(","));
        return new ArrayList<>();
    }
}
