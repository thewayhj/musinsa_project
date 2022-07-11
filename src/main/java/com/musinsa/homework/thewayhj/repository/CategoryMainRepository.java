package com.musinsa.homework.thewayhj.repository;

import com.musinsa.homework.thewayhj.entity.CategoryMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CategoryMainRepository extends JpaRepository<CategoryMain, Integer> {
    CategoryMain findByCategoryName(String categoryMain);
}
