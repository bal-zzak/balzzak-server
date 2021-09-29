package com.balzzak.common.message;

import lombok.Getter;

@Getter
public abstract class MessageBase {
    protected String name = "";
    protected String value = "";

    protected String key = "";
    private final String exchangeName = "balzzak.exchange";

    public MessageBase() {}

    // Base에서 override 하지 않고 어노테이션으로 구현한다 @Getter 랑 중복이다
    //protected String getName() {
    //    return this.name;
    //}
}
