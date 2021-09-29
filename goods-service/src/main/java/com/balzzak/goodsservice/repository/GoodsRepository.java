package com.balzzak.goodsservice.repository;

import com.balzzak.goods.model.domain.Goods;
import com.balzzak.goods.model.domain.compositekey.GoodsCompositeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, GoodsCompositeId> {

    Optional<Goods> findByGoodsId(long goodsId);

    List<Goods> findByCategoryId(long categoryId);

    void deleteByGoodsId(long goodsId);

    void deleteByCategoryId(long categoryId);
}
