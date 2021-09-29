package com.balzzak.goods.model.domain;

import com.balzzak.goods.model.domain.compositekey.GoodsPictureCompositeId;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "GoodsPicture")
@IdClass(GoodsPictureCompositeId.class)
public class GoodsPicture {

    @Id
    private long pictureId;

    @Id
    private long goodsId;

    @Column(nullable = false)
    private String picturePath;

    @Column(nullable = false)
    private Timestamp createDate;

    @Column(nullable = false)
    private Timestamp updateDate;

//    @MapsId("goodsId")
//    @ManyToOne
//    @JoinColumns({
//            @JoinColumn(name = "goodsId"),
//            @JoinColumn(name = "categoryId"),
//    })
    @Transient
    private Goods goods;
}
