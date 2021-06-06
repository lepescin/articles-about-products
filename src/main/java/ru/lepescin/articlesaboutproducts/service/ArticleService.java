package ru.lepescin.articlesaboutproducts.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.lepescin.articlesaboutproducts.model.Article;
import ru.lepescin.articlesaboutproducts.repository.ArticleRepository;
import ru.lepescin.articlesaboutproducts.to.ArticleTo;

import java.util.List;

import static ru.lepescin.articlesaboutproducts.util.ArticleUtil.createNewFromTo;
import static ru.lepescin.articlesaboutproducts.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ArticleService {
    private final ArticleRepository repository;

    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }

    public void delete(int id, int productId) {
        checkNotFoundWithId(repository.delete(id, productId), id);
    }

    public Article get(int id, int productId) {
        return checkNotFoundWithId(repository.get(id, productId), id);
    }

    public List<Article> getAll(int productId) {
        return repository.getAll(productId);
    }

    private Article save(Article article, int productId) {
        Assert.notNull(article, "article must not be null");
        return checkNotFoundWithId(repository.save(article, productId), article.getId());
    }

    public Article create(ArticleTo articleTo, int productId) {
        return create(createNewFromTo(articleTo), productId);
    }

    public Article create(Article article, int productId) {
        return save(article, productId);
    }

    public void update(ArticleTo articleTo, int productId) {
        update(createNewFromTo(articleTo), productId);
    }

    public void update(Article article, int productId) {
        save(article, productId);
    }
}
