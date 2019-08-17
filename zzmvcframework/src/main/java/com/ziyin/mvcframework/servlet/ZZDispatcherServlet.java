package com.ziyin.mvcframework.servlet;

import com.ziyin.mvcframework.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author ziyin
 @create 2019-06-2019/6/19-11:13
 */
public class ZZDispatcherServlet extends HttpServlet {
    private Properties properties = new Properties();
    private List<String> classNames = new ArrayList<>();
    private Map<String, Object> ioc = new HashMap<>();
    private List<Handler> handlerMapping = new ArrayList<>();


    @Override
    public void init(ServletConfig config) throws ServletException {
        //加载配置文件
        doLoadConfig(config.getInitParameter("scanPackage"));
        //扫描所有相关类
        doScanner(properties.getProperty("basePackage"));
        //初始化所有相关Class的实例,并且保存到IOC容器中
        doInstance();
        //自动化依赖注入
        doAutoWired();
        //初始化handlermapping
        initHandlerMapping();
        System.out.println("init finish");
    }

    private void initHandlerMapping() {
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(ZZController.class)){
                continue;
            }
            String url = "";
            if (clazz.isAnnotationPresent(ZZRequestMapping.class)){
                ZZRequestMapping requestMapping = clazz.getAnnotation(ZZRequestMapping.class);
                url = requestMapping.value();
            }
            Method[] methods = clazz.getMethods();

//            for (Method method : methods) {
//                if (!method.isAnnotationPresent(ZZRequestMapping.class)){
//                    continue;
//                }
//                ZZRequestMapping requestMapping = method.getAnnotation(ZZRequestMapping.class);
//                String  murl = url + requestMapping.value();
//                handlerMapping.put(murl,method);
//
//                System.out.println("mapping: " + murl + "----" + method);
//            }

            for (Method method : methods) {
                if (!method.isAnnotationPresent(ZZRequestMapping.class)) {
                    continue;
                }
                ZZRequestMapping requestMapping = method.getAnnotation(ZZRequestMapping.class);
                String regex = ("/" + url + requestMapping.value()).replaceAll("/+","/");
                Pattern pattern = Pattern.compile(regex);
                handlerMapping.add(new Handler(pattern,entry.getValue(),method));
            }
        }
    }

    private void doAutoWired() {
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Field[]  fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(ZZAutowired.class)) {
                    continue;
                }
                ZZAutowired autowired = field.getAnnotation(ZZAutowired.class);
                String beanName = autowired.value().trim();
                if ("".equals(beanName)) {
                    beanName = field.getType().getName();
                }
                field.setAccessible(true);
                try {
                    field.set(entry.getValue(),ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doInstance() {
        if (classNames.isEmpty()) {
            return;
        }
        try {
            for (String className : classNames) {
                Class clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(ZZController.class)) {
                    //beanId
                    //默认采用类名首字母小写,如果自己定义了名字的话,优先使用自己定义的名字
                    //根据类型匹配,利用接口作为key
                    ioc.put(lowerFirst(clazz.getSimpleName()), clazz.newInstance());
                } else if (clazz.isAnnotationPresent(ZZService.class)) {
                    ZZService service = (ZZService) clazz.getAnnotation(ZZService.class);
                    String beanName = service.value();
                    if ("".equals(beanName.trim())) {
                        beanName = lowerFirst(clazz.getSimpleName());
                    }
                    Object instance = clazz.newInstance();
                    ioc.put(beanName, instance);
                    Class[] interfaces = clazz.getInterfaces();
                    for (Class i : interfaces) {
                        ioc.put(i.getName(), instance);
                    }
                }else{
                    continue;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void doScanner(String packageName) {
        URL url = this.getClass().getClassLoader().getResource(packageName.replaceAll("\\.", "/"));
        File classDir = new File(url.getFile());
        for (File file : classDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(packageName + "." + file.getName());
            } else {
                String className = (packageName + "." + file.getName().replaceAll(".class", ""));
                classNames.add(className);
            }
        }
    }

    private void doLoadConfig(String location) {
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream(location)) {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String lowerFirst(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatcher(req,resp);
    }

    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) {
//        String uri = req.getServletPath();
//        if (!handlerMapping.containsKey(uri)) {
//            try {
//                resp.getWriter().write("404 NOT FOUND");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return;
//        }
//        System.out.println("获得对应的方法 : " + handlerMapping.get(uri));
//        Method method = handlerMapping.get(uri);
//        method.invoke()
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }


    private class Handler {
        protected  Object controller; //保存方法对应的实例
        protected Method method;  //保存映射的方法
        protected Pattern pattern;
        protected Map<String,Integer> paramIndexMapping; //参数顺序

        protected Handler(Pattern pattern,Object controller,Method method) {
            this.controller = controller;
            this.method = method;
            this.pattern = pattern;
            paramIndexMapping = new HashMap<>();
            putParamIndexMapping(method);
        }

        private void putParamIndexMapping(Method method) {
            Annotation[][] an = method.getParameterAnnotations();
            for (int i = 0; i < an.length; i++) {
                for (Annotation a : an[i]) {
                    if (a instanceof ZZRequestParam) {
                        String paramName = ((ZZRequestParam) a).value();
                        if (!"".equals(paramName.trim())) {
                            paramIndexMapping.put(paramName,i);
                        }
                    }
                }
            }

            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                Class<?> type = parameterTypes[i];
                if (type == HttpServletRequest.class || type == HttpServletResponse.class) {
                    paramIndexMapping.put(type.getName(),i);
                }
            }
        }
    }
}
