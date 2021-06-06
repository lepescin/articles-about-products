package ru.lepescin.articlesaboutproducts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.lepescin.articlesaboutproducts.model.Product;

@Transactional(readOnly = true)
public interface CrudProductRepository extends JpaRepository<Product, Integer> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Product p WHERE p.id=:id")
    int delete(@Param("id") int id);
}
