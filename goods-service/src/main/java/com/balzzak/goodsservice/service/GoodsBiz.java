package com.balzzak.goodsservice.service;

import com.balzzak.goods.contract.operation.IGoodsBiz;
import com.balzzak.goods.model.domain.Goods;
import com.balzzak.goods.model.domain.GoodsCategory;
import com.balzzak.goods.model.domain.compositekey.GoodsCompositeId;
import com.balzzak.goods.model.dto.request.GoodsDTO;
import com.balzzak.goods.model.dto.request.GoodsGetRequest;
import com.balzzak.goodsservice.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class GoodsBiz implements IGoodsBiz {

    private final GoodsRepository goodsRepository;
    private final GoodsCategoryRepository goodsCategoryRepository;
    private final GoodsPictureRepository goodsPictureRepository;
    private final GoodsSetRepository goodsSetRepository;
    private final GoodsVersionRepository goodsVersionRepository;


    public GoodsBiz(GoodsRepository goodsRepository, GoodsCategoryRepository goodsCategoryRepository, GoodsPictureRepository goodsPictureRepository, GoodsSetRepository goodsSetRepository, GoodsVersionRepository goodsVersionRepository) {
        this.goodsRepository = goodsRepository;
        this.goodsCategoryRepository = goodsCategoryRepository;
        this.goodsPictureRepository = goodsPictureRepository;
        this.goodsSetRepository = goodsSetRepository;
        this.goodsVersionRepository = goodsVersionRepository;
    }

    // 없는 경우 예외를 보내야 하나 ->  404
    public List<Goods> getGoods(GoodsGetRequest request) {
        List<Goods> goodsList = new ArrayList<>();

        if(request.getGoodsId() == null && request.getGoodsCategoryId() == null) {
            goodsList = this.goodsRepository.findAll();
        } else if(request.getGoodsId() != null) {
            Optional<Goods> goods = this.goodsRepository.findByGoodsId(request.getGoodsId());
            if(goods.isPresent())
                goodsList.add(goods.get());
            else
                throw new RuntimeException("404");
        } else if(request.getGoodsCategoryId() != null) {
            goodsList = this.goodsRepository.findByCategoryId(request.getGoodsCategoryId());
            if(goodsList.size() == 0)
               throw new RuntimeException("404");

        } else if(request.getGoodsId() != null && request.getGoodsCategoryId() != null) {
            Optional<Goods> goods = this.goodsRepository.findById(new GoodsCompositeId(request.getGoodsId(), request.getGoodsCategoryId()));
            if(goods.isPresent())
                goodsList.add(goods.get());
            else
                throw new RuntimeException("404");
        }

        return goodsList;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void createGoods(GoodsDTO dto) {
        List<Goods> goodsList = dto.convertGoods();
        List<GoodsCategory> categoryList = dto.convertGoodsCategory();

        // SQL 조인으로 하는게 젤 낫겠는데...
        // mysql 에서 TVP 가 지원되는지도 확인 필요
        // 관리자 페이지 만들어지면 그때 시작하자

        // goodsRepository.save(dto.getGoods());
        // 카테고리와 아이템도 추가해야 한다면
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateGoods(List<Goods> goodsList) {
        this.goodsRepository.saveAll(goodsList);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updateGoodsCategory(List<GoodsCategory> goodsCategoryList) {
        this.goodsCategoryRepository.saveAll(goodsCategoryList);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteGoods(long goodsId) {
        // 상품 테이블 삭제
        this.goodsRepository.deleteByGoodsId(goodsId);
        // 사진 테이블 삭제

        // 셋트상품 테이블 삭제
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteGoodsCategory(long goodsCategoryId) {
        // 카테고리 테이블 삭제
        this.goodsCategoryRepository.deleteById(goodsCategoryId);
        // 상품 테이블 모두 삭제
        this.goodsRepository.deleteByCategoryId(goodsCategoryId);
    }
}
