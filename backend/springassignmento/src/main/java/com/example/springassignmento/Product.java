package com.example.springassignmento;
import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product
{
    @Id
    @Column(name = "productId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @Column(name = "productName")
    private String productName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "mrpperunit")
    private Double mrpperUnit;

    @Column(name = "salesmanId")
    private Long salesmanId;

}





