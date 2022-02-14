package com.ngocnb20.travel.service;

import com.ngocnb20.travel.model.entity.Member;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;
import java.util.Optional;

public interface MemberService extends BaseService {
    Optional<Member> getMemberByEmail(String email);
    boolean getByEmail(String email) throws ParseException, JOSEException;

}
