package ha.ha.su.example.pubsub.rest.service;


import ha.ha.su.example.pubsub.ds.impl.PublishDAO;
import ha.ha.su.example.pubsub.ds.model.Message;

import org.apache.log4j.Logger;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;




@Path("pusher")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PubService {

    static HashMap<String, JsonObject> updatedStories = new HashMap();

    static Logger logger = Logger.getLogger(PubService.class);

    /*
     * 
     * In Param from JSON Client
       {
         "from": "hari",
         "to": "channelName",
         "subject": "subject1",
         "message": "message"
       }
     * 
     */

    @POST
    @Path("pushIt")
    public JsonObject postMessage(JsonObject param) {


        String strFrom = param.getString("from");
        String strTo = param.getString("to");
        String strSubject = param.getString("subject");
        String strMessage = param.getString("message");

        Message message = new Message();

        message.setChannelName(strTo);
        message.setEmailId(strFrom);
        message.setHeading(strSubject);
        message.setMessageTxt(strMessage);

        PublishDAO dao = new PublishDAO();
        dao.pushIt(message);


        JsonObject response = Json.createObjectBuilder()
                .add("responseCode", "200")
                .add("msg", "message :"+strSubject+" sent to "+strTo)
                .build();


        return response;


    }
}


