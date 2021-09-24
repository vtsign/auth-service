package tech.vtsign.authservice.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tech.vtsign.authservice.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@AllArgsConstructor
public class UserDetailPriciple implements UserDetails {
    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        //list of permission
//        this.user.getListPermission().forEach(authority ->{
//            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority((String) authority);
//            list.add(grantedAuthority);
//        });
        // list of roles
        this.user.getListRoles().forEach(role ->{
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_"+role);
            list.add(grantedAuthority);
        });
        return list;
//        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
