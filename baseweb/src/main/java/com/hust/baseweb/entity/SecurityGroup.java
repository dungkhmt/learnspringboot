package com.hust.baseweb.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class SecurityGroup {
    @Id
    @Column(name="group_id")
    private String groupId;

}

