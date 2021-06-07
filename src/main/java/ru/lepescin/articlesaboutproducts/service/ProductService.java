package ru.lepescin.articlesaboutproducts.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.lepescin.articlesaboutproducts.model.Product;
import ru.lepescin.articlesaboutproducts.repository.CrudProductRepository;
import ru.lepescin.articlesaboutproducts.to.ProductTo;

import java.util.List;

import static ru.lepescin.articlesaboutproducts.util.ProductUtil.createNewFromTo;
import static ru.lepescin.articlesaboutproducts.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ProductService {
    private final CrudProductRepository repository;

    public ProductService(CrudProductRepository repository) {
        this.repository = repository;
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Product get(int id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    @Transactional
    protected Product save(Product product) {
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

    public List<Product> getAllSortedByNameAsc() {
        return repository.getAllSortedByNameAsc();
    }

    public List<Product> getAllSortedByPriceAsc() {
        return repository.getAllSortedByPriceAsc();
    }

    public List<Product> getAllSortedByQuantityOfArticlesAsc() {
        return repository.getAllSortedByQuantityOfArticlesAsc();
    }

    public List<Product> getAllFilteredByPrice(Integer fromPrice, Integer toPrice) {
        return repository.getAllFilteredByPrice(fromPrice, toPrice);
    }

    public List<Product> getAllFilteredByQuantityOfArticles(Integer fromQty, Integer toQty) {
        return repository.getAllFilteredByQuantityOfArticles(fromQty, toQty);
    }
}
