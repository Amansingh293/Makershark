package com.wep.makersharks.models;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="supplier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplier_id;

    @Column(nullable = false)
    private String company_name;

    @Column(nullable = false)
    private  String website;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NatureOfBusiness natureOfBusiness;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ManufacturingProcesses manufacturingProcesses;

}
