package com.balzzak.goods.model.domain.compositekey;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;



@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class GoodsCompositeId implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long goodsId;

    @EqualsAndHashCode.Include
    @Id
    private long categoryId;

    public GoodsCompositeId(long goodsId, long categoryId) {
        this.goodsId = goodsId;
        this.categoryId = categoryId;
    }

}
