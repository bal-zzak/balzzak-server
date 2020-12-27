package com.balzzak.goods.model.domain.compositekey;


import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class GoodsSetCompositeId implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    private long setGoodsId;

    @EqualsAndHashCode.Include
    @Id
    private long goodsId;
}
