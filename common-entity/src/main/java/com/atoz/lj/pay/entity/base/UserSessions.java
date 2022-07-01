package com.atoz.lj.pay.entity.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "userSession")
public class    UserSessions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId",nullable = false)
    private Long usersId;

    @Column(name = "loginTime",nullable = false)
    private Date loginTime;

    @Column(name = "logoutTime")
    private Date logoutTime;

    private boolean isLogin;
}
