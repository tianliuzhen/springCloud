package com.aaa.security_oauth2.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import sun.net.www.protocol.file.FileURLConnection;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

/**
 * 类工具
 */
@Slf4j
public class EnumUtils {

    public static final String DEFAULT_ATTR_NAME = "code";//枚举有的属性名

    public static Map<String,Object[]> enumMap = Maps.newHashMap();

    public static Map<String,Object[]> getEnumPackageEnums(Class<?> enumClazz){

        if(!enumMap.isEmpty()){ return enumMap;}

        List<Class<?>> enumList = Lists.newLinkedList();
        findLocalEnums(enumClazz.getPackage().getName(),enumClazz.getClassLoader(), enumList);
        findJarEnums(enumClazz.getPackage().getName(),enumClazz.getClassLoader(), enumList);

        enumList.forEach(e->{
            try {
                Object[] objects = (Object[]) e.getDeclaredMethod("values").invoke(new Object(), new Object[]{});
                enumMap.put(StrUtils.firstToLowerCase(e.getSimpleName()) , objects);
            } catch (NoSuchMethodException e1) {
                log.info(e1.getMessage());
            } catch (IllegalAccessException e1) {
                log.info(e1.getMessage());
            } catch (InvocationTargetException e1) {
                log.info(e1.getMessage());
            }
        });
        return enumMap;
    }

    /**
     * 根据包名查找类及其子类
     * @param packName 包名
     * @param classLoader 类加载器
     * @param clazzList
     */
    public static void findLocalEnums(final String packName, ClassLoader classLoader, List<Class<?>> clazzList){

        URI url;
        File file;
        try {
            url = classLoader.getResource(packName.replace(".", File.separator)).toURI();
            file = new File(url);
        } catch (Exception e1) {
            log.info(e1.getMessage());
            return;
        }
        file.listFiles(chiFile -> {
            if(chiFile.isDirectory()){
                findLocalEnums(packName+"."+chiFile.getName(),classLoader, clazzList);
            }
            if(chiFile.getName().endsWith(".class")){
                Class<?> clazz = null;
                try {
                    clazz = classLoader.loadClass(packName + "." + chiFile.getName().replace(".class", ""));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if(!clazz.isEnum()){
                    return false;
                }
                if(!Arrays.stream(clazz.getDeclaredFields()).map(Field::getName).collect(Collectors.toList()).contains(DEFAULT_ATTR_NAME)){
                    return false;
                }
                clazzList.add(clazz);
                return true;
            }
            return false;
        });

    }


    /**
     * jar包查找
     * @param packName
     */
    public static void findJarEnums(final String packName, ClassLoader classLoader, List<Class<?>> clazzList){
        String pathName = packName.replace(".", "/");
        JarFile jarFile;
        try {
            URL url = classLoader.getResource(pathName);
            URLConnection urlConnection = url.openConnection();
            //读取的是本地的
            if(urlConnection instanceof FileURLConnection) {
                return;
            }
            JarURLConnection jarURLConnection  = (JarURLConnection)urlConnection;
            jarFile = jarURLConnection.getJarFile();
        } catch (Exception e) {
            log.error(e.getMessage());
            return;
        }

        Enumeration<JarEntry> jarEntries = jarFile.entries();
        while (jarEntries.hasMoreElements()) {
            JarEntry jarEntry = jarEntries.nextElement();
            String jarEntryName = jarEntry.getName();

            if(jarEntryName.contains(pathName) && !jarEntryName.equals(pathName+"/")){
                //递归遍历子目录
                if(jarEntry.isDirectory()){
                    String clazzName = jarEntry.getName().replace("/", ".");
                    int endIndex = clazzName.lastIndexOf(".");
                    String prefix = null;
                    if (endIndex > 0) {
                        prefix = clazzName.substring(0, endIndex);
                    }
                    findJarEnums(prefix , classLoader, clazzList);
                }
                if(jarEntry.getName().endsWith(".class")){
                    Class<?> clazz = null;
                    try {
                        clazz = classLoader.loadClass(jarEntry.getName().replace("/", ".").replace(".class", ""));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                    if(clazz == null || !clazz.isEnum()){
                        continue;
                    }
                    if(!Arrays.stream(clazz.getDeclaredFields()).map(Field::getName).collect(Collectors.toList()).contains(DEFAULT_ATTR_NAME)){
                        continue;
                    }
                    clazzList.add(clazz);
                }
            }

        }

    }
}
