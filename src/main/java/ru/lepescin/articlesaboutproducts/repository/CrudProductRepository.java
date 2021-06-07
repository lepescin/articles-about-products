package ru.lepescin.articlesaboutproducts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.lepescin.articlesaboutproducts.model.Product;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudProductRepository extends JpaRepository<Product, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Product p WHERE p.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT p FROM Product p ORDER BY p.name")
    List<Product> getAllSortedByNameAsc();

    @Query("SELECT p FROM Product p ORDER BY p.price")
    List<Product> getAllSortedByPriceAsc();

    @Query("SELECT p FROM Product p ORDER BY p.articles.size")
    List<Product> getAllSortedByQuantityOfArticlesAsc();

    @Query("SELECT p FROM Product p WHERE p.price >= :fromPrice AND p.price <= :toPrice ORDER BY p.price")
    List<Product> getAllFilteredByPrice(@Param("fromPrice") Integer fromPrice, @Param("toPrice") Integer toPrice);

    @Query("SELECT p FROM Product p WHERE p.articles.size >= :fromQty AND p.articles.size <= :toQty ORDER BY p.articles.size")
    List<Product> getAllFilteredByQuantityOfArticles(@Param("fromQty") Integer fromQty, @Param("toQty") Integer toQty);
}
