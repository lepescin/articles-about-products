package ru.lepescin.articlesaboutproducts.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.lepescin.articlesaboutproducts.model.Article;
import ru.lepescin.articlesaboutproducts.service.ArticleService;
import ru.lepescin.articlesaboutproducts.to.ArticleTo;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.lepescin.articlesaboutproducts.util.ValidationUtil.assureIdConsistent;
import static ru.lepescin.articlesaboutproducts.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = ArticleController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class ArticleController {
    static final String REST_URL = "/products";

    @Autowired
    private ArticleService articleService;

    @PostMapping(value = "/{productId}/articles", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Article> create(@Valid @RequestBody ArticleTo articleTo, @PathVariable int productId) {
        log.info("create {} for product with id={}", articleTo, productId);
        checkNew(articleTo);
        Article created = articleService.create(articleTo, productId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/" + productId + "/articles" + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{productId}/articles/{articleId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int productId, @PathVariable int articleId) {
        log.info("delete article {} about product with id={}", articleId, productId);
        articleService.delete(articleId, productId);
    }

    @PutMapping(value = "/{productId}/articles/{articleId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody ArticleTo articleTo, @PathVariable int productId, @PathVariable int articleId) {
        assureIdConsistent(articleTo, articleId);
        log.info("update {} with id={} for product with id={}", articleTo, articleId, productId);
        articleService.update(articleTo, productId);
    }

    @GetMapping("/{productId}/articles")
    public List<Article> getAllArticlesForProduct(@PathVariable int productId) {
        log.info("get all articles about product {}", productId);
        return articleService.getAll(productId);
    }

    @GetMapping("/{productId}/articles/{articleId}")
    public Article getArticle(@PathVariable int productId, @PathVariable int articleId) {
        log.info("get article {} about product {}", articleId, productId);
        return articleService.get(articleId, productId);
    }
}
