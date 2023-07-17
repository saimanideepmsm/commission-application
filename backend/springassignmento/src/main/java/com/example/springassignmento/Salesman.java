package com.example.springassignmento;
import javax.persistence.*;
import lombok.*;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "salesman")
public class Salesman {
    @Id
    @Column(name = "salesmanId")
    private Long salesmanId;

    @Column(name = "salesmanName")
    private String salesmanName;

    @Column(name = "salesmanArea")
    private String salesmanArea;

    @Column(name = "commissionRate")
    private Double commissionRate;
}

