package com.balzzak.goods.contract.operation;

import com.balzzak.goods.model.domain.Goods;
import com.balzzak.goods.model.dto.request.GoodsGetRequest;

import java.util.List;

public interface IGoodsBiz {
    List<Goods> getGoods(GoodsGetRequest request);
}
