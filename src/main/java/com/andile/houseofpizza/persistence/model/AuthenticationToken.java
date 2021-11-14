package com.andile.houseofpizza.persistence.model;

import javax.persistence.*;


import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tokens")
public class AuthenticationToken implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private String token;

    @Column(name = "created_date")
    private Date createdDate;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

}
