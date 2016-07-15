package ha.ha.su.example.pubsub.ds.impl;

import java.net.URISyntaxException;
import ha.ha.su.example.pubsub.ds.model.Message;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by harid on 11/07/16.
 */
public class PublishDAO implements java.io.Serializable{


    JedisPool pool = null;
    Jedis jedis = null;

    public void pushIt(Message mesTex) {

        try {
            addAndPushStuff(mesTex);
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    private void addAndPushStuff(Message mesTex) throws URISyntaxException, Exception {


        try {
             pool = new JedisPool(new JedisPoolConfig(), "localhost");
             jedis = pool.getResource();
             jedis.set(mesTex.getChannelName(), mesTex.getMessageTxt());
             jedis.publish(mesTex.getChannelName(),mesTex.getMessageTxt());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        pool.destroy();
    }



}
