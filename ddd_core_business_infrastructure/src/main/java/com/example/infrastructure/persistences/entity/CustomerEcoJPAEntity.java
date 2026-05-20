package com.example.infrastructure.persistences.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers_eco")
public class CustomerEcoJPAEntity extends BaseAuditableEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "email", unique = true, length = 255)
    private String email;

    @Column(name = "adress", length = 255)
    private String adress;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<OrderEcoJPAEntity> orders = new ArrayList<>();
}
