package ru.lepescin.articlesaboutproducts.to;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ProductTo extends BaseTo {
    private String name;

    private String description;

    private int price;
}
