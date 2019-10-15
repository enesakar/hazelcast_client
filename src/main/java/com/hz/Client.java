package com.hz;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.spi.impl.discovery.HazelcastCloudDiscovery;
import com.hazelcast.client.spi.properties.ClientProperty;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

import java.util.Properties;
import java.util.Random;

public class Client {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("client start");
        ClientConfig config = new ClientConfig();
        config.setGroupConfig(new GroupConfig("test2", "87196d3f1cc54c019362fccd6845a26a"));
        config.setProperty("hazelcast.client.statistics.enabled","true");
        config.setProperty(ClientProperty.HAZELCAST_CLOUD_DISCOVERY_TOKEN.getName(), "8oX4m76GI6tg5DJ0AVjmRFIijQa52ljnxRFUgqpv48RUi2vyYF");
        config.setProperty(HazelcastCloudDiscovery.CLOUD_URL_BASE_PROPERTY.getName(), "https://optimusprime.test.hazelcast.cloud");
        HazelcastInstance client = HazelcastClient.newHazelcastClient(config);
        IMap<String, String> map = client.getMap("map");
        map.put("key", "value");
        if(map.get("key").equals("value")) {
            System.out.println("Connection Successful!");
            System.out.println("Now, `map` will be filled with random entries.");
        }
        else {
            throw new RuntimeException("Connection failed, check your configuration.");
        }
        Random random = new Random();
        while (true) {
            int randomKey = (int) random.nextInt(100_000);
            map.put("key" + randomKey, "value" + randomKey);
            System.out.println("put success");
            map.get("key" + random.nextInt(100_000));
            System.out.println("get success");
            if(randomKey % 10 == 0 ) {
                System.out.println("map size:" + map.size());
            }
        }
    }

}
