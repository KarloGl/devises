
package org.drools.examples.broker.model;

public class SuddenDropEvent {
    
    private String symbol;
    private double percent;
    private long timestamp;
    
    public SuddenDropEvent() {
        super();
    }
    
    public SuddenDropEvent(String symbol,
                           double percent,
                           long timestamp) {
        super();
        this.symbol = symbol;
        this.percent = percent;
        this.timestamp = timestamp;
    }

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public double getPercent() {
        return percent;
    }
    public void setPercent(double percent) {
        this.percent = percent;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
