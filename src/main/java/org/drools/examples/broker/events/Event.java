
package org.drools.examples.broker.events;

import java.util.Date;


public interface Event<T> {
    
    /**
     * Returns the timestamp from this event
     * 
     * @return
     */
    public long getTimestamp();
    
    /**
     * This is the same as getTimestamp, but returns a Date 
     * object instead
     * 
     * @return
     */
    public Date getDate();

    /**
     * Returns this event's actual object
     * 
     * @return
     */
    public T getObject();

}
