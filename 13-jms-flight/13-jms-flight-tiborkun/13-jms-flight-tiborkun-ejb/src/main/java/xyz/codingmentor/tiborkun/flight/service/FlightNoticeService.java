package xyz.codingmentor.tiborkun.flight.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.DependsOn;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Topic;
import xyz.codingmentor.tiborkun.flight.api.CRUDRepository;
import xyz.codingmentor.tiborkun.flight.api.FlightNotice;
import xyz.codingmentor.tiborkun.flight.dto.Flight;

/**
 *
 * @author Tibor Kun
 */
@DependsOn("Loader")
@Singleton
@Startup
public class FlightNoticeService implements FlightNotice {

    @Inject
    private CRUDRepository<Flight> repository;

    @Inject
    private JMSContext context;

    @Resource(lookup = "jms/myTopic")
    private Topic topic;

    private String message;
    private Calendar calendar;
    private Date startTime;
    private Date endTime;
    private List<Flight> flights;

    @Override
    public void deletedFligth(Long flightNumber) {
        message = "deleted flight. Flight number: " + flightNumber;
        context.createProducer().send(topic, message);
    }

    @Override
    public void updatedFlight(Flight flight) {
        message = "updated flight. Flight number: " + flight.getFlightNumber();
        context.createProducer().send(topic, message);
    }

    @Schedule(minute = "*", hour = "*")
    @Override
    public void startsInAnHour() {
        initDates();
        flights = repository.getAll();
        for (Flight flight : flights) {
            if (flight.getDepartureDate().after(startTime) && flight.getDepartureDate().before(endTime)) {
                context.createProducer().send(topic, createMessage(flight));
            }
        }
    }

    private String createMessage(Flight flight) {
        message = "flight departs in an hour:"
                + "\n\tflight number: " + flight.getFlightNumber()
                + "\n\tdeparture time: " + flight.getDepartureDate().toString()
                + "\n\tdeparture: " + flight.getDeparture()
                + "\n\tarrival: " + flight.getArrival();
        return message;
    }

    private void initDates() {
        calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 60);
        startTime = calendar.getTime();
        calendar.add(Calendar.MINUTE, 1);
        endTime = calendar.getTime();
    }

}
