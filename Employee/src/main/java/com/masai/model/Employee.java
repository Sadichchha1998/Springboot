package com.masai.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer employeeId; // : Integer;
	@NotNull
	private String employeeName; // : String;

//	address: Address; [ HAS-A Relationship]
	@NotNull
	@Embedded
	@Valid
	private Address address;
	@NotNull
	@Min(value = 18, message = "Minimum age Should be 18")
	private Integer age; // : int
	@NotNull
	@Email
	@Column(unique = true)
	private String email; // : String;
	@NotNull
	@Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Mobile Number Should be 10 Digit and Should Start from (6,7,8,9)")
	private String mobile; // : String;
	@NotNull
	@Size(min = 8, max = 15, message = "Password Should be between 8  and 15 characters Only")
	private String password; // : String;

	   public Address getAddress() {
	        return address;
	    }

	    public void setAddress(Address address) {
	        this.address = address;
	    }
}