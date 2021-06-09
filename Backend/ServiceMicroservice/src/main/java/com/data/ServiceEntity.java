package com.data;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "services")
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private Integer employeeId;

    @Temporal(TemporalType.DATE)
    private Date date;

    private Double longitude;

    private Double latitude;

    private String address;
}