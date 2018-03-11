package com.test;

import org.junit.Test;

/**
 * Created by majianghua on 2018/1/3.
 */
public class Test01 {
    int defaltLength=4;

    @Test
    public void haha() {
        System.out.println(hash("s"));
        System.out.println(hash("u"));
        System.out.println(hash("i"));
        System.out.println(hash("o"));
    }
    public int hash(Object o) {
        return o.hashCode() % defaltLength;
    }


}
