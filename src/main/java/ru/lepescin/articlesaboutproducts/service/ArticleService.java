package ru.lepescin.articlesaboutproducts.service;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.lepescin.articlesaboutproducts.model.Article;
import ru.lepescin.articlesaboutproducts.repository.CrudArticleRepository;
import ru.lepescin.articlesaboutproducts.repository.CrudProductRepository;
import ru.lepescin.articlesaboutproducts.to.ArticleTo;

import java.time.LocalDateTime;
import java.util.List;

import static ru.lepescin.articlesaboutproducts.util.ArticleUtil.createNewFromTo;
import static ru.lepescin.articlesaboutproducts.util.ValidationUtil.checkNotFoundWithId;

@Service
public class ArticleService {
    private final CrudArticleRepository articleRepository;
    private final CrudProductRepository productRepository;


    public ArticleService(CrudArticleRepository articleRepository, CrudProductRepository productRepository) {
        this.articleRepository = articleRepository;
        this.productRepository = productRepository;
    }

    public void delete(int id, int productId) {
        checkNotFoundWithId(articleRepository.delete(id, productId) != 0, id);
    }

    public Article get(int id, int productId) {
        return checkNotFoundWithId(articleRepository.findById(id)
                .filter(article -> article.getProduct().getId() == productId)
                .orElse(null), id);
    }

    public List<Article> getAll(int productId) {
        return articleRepository.getAll(productId);
    }

    @Transactional
    protected Article save(Article article, int productId) {
        Assert.notNull(article, "article must not be null");
        if (!article.isNew() && get(article.getId(), productId) == null) {
            return null;
        }
        article.setCreated(LocalDateTime.now());
        article.setProduct(checkNotFoundWithId(productRepository.findById(productId).orElse(null), productId));
        return articleRepository.save(article);
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

    public List<Article> getAllSortedByArticleTitleAsc() {
        return articleRepository.getAllSortedByArticleTitleAsc();
    }

    public List<Article> getAllSortedByDateTimeAsc() {
        return articleRepository.getAllSortedByDateTimeAsc();
    }

    public List<Article> getAllFilteredByDateTime(@Param("fromDatetime") LocalDateTime fromDatetime,
                                                  @Param("toDateTime") LocalDateTime toDateTime) {
        return articleRepository.getAllFilteredByDateTime(fromDatetime, toDateTime);
    }
}
