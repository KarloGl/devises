package org.drools.examples.broker.events;

import java.util.Date;

import org.drools.core.time.Job;
import org.drools.core.time.JobContext;
import org.drools.core.time.JobHandle;
import org.drools.core.time.TimerService;
import org.drools.core.time.Trigger;

/**
 * An event feeder reads an event data file and publishes
 * the read events to the consumer
 */
public class EventFeeder {

    private final TimerService  clock;
    private final EventSource   source;
    private final EventReceiver sink;

    public EventFeeder(final TimerService clock,
                       final EventSource source,
                       final EventReceiver sink) {
        this.clock = clock;
        this.source = source;
        this.sink = sink;
    }

    /**
     * For this simple demo, we are loading the
     * whole stream of events into the memory.
     */
    public void feed() {
        if ( source.hasNext() ) {
            Event< ? > event = source.getNext();
            FeedContext context = new FeedContext( event );
            FeedTrigger trigger = new FeedTrigger();
            trigger.setNextFireTime( event.getDate() );
            FeedJob job = new FeedJob( source,
                                       sink,
                                       trigger,
                                       clock );
            clock.scheduleJob( job,
                               context,
                               trigger );
        }
    }

    private static class FeedJob implements Job {

        private final EventSource   source;
        private final EventReceiver sink;
        private final FeedTrigger   trigger;
        private final TimerService  clock;

        public FeedJob(final EventSource source,
                       final EventReceiver sink,
                       final FeedTrigger trigger,
                       final TimerService clock) {
            this.source = source;
            this.sink = sink;
            this.trigger = trigger;
            this.clock = clock;
        }

        public void execute(JobContext context) {
            this.sink.receive( ((FeedContext) context).event );
            if ( this.source.hasNext() ) {
                ((FeedContext) context).setEvent( this.source.getNext() );
                this.trigger.setNextFireTime( ((FeedContext) context).getEvent().getDate() );
                clock.scheduleJob( this,
                                   context,
                                   trigger );
            }
        }
    }

    private static class FeedContext implements JobContext {

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JobHandle  handle;
        private Event< ? > event;

        public FeedContext(Event< ? > event) {
            super();
            this.event = event;
        }

        public JobHandle getJobHandle() {
            return this.handle;
        }

        public void setJobHandle(JobHandle handle) {
            this.handle = handle;
        }

        public Event< ? > getEvent() {
            return event;
        }

        public void setEvent(Event< ? > event) {
            this.event = event;
        }
    }

    private static class FeedTrigger
        implements
        Trigger {
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Date next;

        public void setNextFireTime(Date date) {
            this.next = date;
        }

        public Date hasNextFireTime() {
            return next;
        }

        public Date nextFireTime() {
            Date ret = next;
            next = null;
            return ret;
        }
    }

}
