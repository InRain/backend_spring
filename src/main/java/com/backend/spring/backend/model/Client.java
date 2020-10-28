package com.backend.spring.backend.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "clients")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "individual_tax_number")
    private String individualTaxNumber;

    @Column(name = "organizational_legal_form")
    private String organizationalLegalForm;

    //TODO replace with Address class
    @Column(name = "address")
    private String address;

    @Column(name = "debt_amount")
    private String debtAmount;

    //TODO replace with CommunicationType class
    @Column(name = "communication_type")
    private String communicationType;

}
