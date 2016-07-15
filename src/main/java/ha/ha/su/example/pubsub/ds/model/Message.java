package ha.ha.su.example.pubsub.ds.model;

/**
 * Created by harid on 11/07/16.
 */
public class Message implements java.io.Serializable {

    private String channelName;
    private String emailId;
    private String heading;
    private String messageTxt;


    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getMessageTxt() {
        return messageTxt;
    }

    public void setMessageTxt(String messageTxt) {
        this.messageTxt = messageTxt;
    }
}
