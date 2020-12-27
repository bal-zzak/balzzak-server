package com.balzzak.data.goods.models.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class GoodsPictureId  implements Serializable  {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pictureId;

    @EqualsAndHashCode.Include
    @Id
    private long goodsId;
}
