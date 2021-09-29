package com.balzzak.common.message;

import com.balzzak.common.utils.JsonUtil;

public class MessageSerializer extends MessageBase {

    public <T> T deserializeValue() throws Exception {
        return JsonUtil.fromJson(this.value);
    }

    public <T> T deserializeValue(Class<T> typeOfT) throws Exception {
        return JsonUtil.fromJson(this.value, typeOfT);
    }

    public String serializeValue(Object obj) throws Exception {
        return JsonUtil.toJson(obj);
    }
}
