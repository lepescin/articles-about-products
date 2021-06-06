package ru.lepescin.articlesaboutproducts.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.lepescin.articlesaboutproducts.model.Product;
import ru.lepescin.articlesaboutproducts.repository.ProductRepository;
import ru.lepescin.articlesaboutproducts.to.ProductTo;

import java.util.List;

import static ru.lepescin.articlesaboutproducts.util.ProductUtil.createNewFromTo;
import static ru.lepescin.articlesaboutproducts.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Product get(int id) {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public List<Product> getAll() {
        return repository.getAll();
    }

    private Product save(Product product) {
        Assert.notNull(product, "article must not be null");
        return checkNotFoundWithId(repository.save(product), product.getId());
    }

    public Product create(ProductTo productTo) {
        return create(createNewFromTo(productTo));
    }

    public Product create(Product product) {
        return save(product);
    }

    public void update(ProductTo productTo) {
        update(createNewFromTo(productTo));
    }

    public void update(Product product) {
        save(product);
    }
}
