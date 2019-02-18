package com.test;

import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.HashMap;

/**
 * Created by majianghua on 2018/1/3.
 */
public class Test01 {
    int defaltLength=4;

    @Test
    public void haha() {
//        System.out.println(hash("s"));
//        System.out.println(hash("u"));
//        System.out.println(hash("i"));
//        System.out.println(hash("p"));

        System.out.println(Long.MAX_VALUE/Integer.MAX_VALUE);
    }

    public int hash(Object o) {
        return o.hashCode() % defaltLength;
    }


}
