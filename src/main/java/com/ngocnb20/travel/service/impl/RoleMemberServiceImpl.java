package com.ngocnb20.travel.service.impl;

import com.ngocnb20.travel.model.entity.Member;
import com.ngocnb20.travel.model.entity.RoleMember;
import com.ngocnb20.travel.repository.RoleMemberRepository;
import com.ngocnb20.travel.service.RoleMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMemberServiceImpl implements RoleMemberService {

    @Autowired
    RoleMemberRepository roleMemberRepository;

    @Override
    public List<RoleMember> getMemberRole(Member member) {
        return roleMemberRepository.findByMember(member);
    }
}
