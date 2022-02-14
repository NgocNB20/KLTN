package com.ngocnb20.travel.repository;

import com.ngocnb20.travel.model.entity.Member;
import com.ngocnb20.travel.model.entity.RoleMember;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleMemberRepository extends CrudRepository<RoleMember,Long> {
    List<RoleMember> findByMember(Member member);
}
