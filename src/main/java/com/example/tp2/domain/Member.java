package com.example.tp2.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name ="member_id")
    private Long id;

    private String name;

    private String userId;
    private String password;
//    @Embedded
//    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orderList = new ArrayList<>();
//    private Integer age;
//    private String email;
//    private String phoneNumber;

//    private List<Order> orderList = new ArrayList<>();


}
