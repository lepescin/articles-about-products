package ru.lepescin.articlesaboutproducts.util;

import ru.lepescin.articlesaboutproducts.model.Product;
import ru.lepescin.articlesaboutproducts.to.ProductTo;

public class ProductUtil {
    public static Product createNewFromTo(ProductTo productTo){
        return new Product(productTo.getId(),productTo.getName(),productTo.getDescription(),productTo.getPrice());
    }
}
