package com.balzzak.goods.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class GoodsCategorySetRequest {

    @NotNull
    private Integer sortOrder;

    @NotEmpty
    private String categoryName;
}
