package com.balzzak.goodsservice.repository;

import com.balzzak.goods.model.domain.GoodsVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsVersionRepository extends JpaRepository<GoodsVersion, Long> {
}
