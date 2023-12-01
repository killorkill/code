package com.example.banlinhkien.controller;


import com.example.banlinhkien.models.AccountDTO;
import com.example.banlinhkien.service.AccountService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class RegisterController {


    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping(value = "/register")
    public String register(Model model) {
        AccountDTO accountDTO = new AccountDTO();
        model.addAttribute("account", accountDTO);
        return "register";
    }

    @PostMapping(value = "/register")
    public String register(HttpServletRequest request, @ModelAttribute(name = "account") AccountDTO accountDTO) {

        if (!accountDTO.getPassword().equals(accountDTO.getRePassword())) {
            request.setAttribute("errorpassword", "Mật khẩu không trùng nhau");
            request.setAttribute("status", "Đăng kí thất bại");
            return "register";
        }

        AccountDTO account = accountService.getByUsernameOrEmail(accountDTO.getUsername(), accountDTO.getEmail());

        if (account == null) {
            accountDTO.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
            List<String> roles = new ArrayList<String>();
            roles.add("User");
            accountDTO.setActive(true);
            accountDTO.setRoles(roles);

            String key = RandomStringUtils.randomAlphanumeric(10);
            accountDTO.setKey(key);

            accountService.addOrUpdate(accountDTO);


            request.setAttribute("status", "Đăng kí thành công");
        } else {
            if (account.getUsername().equalsIgnoreCase(accountDTO.getUsername())) {
                request.setAttribute("errusername", "Tài khoản đã tồn tại");
            }

            if (account.getEmail().equalsIgnoreCase(accountDTO.getEmail())) {
                request.setAttribute("erremail", "Email đã tồn tại");
            }

            request.setAttribute("status", "Đăng kí thất bại");
        }

        return "register";
    }

    @GetMapping(value = "/XacNhanEmail")
    public String validateEmail(@RequestParam(value = "email", required = true) String email,
                                @RequestParam(value = "key", required = true) String key) {

        AccountDTO accountDTO = accountService.getByEmail(email);
        if (accountDTO.getKey().equals(key)) {
            accountDTO.setActive(true);
            accountService.addOrUpdate(accountDTO);
        }
        return "redirect:/home";
    }

    @GetMapping(value = "/resetPass")
    public String resetPass(Model model) {
        AccountDTO accountDTO = new AccountDTO();
        model.addAttribute("account", accountDTO);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getName().equals("anonymousUser")) {
            return "login";
        }
        return "resetPassword";
    }

    @PostMapping(value = "/resetPass")
    public String resetPass(HttpServletRequest request, @ModelAttribute(name = "account") AccountDTO accountDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        request.setAttribute("authentication", authentication.getName());

        if (accountDTO.getPassword().equals(accountDTO.getRePassword())) {
            AccountDTO account = accountService.getByUsername(authentication.getName());
            if(!ObjectUtils.isEmpty(account)){
              if(passwordEncoder.matches(accountDTO.getOldPassword(), account.getPassword())){
                  account.setPassword(passwordEncoder.encode(accountDTO.getPassword()));
                  accountService.addOrUpdate(account);
                  request.setAttribute("status", "Đổi mật khẩu thành công");
              }else {
                  request.setAttribute("errorOldPassword", "Mật khẩu cũ không đúng");
                  request.setAttribute("status", "Đổi mật khẩu thất bại");
              }
            }
        } else {
            request.setAttribute("errorPassword", "Mật khẩu không trùng nhau");
            request.setAttribute("status", "Đổi mật khẩu thất bại");
        }

        return "resetPassword";
    }
}
