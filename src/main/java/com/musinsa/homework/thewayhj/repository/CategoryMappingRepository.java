package com.musinsa.homework.thewayhj.repository;

import com.musinsa.homework.thewayhj.entity.CategoryMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CategoryMappingRepository extends JpaRepository<CategoryMapping, Integer> {
    List<CategoryMapping> findByCategoryId(int categoryId);
    CategoryMapping findByCategoryIdAndSubCategoryId(int categoryId, int subCategoryId);
    void deleteCategoryMappingByCategoryId(int categoryId);
}
