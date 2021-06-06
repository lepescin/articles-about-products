package ru.lepescin.articlesaboutproducts.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "products")
public class Product extends AbstractBaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private int price;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private List<Article> articles;

    public Product(Integer id, String name, String description, int price) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
