package com.musinsa.homework.thewayhj.repository;

import com.musinsa.homework.thewayhj.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ItemRepository extends JpaRepository<Item, Integer> {
    List<Item> findByCategoryIdIn(List<Integer> categoryIds);
    List<Item> findByCategoryId(int categoryId);
}
