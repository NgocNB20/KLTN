package com.ngocnb20.travel.model.entity;

import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Nationalized // NVarchar to save UTF-8
    @NotBlank
    private String name;
    @OneToMany(mappedBy = "role")
    private Set<Account> accounts=new HashSet<>();

}
