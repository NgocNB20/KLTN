package com.ngocnb20.travel.controller;


import com.ngocnb20.travel.model.entity.Member;
import com.ngocnb20.travel.service.impl.UserDetailServiceImpl;
import com.ngocnb20.travel.util.TokenUtil;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/login")
@CrossOrigin("*")
public class LoginResource {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserDetailServiceImpl userDetailsService;

    @GetMapping
    public String getData(){
        System.out.println("get login");
        return "abcd";
    }
    @PostMapping
    public ResponseEntity<?> submitLogin(@RequestBody @Valid Member member) throws ParseException, JOSEException {
        System.out.println("Login post");
        UserDetails userDetails = userDetailsService.loadUserByUsername(member.getEmail());
        if (Objects.nonNull(userDetails)
                && passwordEncoder.matches(member.getPassword(),userDetails.getPassword())){
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("userId",userDetails.getUsername());
            //FIXME:GET FROM DB
            objectMap.put("role",new String[]{"ROLE_ADMIN","ROLE_USER"});

            String token = TokenUtil.encode(objectMap);
            return ResponseEntity.ok(token);
        }
        System.out.println("Login post");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

    }
}
