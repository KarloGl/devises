
package org.drools.examples.broker.events;


public interface EventReceiver {
    
    public void receive( Event<?> event );

}
