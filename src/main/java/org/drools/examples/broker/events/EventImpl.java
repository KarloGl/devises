
package org.drools.examples.broker.events;

import java.util.Date;

public class EventImpl<T> implements Event<T> {

    private final long timestamp;
    private final T object;
    
    public EventImpl(long timestamp,
                     T object) {
        super();
        this.timestamp = timestamp;
        this.object = object;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public T getObject() {
        return object;
    }
    
    public Date getDate() {
        return new Date( this.timestamp );
    }

    public String toString() {
        return "Event( "+timestamp+", "+object+" )";
    }
    
}
