package ru.lepescin.articlesaboutproducts.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lepescin.articlesaboutproducts.model.Product;

import java.util.List;

@Repository
public class ProductRepository {
    private final CrudProductRepository crudRepository;

    @Autowired
    public ProductRepository(CrudProductRepository crudRepository){
        this.crudRepository = crudRepository;
    }

    public Product save(Product product) {
        if (! product.isNew() && get( product.getId()) == null) {
            return null;
        }
        return crudRepository.save(product);
    }

    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    public Product get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    public List<Product> getAll() {
        return crudRepository.findAll();
    }
}
