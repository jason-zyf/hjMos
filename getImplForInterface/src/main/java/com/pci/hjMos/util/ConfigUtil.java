package com.pci.hjMos.util;

import com.pci.hjMos.service.Config;
import org.aspectj.util.Reflection;
import org.reflections.Reflections;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ConfigUtil {

    // 初始化方法名称
    private static final String INIT_METHOD_NAME = "initialize";

    //要扫描的包名
    private static final String PACKAGE_NAME = "com.pci.hjMos";

    public static void main(String[] args) {
        // 获取所有实现类的名字集合
        List<String> list = getConfigNameList();
        for (String name : list) {
            System.out.println(name);
            //初始化
            manualInitialize(name);
        }

        // 获取
        /*List<Class> configList = getConfigList();
        System.out.println(configList);
        for (Class clazz: configList) {
            System.out.println(clazz);
            manualInitializeForClazz(clazz);
        }*/

    }

    /**
     * 获取所有s实现类的名字的集合
     * @return
     */
    private static List<String> getConfigNameList() {

        List<String> nameList  = new ArrayList<>();
        Reflections reflections = new Reflections(PACKAGE_NAME);
        Set<Class<? extends Config>> classes = reflections.getSubTypesOf(Config.class);
        if (classes != null) {
            for (Class<? extends Config> config: classes) {
                boolean isAbstract = Modifier.isAbstract(config.getModifiers());
                //过滤抽象类
                if (!isAbstract) {
                    nameList.add(config.getName());
                }
            }
        }
        return nameList ;
    }

    /**
     * 获取所有实现的类
     * @return
     */
    public static List<Class> getConfigList() {
        List<Class> moduleList= new ArrayList<Class>();
        Reflections reflections = new Reflections(PACKAGE_NAME);
        Set<Class<? extends Config>> classes = reflections.getSubTypesOf(Config.class);
        if (classes != null) {
            for (Class<? extends Config> config : classes) {
                boolean isAbstract = Modifier.isAbstract(config.getModifiers());
                if (!isAbstract) {
                    moduleList.add(config);
                }
            }
        }
        return moduleList;
    }

    /**
     * 调用初始化方法
     * @param fullClassName 全限定名
     */
    @SuppressWarnings("unchecked")
    private static void manualInitialize(String fullClassName) {

        try {
            Class clazz = Class.forName(fullClassName);
            Constructor[] constructors = clazz.getDeclaredConstructors();   // 获取构造器
            AccessibleObject.setAccessible(constructors, true);
            for (Constructor con: constructors) {
                if(con.isAccessible()){
                    Object classObject = con.newInstance();
                    Method method = clazz.getMethod(INIT_METHOD_NAME);
                    method.invoke(classObject);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void manualInitializeForClazz(Class clazz) {
        try {
            Constructor[] constructors = clazz.getDeclaredConstructors();
            AccessibleObject.setAccessible(constructors, true);
            for (Constructor con: constructors) {
                if(con.isAccessible()){
                    Object classObject = con.newInstance();
                    Method method = clazz.getMethod(INIT_METHOD_NAME);
                    method.invoke(classObject);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
