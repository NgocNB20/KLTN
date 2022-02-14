package com.ngocnb20.travel.service.impl;

import com.ngocnb20.travel.model.entity.Member;
import com.ngocnb20.travel.repository.MemberRepository;
import com.ngocnb20.travel.service.MemberService;
import com.ngocnb20.travel.util.TokenUtil;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Override
    public Optional<Member> getMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

   @Override
    public boolean getByEmail(String email)  {
        Optional<Member> member = memberRepository.findByEmail(email);
        if (member.isPresent()){
           Member members = member.get();
            Map<String,Object> param = new HashMap<>();
            param.put("username",members.getEmail());
            param.put("Date","19/11/2000");

            param.put("Id",3L);
            String token = null;
            try {
                token = TokenUtil.encode(param);
            } catch (Exception e) {
                e.printStackTrace();
            }
            members.setResetPassword(token);
            Member member1 = new Member();
            member1.setId(4L);
            member1.setEmail(member.get().getEmail());
            member1.setPassword(member.get().getPassword());
            member1.setResetPassword(member.get().getResetPassword());
            memberRepository.save(member1);
            //GỬI MAIL
            return true;
        }
        return false;

    }

}
