package com.musinsa.homework.thewayhj.repository;

import com.musinsa.homework.thewayhj.entity.CategorySub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CategorySubRepository extends JpaRepository<CategorySub, Integer> {
    List<CategorySub> findByCategoryIdIn(List<Integer> categoryIds);
}
