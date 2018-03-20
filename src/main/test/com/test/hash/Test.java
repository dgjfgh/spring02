package com.test.hash;

import java.util.ArrayList;

/**
 * Created by majianghua on 2018/1/19.
 */
public class Test {
    public static void main(String[] args) {
        String[] servers = {"192.168.0.0", "192.168.0.1", "192.168.0.2",
                "192.168.0.3", "192.168.0.4"};
        ArrayList<String> serversList = new ArrayList<String>();
        for (String server : servers) {
            serversList.add(server);
        }
        KetamaNodeLocator locator = new KetamaNodeLocator(serversList, HashAlgorithm.KETAMA_HASH, 100);

        String primary = locator.getPrimary("iuhhnjkanskdncauihduhaudhcvkjjjvh");
        System.out.println(primary);
    }
}
