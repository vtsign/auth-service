package tech.vtsign.authservice.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import tech.vtsign.authservice.model.User;

@Component
public class UserDetailServicePriciple implements UserDetailsService {
//    private LoginDao loginDao;

//    public LoginDao getLoginDao() {
//        return loginDao;
//    }

    //    @Autowired
//    public void setLoginDao(LoginDao loginDao) {
//        this.loginDao = loginDao;
//    }

    //    @Autowired
//    public void setLoginDao(LoginDao loginDao) {
//        this.loginDao = loginDao;
//    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        Login login =loginDao.findByUsername(s);
        User user = new User("cxtuan","123456","ADMIN,STUDENT");

//        if (login == null) {
//            throw new UsernameNotFoundException("User not found");
//        }
        UserDetailPriciple userDetailPriciple = new UserDetailPriciple(user);
        return userDetailPriciple;
    }
}
