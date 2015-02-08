package com.sechelc.cloud.manager.login;

import com.sechelc.cloud.manager.account.Account;
import com.sechelc.cloud.manager.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Created by sechelc on 07.02.2015.
 */
@RestController
public class LoginController {
    @Autowired
    private AccountRepository accountRepository;

    @Inject
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "login/android", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String getGraphData(@RequestParam("userName") String username, @RequestParam("password") String password, @RequestParam("truckNo") String truckNo) {
        Account byEmail = accountRepository.findByEmail(username);
        if (byEmail != null && passwordEncoder.matches(password, byEmail.getPassword())) {
            return "test";
        } else return "errors:" + username +"|"+ password +"|"+ truckNo + "|" + password;
    }

}
