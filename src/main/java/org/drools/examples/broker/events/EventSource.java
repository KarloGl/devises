
package org.drools.examples.broker.events;

public interface EventSource {
    
    public boolean hasNext();
    
    public Event<?> getNext();

}
