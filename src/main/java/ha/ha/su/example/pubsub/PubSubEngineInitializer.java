package ha.ha.su.example.pubsub;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import javax.json.stream.JsonGenerator;


public class PubSubEngineInitializer extends ResourceConfig {


    public PubSubEngineInitializer() {
        packages("ha.ha.su.example.pubsub.rest.service");
        register(LoggingFilter.class);
        register(CORSResponseFilter.class);

        property(JsonGenerator.PRETTY_PRINTING, true);

        this.registerInstances(new PubSubEngineLifecycleListener());


    }
}



