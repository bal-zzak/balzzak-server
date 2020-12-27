package com.balzzak.data.goods.models.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "GoodsSet")
@IdClass(GoodsSetId.class)
public class GoodsSet {

    @Id
    private long setGoodsId;

    @Id
    private long goodsId;

    @Column(nullable = false)
    private LocalDateTime createDate;

    @Column(nullable = false)
    private LocalDateTime updateDate;
}
