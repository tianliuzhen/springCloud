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


import cn.hutool.core.text.StrFormatter;
import com.google.common.collect.Maps;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnumCacheUtils {
    private static final Logger log = LoggerFactory.getLogger(EnumCacheUtils.class);
    public static final String ERROR_MSG = "无法匹配枚举【{}】的值【{}】";
    public static final String ERROR_FULL_MSG = "无法匹配枚举【{}】的值【{}】，值只能为【{}】";
    public static Map<Object, Enum> CACHE = Maps.newConcurrentMap();

    public EnumCacheUtils() {
    }

    public static <E extends Enum> void build(Object[] os) {
        Arrays.stream(os).forEach((o) -> {
            build((Enum)o);
        });
    }

    public static <E extends Enum> void build(E... es) {
        Enum[] var1 = es;
        int var2 = es.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            E e = (E) var1[var3];
            CACHE.put(e.getClass().getName() + ":" + getCodeOrName(e).toString(), e);
        }

    }

    private static <E> Object getCodeOrName(E e) {
        Object code = null;

        try {
            Method method = e.getClass().getMethod("getCode");
            code = method.invoke(e);
        } catch (IllegalAccessException var4) {
        } catch (NoSuchMethodException var5) {
        } catch (InvocationTargetException var6) {
        }

        if (code == null) {
            code = ((Enum)e).name();
        }

        return code;
    }

    public static <E extends Enum> E get(Class<?> enumClass, Object code) {
        if (code == null) {
            return null;
        } else {
            String key = enumClass.getName() + ":" + code;
            if (CACHE.containsKey(key)) {
                return (E) CACHE.get(key);
            } else {
                log.error(StrFormatter.format("无法匹配枚举【{}】的值【{}】", new Object[]{enumClass.getName(), code}));
                String errorMsg = StrFormatter.format("无法匹配枚举【{}】的值【{}】，值只能为【{}】", new Object[]{enumClass.getSimpleName(), code, Arrays.stream(enumClass.getEnumConstants()).map((e) -> {
                    return getCodeOrName(e).toString();
                }).collect(Collectors.joining(","))});
                throw new RuntimeException(errorMsg);
            }
        }
    }
}
