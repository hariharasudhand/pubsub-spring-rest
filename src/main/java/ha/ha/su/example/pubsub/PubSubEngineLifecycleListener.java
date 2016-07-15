package ha.ha.su.example.pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.spi.Container;
import org.glassfish.jersey.server.spi.ContainerLifecycleListener;

public class PubSubEngineLifecycleListener implements ContainerLifecycleListener {

    static Logger logger = Logger.getLogger(PubSubEngineLifecycleListener.class);
    JedisPool jedispool = new JedisPool("localhost");

    public void onStartup(Container container) {

        logger.info("*****************************************************************************************");
        logger.info("							HaHaSu's PubSub Engine Started..								 		  ");
        logger.info("*****************************************************************************************");

        logger.info("*****************************************************************************************");
        try {
            testMessages();
        }catch(Exception err) {
            logger.info("Error in subscription " + err.toString());
        }
    }

    public void onReload(Container container) {
        logger.info("*****************************************************************************************");
        logger.info(" 							HaHaSu's PubSub  Engine Reloaded..										  ");
        logger.info("*****************************************************************************************");
    }

    public void onShutdown(Container container) {

        //perform cleanup Tasks
        //1 : Close the hibernate session factory and clear connections


        logger.info("*****************************************************************************************");
        logger.info("							HaHaSu's PubSub  Engine Stopped..										 ");
        logger.info("*****************************************************************************************");
    }


    private static final String channel_name= "news7";


    public void testMessages() throws Exception
    {

        final Jedis subscriberJedis = jedispool.getResource();

        final Subscriber subscriber = new Subscriber();
        new Thread(new Runnable(){
            public void run()
            {
                try
                {
                    logger.info("Subscribing to " + channel_name);
                    subscriberJedis.subscribe(subscriber,channel_name);
                    logger.info("Subscription ended.");
                }
                catch (Exception e)
                {
                    logger.info("Subscribing failed."+e);
                }
            }
        }).start();
    }
}