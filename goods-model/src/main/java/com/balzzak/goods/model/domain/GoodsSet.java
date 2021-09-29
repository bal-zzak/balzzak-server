package com.balzzak.goods.model.domain;

import com.balzzak.goods.model.domain.compositekey.GoodsSetCompositeId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "GoodsSet")
@IdClass(GoodsSetCompositeId.class)
public class GoodsSet {

    @Id
    private long setGoodsId;

    @Id
    private long goodsId;

    @Column(nullable = false)
    private Timestamp createDate;

    @Column(nullable = false)
    private Timestamp updateDate;
}
