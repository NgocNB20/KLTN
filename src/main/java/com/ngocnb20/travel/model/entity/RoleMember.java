package com.ngocnb20.travel.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**master data à thông tin không cần thay đổi không thực hiện CRUD vói master data chỉ read **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RoleMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id" , nullable = false,referencedColumnName = "id")
    private Role role;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id" , nullable = false ,referencedColumnName = "id")
    private Member member;
}
