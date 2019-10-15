package com.hz;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.Properties;

public class Client {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("client start");
        ClientConfig config = new ClientConfig();
        HazelcastInstance client = HazelcastClient.newHazelcastClient(config);
        System.out.println("client connected");
        IMap map = client.getMap("map");
        for (int i = 0; i < 100; i++) {
            map.put(i, i);
            System.out.println(map.size());
        }
        while (true) {
            System.out.println("map size:" + map.size());
            Thread.sleep(3000);
        }
    }

}
