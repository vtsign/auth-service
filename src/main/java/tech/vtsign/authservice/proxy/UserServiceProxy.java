package tech.vtsign.authservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tech.vtsign.authservice.model.LoginServerRequestDto;
import tech.vtsign.authservice.model.LoginServerResponseDto;
import tech.vtsign.authservice.model.RegisterServerRequestDto;
import tech.vtsign.authservice.model.RegisterServerResponseDto;

@FeignClient(name = "user-service")
public interface UserServiceProxy {
    @GetMapping("/user/{email}")
    LoginServerResponseDto retrieveUser(@PathVariable String email);

    @PostMapping("/user/register")
    RegisterServerResponseDto register(@RequestBody RegisterServerRequestDto registerServerRequestDto);

    @PostMapping("/user/login")
    LoginServerResponseDto login(@RequestBody LoginServerRequestDto loginServerRequestDto);
}
