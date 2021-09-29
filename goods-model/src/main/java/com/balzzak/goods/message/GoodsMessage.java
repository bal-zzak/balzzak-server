package com.balzzak.goods.message;

import com.balzzak.common.message.MessageSerializer;
import com.balzzak.common.utils.JsonUtil;

public class GoodsMessage extends MessageSerializer {

    private static final String ROUTING_KEY = "balzzak.service.goods";

    public GoodsMessage() {
    }

    public GoodsMessage(GoodsMessageName msg, Object body) {
        this.key = ROUTING_KEY;
        this.name = msg.name();
        String json = JsonUtil.toJson(body);
        this.value = json;
    }

    public GoodsMessageName getMessageName() {
        // Base 클래스에서 String으로 가져오면 Enum 리턴이 되지 않는다..
        // Objcet를 String으로 변환해야지만 Enum으로 리턴이 가능해진다
        // 이유 : getName에 대한 메소드위치를 부모에서 호출한 것인지 자식에서 호출한 것인지 모호한 판단을 한다
        //Object name = super.getName();
        return GoodsMessageName.valueOf(this.getName());
    }

}
