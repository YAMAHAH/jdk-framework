package com.example.demo.core;

import com.example.demo.utils.SpringUtils;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.io.FileReader;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("unchecked")
@Component
public class CoreService {

    public Map<String, ApiControllerBase> getApiControllers() {
        Map<String, ApiControllerBase> map = SpringUtils.getBeansOfType(ApiControllerBase.class);
        for (ApiControllerBase apiControllerBase : map.values()) {
            Method[] methods = ReflectionUtils.getAllDeclaredMethods(apiControllerBase.getClass());
            for (Method method : methods) {

            }
        }
        return map;
    }

    public void getCustomAnnotations(){

    }
    public static Map<Integer, String> map = new ConcurrentHashMap<>();

    static  {
        //反射工具包，指明扫描路径
        Reflections reflections = new Reflections("com.example.demo.controller");
        //获取带Handler注解的类
        Set<Class<?>> classList = reflections.getTypesAnnotatedWith(CustomAnnotation.class);
        for (Class classes : classList) {
            CustomAnnotation t = (CustomAnnotation) classes.getAnnotation(CustomAnnotation.class);
            String[] valueList = t.value();
            //获取 foo 这个代理实例所持有的 InvocationHandler
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(t);


            // 获取 memberValues
            Map memberValues = null;
            try {
                // 获取 AnnotationInvocationHandler 的 memberValues 字段
                Field declaredField = invocationHandler.getClass().getDeclaredField("memberValues");
                // 因为这个字段事 private final 修饰，所以要打开权限
                declaredField.setAccessible(true);
                memberValues = (Map) declaredField.get(invocationHandler);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e){
                e.printStackTrace();
            }
            // 修改 value 属性值
            memberValues.put("remark", "test.annotation.new.value");
            // 获取 foo 的 value 属性值
            String newValue = t.remark();
            System.out.println("修改之后的注解值：" + newValue);
            //获取注解的值并循环
            for (String value : valueList) {
                //注解值为key，类名为value
                map.put(Integer.valueOf(value), classes.getSimpleName()); //CommonUtils.normalizeFirstWord(
            }
        }
    }

    //通过eventTypeId，也就是注解的值获取相应处理Handler的类名
    public static String getBeanNameByEventType(Integer eventTypeId) {
        return map.get(eventTypeId);
    }
    public  void aaa() {
        //反射工具包，指明扫描路径
        Reflections reflections = new Reflections("com.example.demo.controller");
        //获取带Handler注解的类
        Set<Class<?>> classList = reflections.getTypesAnnotatedWith(CustomAnnotation.class);
        for (Class classes : classList) {
            CustomAnnotation t = (CustomAnnotation) classes.getAnnotation(CustomAnnotation.class);
            String[] valueList = t.value();
            //获取 foo 这个代理实例所持有的 InvocationHandler
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(t);


            // 获取 memberValues
            Map memberValues = null;
            try {
                // 获取 AnnotationInvocationHandler 的 memberValues 字段
                Field declaredField = invocationHandler.getClass().getDeclaredField("memberValues");
                // 因为这个字段事 private final 修饰，所以要打开权限
                declaredField.setAccessible(true);
                memberValues = (Map) declaredField.get(invocationHandler);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e){
                e.printStackTrace();
            }
            // 修改 value 属性值
            memberValues.put("remark", "test.annotation.new.value");
            // 获取 foo 的 value 属性值
            String newValue = t.remark();
            System.out.println("修改之后的注解值：" + newValue);
            //获取注解的值并循环
            for (String value : valueList) {
                //注解值为key，类名为value
                map.put(Integer.valueOf(value), classes.getSimpleName()); //CommonUtils.normalizeFirstWord(
            }
        }
//        try {
//            for (Method method : CoreService.class
//                    .getClassLoader()
//                    .loadClass(("com.journaldev.annotations.AnnotationExample"))
//                    .getMethods()) {
//                // checks if MethodInfo annotation is present for the method
//                if (method.isAnnotationPresent(CustomAnnotation.class)) {
//                    try {
//                        // iterates all the annotations available in the method
//                        for (Annotation anno : method.getDeclaredAnnotations()) {
//                            System.out.println("Annotation in Method '"
//                                    + method + "' : " + anno);
//                        }
//                        CustomAnnotation methodAnno = method.getAnnotation(CustomAnnotation.class);
//                        if (methodAnno.value().length > 0) {
//                            System.out.println("Method with revision no 1 = "
//                                    + method);
//                        }
//
//                    } catch (Throwable ex) {
//                        ex.printStackTrace();
//                    }
//                }
//            }
//        } catch (SecurityException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }
    public static String getValue(String key) throws IOException {
        Properties pro = new Properties();//获取配置文件的对象
        FileReader in = new FileReader("pro.txt");//获取输入流
        pro.load(in);//将流加载到配置文件对象中
        in.close();
        return pro.getProperty(key);//返回根据key获取的value值
    }
}
