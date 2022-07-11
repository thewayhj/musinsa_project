package com.musinsa.homework.thewayhj.repository;

import com.musinsa.homework.thewayhj.entity.CategorySub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CategorySubRepository extends JpaRepository<CategorySub, Integer> {
    CategorySub findByCategoryId (int categoryId);
}
