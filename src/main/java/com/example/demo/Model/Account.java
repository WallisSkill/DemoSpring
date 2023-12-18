package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Account")
@JsonPropertyOrder({"accountID", "mail", "password", "role", "IsActive"})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    private int AccountID;
    private String Mail;
    private String Password;
    private int Role;
    @Column(name = "IsActive")
    private boolean IsActive;


}
