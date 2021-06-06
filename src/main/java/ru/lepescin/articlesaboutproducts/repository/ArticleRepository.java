package ru.lepescin.articlesaboutproducts.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.lepescin.articlesaboutproducts.model.Article;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ArticleRepository {
    private final CrudArticleRepository articleRepository;
    private final CrudProductRepository productRepository;

    @Autowired
    public ArticleRepository(CrudArticleRepository articleRepository, CrudProductRepository productRepository){
        this.articleRepository = articleRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Article save(Article article, int productId) {
        if (!article.isNew() && get(article.getId(), productId) == null) {
            return null;
        }
        article.setCreated(LocalDateTime.now());
        article.setProduct(productRepository.findById(productId).orElse(null));
        return articleRepository.save(article);
    }

    public boolean delete(int id, int productId) {
        return articleRepository.delete(id, productId) != 0;
    }

    public Article get(int id, int productId) {
        return articleRepository
                .findById(id)
                .filter(article -> article.getProduct().getId() == productId)
                .orElse(null);
    }

    public List<Article> getAll(int productId) {
        return articleRepository.getAll(productId);
    }
}
