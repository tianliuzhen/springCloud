package com.aaa.security_oauth2.config.config_enum.handlerJson;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/8/22
 */
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnumDeserializerByCode extends JsonDeserializer<Object> {
    private static final Logger log = LoggerFactory.getLogger(EnumDeserializerByCode.class);

    public EnumDeserializerByCode() {
    }

    @Override
    public Object deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        TreeNode node = parser.readValueAsTree();
        Object object = parser.getCurrentValue();
        String attrName = parser.getCurrentName();
        Object attrValue;
        if (node.isValueNode()) {
            attrValue = node;
        } else {
            if (!node.isObject() || !node.toString().startsWith("{") || !node.toString().endsWith("}")) {
                throw new IllegalArgumentException("deserializer enum error: node value [" + node + "] can't deserializer to " + object.getClass().getName());
            }

            attrValue = node.get("code");
        }

        Class clazz = null;

        try {
            clazz = object.getClass().getDeclaredField(attrName).getType();
        } catch (NoSuchFieldException var9) {
        }

        if (clazz == null) {
            return null;
        } else if (!clazz.isEnum()) {
            throw new IllegalArgumentException("deserializer enum error: " + clazz.getName() + " is not enum");
        } else {
            if (attrValue.toString().contains("\"")) {
                attrValue = attrValue.toString().replace("\"", "");
            }

            return EnumCacheUtils.get(clazz, attrValue);
        }
    }
}
