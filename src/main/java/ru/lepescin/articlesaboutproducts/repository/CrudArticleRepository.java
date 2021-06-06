package ru.lepescin.articlesaboutproducts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.lepescin.articlesaboutproducts.model.Article;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudArticleRepository extends JpaRepository<Article, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Article a WHERE a.id=:id AND a.product.id=:productId")
    int delete(@Param("id") int id, @Param("productId") int productId);

    @Query("SELECT a FROM Article a WHERE a.product.id=:productId " +
            "ORDER BY a.created DESC")
    List<Article> getAll(@Param("productId") int productId);
}
