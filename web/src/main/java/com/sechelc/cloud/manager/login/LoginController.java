package com.sechelc.cloud.manager.login;

import com.sechelc.cloud.manager.account.Account;
import com.sechelc.cloud.manager.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sechelc on 07.02.2015.
 */
@RestController
public class LoginController {
    @Autowired
    private AccountRepository accountRepository;


    @RequestMapping(value = "login/android", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public String getGraphData(@RequestParam("userName") String username, @RequestParam("password") String password, @RequestParam("truckNo") String truckNo) {
        Account byEmail = accountRepository.findByEmail(username);
        if (byEmail != null && byEmail.getPassword().equals(password)) {
            return "test";
        } else return null;
    }

}
