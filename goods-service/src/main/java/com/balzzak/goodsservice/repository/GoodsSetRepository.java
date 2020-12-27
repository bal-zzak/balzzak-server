package com.balzzak.goodsservice.repository;

import com.balzzak.goods.model.domain.GoodsSet;
import com.balzzak.goods.model.domain.compositekey.GoodsSetCompositeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsSetRepository extends JpaRepository<GoodsSet, GoodsSetCompositeId> {
}
