package com.balzzak.goods.model.dto.request;

import com.balzzak.goods.model.enums.SaleCode;
import com.balzzak.goods.model.enums.SaleState;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class GoodsSetRequest {

    @NotNull
    private Long goodsCategoryId;

    @NotNull
    private BigDecimal regularPrice;

    @NotNull
    private BigDecimal discountPrice;

    @NotEmpty
    private String goodsName;

    @NotEmpty
    private String description;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private SaleCode saleCode;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private SaleState saleState;

    private String imagePath;

    private String thumbnailImagePath;

    @NotEmpty
    private String countryOfOrigin;

    @NotNull
    private Long versionId;
}
