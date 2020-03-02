package com.lcb.imageloader;

import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * IOC工具类
 */
public class InjectUtils {


    /**
     * 依赖注入
     *
     * @param context 上下文
     */
    public static void inject(Object context) {

        injectLayout(context);

        injectView(context);


    }

    private static void injectView(Object context) {
        Class<?> aClass = context.getClass();

        Field[] fields = aClass.getDeclaredFields();
        if (fields != null) {
            for (Field field : fields) {
                InjectView injectView = field.getAnnotation(InjectView.class);
                if (injectView != null) {
                    int viewId = injectView.value();
                    System.out.println("-----injectView----- " + viewId);
                    try {
                        Method method = aClass.getMethod("findViewById", int.class);
                        View view = (View) method.invoke(context, viewId);
                        field.setAccessible(true);
                        field.set(context, view);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }

    }

    private static void injectLayout(Object context) {
        Class<?> aClass = context.getClass();
        ContentView contentView = aClass.getAnnotation(ContentView.class);
        if (contentView != null) {
            int layoutId = contentView.value();
            try {
                Method method = context.getClass().getMethod("setContentView", int.class);
                method.invoke(context, layoutId);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }

}
