package com.ngocnb20.travel.model.entity;

import lombok.*;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_account")
    private Long id;
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    @NotBlank
    @Nationalized
    private String username;
    @Size(min = 8)
    private String password;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role",referencedColumnName = "id")
    private Role role;

}
