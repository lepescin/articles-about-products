package ru.lepescin.articlesaboutproducts.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.lepescin.articlesaboutproducts.model.Product;
import ru.lepescin.articlesaboutproducts.service.ProductService;
import ru.lepescin.articlesaboutproducts.to.ProductTo;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.lepescin.articlesaboutproducts.util.ValidationUtil.assureIdConsistent;
import static ru.lepescin.articlesaboutproducts.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = ProductController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class ProductController {
    static final String REST_URL = "/products";

    @Autowired
    private ProductService productService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> create(@Valid @RequestBody ProductTo productTo) {
        log.info("create {}", productTo);
        checkNew(productTo);
        Product created = productService.create(productTo);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete product with id={}", id);
        productService.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody ProductTo productTo,
                       @PathVariable int id) {
        assureIdConsistent(productTo, id);
        log.info("update {} with id={}", productTo, id);
        productService.update(productTo);
    }

    @GetMapping
    public List<Product> getAll() {
        log.info("get all products");
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) {
        log.info("get product {} with articles", id);
        return productService.get(id);
    }
}
