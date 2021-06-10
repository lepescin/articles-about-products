package ru.lepescin.articlesaboutproducts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.lepescin.articlesaboutproducts.model.Article;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudArticleRepository extends JpaRepository<Article, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Article a WHERE a.id=:id AND a.product.id=:productId")
    int delete(@Param("id") int id, @Param("productId") int productId);

    @Query("SELECT a FROM Article a WHERE a.product.id=:productId")
    List<Article> getAll(@Param("productId") int productId);

    @Query("SELECT a FROM Article a ORDER BY a.title")
    List<Article> getAllSortedByArticleTitleAsc();

    @Query("SELECT a FROM Article a ORDER BY a.created")
    List<Article> getAllSortedByDateTimeAsc();

    @Query("SELECT a FROM Article a WHERE a.created >= :fromDatetime AND a.created <= :toDateTime ORDER BY a.created")
    List<Article> getAllFilteredByDateTime(@Param("fromDatetime") LocalDateTime fromDatetime, @Param("toDateTime") LocalDateTime toDateTime);
}
