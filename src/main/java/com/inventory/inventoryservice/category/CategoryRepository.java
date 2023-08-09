package com.inventory.inventoryservice.category;
import com.inventory.inventoryservice.category.model.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long>,
                                                QuerydslPredicateExecutor<CategoryEntity> {
}
