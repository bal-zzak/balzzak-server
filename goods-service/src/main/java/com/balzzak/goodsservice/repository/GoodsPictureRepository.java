package com.balzzak.goodsservice.repository;

import com.balzzak.goods.model.domain.GoodsPicture;
import com.balzzak.goods.model.domain.compositekey.GoodsPictureCompositeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsPictureRepository extends JpaRepository<GoodsPicture, GoodsPictureCompositeId> {
}
