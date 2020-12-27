package com.balzzak.goods.model.domain;

import com.balzzak.common.utils.DatetimeHelper;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "GoodsCategory")
public class GoodsCategory {

    public void setCurrentDatetime() {
        //this.createDate = DatetimeHelper.timestampNow();
        //this.updateDate = DatetimeHelper.timestampNow();
    }

    public void update(GoodsCategory goodsCategory) {
        this.sortOrder = goodsCategory.getSortOrder();
        this.categoryName = goodsCategory.getCategoryName();
        //this.updateDate = DatetimeHelper.timestampNow();
    }

    @Builder
    public GoodsCategory(long categoryId, String categoryName, Timestamp createDate, Timestamp updateDate) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        //this.createDate = createDate;
        //this.updateDate = updateDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;

    @Column(nullable = false)
    private int sortOrder;

    @Column(nullable = false)
    private String categoryName;

    @Column(nullable = false)
    private Timestamp createDate;

    @Column(nullable = false)
    private Timestamp updateDate;

    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    @Transient
    private List<Goods> goods = new ArrayList<>();
}
