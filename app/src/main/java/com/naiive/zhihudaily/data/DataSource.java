package com.naiive.zhihudaily.data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangzhe on 16/6/17.
 */
public interface DataSource {

    class Proxy {
        private static final Proxy INSTANCE = new Proxy();

        private Map<Class, Object> objects;

        private Proxy() {
            objects = new HashMap<>();
        }

        public static Proxy create() {
            return INSTANCE;
        }


        public void init(Class... clazz) {
            for (Class t : clazz) {
                try {
                    objects.put(t, t.newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        public <T> T get(Class clazz) {
            if (objects.get(clazz) == null) {
                try {
                    objects.put(clazz, clazz.newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return (T) objects.get(clazz);
        }

        public void remove(Class clazz){
            objects.remove(clazz);
        }


    }
}
