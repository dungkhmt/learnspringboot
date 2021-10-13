package com.hust.baseweb.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name="user_login")
@Table(name="user_login")
public class UserLogin {
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Id
    @Column(name="user_login_id")
    private String userLoginId;

    @Column(name="password")
    private String password;

    @Column(name="created_stamp")
    private Date createdStamp;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_login_security_group",
            joinColumns = @JoinColumn(name = "user_login_id", referencedColumnName = "user_login_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id", referencedColumnName = "group_id"))

    private List<SecurityGroup> groups;


}
