package xyz.codingmentor.tiborkun.flight.tracker;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author Tibor Kun
 */
@MessageDriven(mappedName = "jms/myTopic")
public class FlightNoticeTracker implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(FlightNoticeTracker.class.getName());

    public FlightNoticeTracker() {
        // notning to initialize
    }

    @Override
    public void onMessage(Message message) {
        try {
            LOGGER.log(Level.INFO, "Flights notice: {0}", message.getBody(String.class));
        } catch (JMSException ex) {
            Logger.getLogger(FlightNoticeTracker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
