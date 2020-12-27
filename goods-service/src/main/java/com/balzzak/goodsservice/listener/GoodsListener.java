package com.balzzak.goodsservice.listener;

import com.balzzak.goods.message.GoodsMessage;
import com.balzzak.goods.message.GoodsMessageName;
import com.balzzak.goods.model.domain.Goods;
import com.balzzak.goods.model.domain.GoodsCategory;
import com.balzzak.goods.model.dto.request.GoodsDTO;
import com.balzzak.goods.model.dto.request.GoodsGetRequest;
import com.balzzak.goodsservice.repository.*;
import com.balzzak.goodsservice.service.GoodsBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class GoodsListener {

    private final GoodsBiz goodsBiz;
    private final GoodsRepository goodsRepository;
    private final GoodsCategoryRepository goodsCategoryRepository;
    private final GoodsSetRepository goodsSetRepository;
    private final GoodsPictureRepository goodsPictureRepository;
    private final GoodsVersionRepository goodsVersionRepository;

    public GoodsListener(GoodsBiz goodsBiz, GoodsRepository goodsRepository, GoodsCategoryRepository goodsCategoryRepository, GoodsSetRepository goodsSetRepository, GoodsPictureRepository goodsPictureRepository, GoodsVersionRepository goodsVersionRepository) {
        this.goodsBiz = goodsBiz;
        this.goodsRepository = goodsRepository;
        this.goodsCategoryRepository = goodsCategoryRepository;
        this.goodsSetRepository = goodsSetRepository;
        this.goodsPictureRepository = goodsPictureRepository;
        this.goodsVersionRepository = goodsVersionRepository;
    }

    @RabbitListener(queues = "goods.request", containerFactory = "rabbitListenerContainerFactory")
    public Object handleSignUpEvent(final GoodsMessage message) throws Exception {

        try {
            GoodsMessageName name = message.getMessageName();
            log.warn(name.name());
            switch(name) {
                case GOODS_GET: {
                    GoodsGetRequest request = message.deserializeValue(GoodsGetRequest.class);
                    List<Goods> list = goodsBiz.getGoods(request);
                    return message.serializeValue(list);
                }
                case GOODS_CATEGORY_GET: {
                    Long categoryId = message.deserializeValue(Long.class);
                    if(categoryId == null) {
                        List<GoodsCategory> categories = goodsCategoryRepository.findAll();
                        return message.serializeValue(categories);
                    } else {
                        Optional<GoodsCategory> category = goodsCategoryRepository.findById(categoryId);
                        List<GoodsCategory> categories = category.stream().collect(Collectors.toList());
                        return message.serializeValue(categories);
                    }
                }
                case GOODS_SET:{
                    GoodsDTO goodsDTO = message.deserializeValue();
                    goodsBiz.createGoods(goodsDTO);
                    break;
                }
                case GOODS_DEL:{
                    long goodsId = message.deserializeValue(Long.class);
                    goodsBiz.deleteGoods(goodsId);
                    break;
                }
                case CATEGORY_DEL:{
                    long goodsCategoryId = message.deserializeValue(Long.class);
                    goodsBiz.deleteGoodsCategory(goodsCategoryId);
                    break;
                }
                default: {
                    log.error("error");
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new RuntimeException("runtime");
    }
}
