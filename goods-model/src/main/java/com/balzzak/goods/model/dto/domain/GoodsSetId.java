package com.balzzak.data.goods.models.domain;


import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class GoodsSetId  implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    private long setGoodsId;

    @EqualsAndHashCode.Include
    @Id
    private long goodsId;
}
