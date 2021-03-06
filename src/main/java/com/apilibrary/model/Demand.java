package com.apilibrary.model;


import java.time.LocalDate;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "demand")
public class Demand {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	@NotNull @NotBlank
	private String status;
	@NotNull @NotBlank
	private String payment;
	private double shipping;
	private double total;
	private int accountId;
	private int addressId;
	
}
