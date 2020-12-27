package com.balzzak.data.goods.models.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class GoodsId  implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long goodsId;

    @EqualsAndHashCode.Include
    @Id
    private long categoryId;

    public GoodsId(long goodsId, long categoryId) {
        this.goodsId = goodsId;
        this.categoryId = categoryId;
    }

}
