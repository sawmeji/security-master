package com.example.securitymaster.ds;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "FirstName cannot be blank")
    @Pattern(regexp = "[A-Za-z-]*", message = "Firstname cannot contain illegal characters.")
    private String firstName;

    @NotBlank(message = "Lastname cannot be blank")
    @Pattern(regexp = "[A-Za-z-]*", message = "LastName cannot contain illegal characters.")
    private String lastName;

    @NotBlank(message = "PhoneNumber cannot be blank")
    @Pattern(regexp = "[0-9\\-+]*", message = "PhoneNumber cannot contain illegal characters.")
    private String phoneNumber;

    @NotBlank(message = "Address cannot be blank")
    @Pattern(regexp = "[\\w .\\-/,]*", message = "Address cannot contain illegal characters.")
    private String address;

    @NotBlank(message = "CubicleNo cannot be blank")
    @Pattern(regexp = "[A-Za-z0-9\\-]*", message = "CubicleNo cannot contain illegal characters.")
    private String cubicleNo;

    public Employee() {

    }

    public Employee(String firstName, String lastName, String phoneNumber, String address, String cubicleNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.cubicleNo = cubicleNo;
    }
}
