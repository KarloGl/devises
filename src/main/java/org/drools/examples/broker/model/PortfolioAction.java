
package org.drools.examples.broker.model;

public class PortfolioAction {
    private Action action;
    private String symbol;
    private int quant;
    
    public PortfolioAction() {
        super();
    }
    public PortfolioAction(Action action,
                           String symbol,
                           int quant) {
        super();
        this.action = action;
        this.symbol = symbol;
        this.quant = quant;
    }

    public Action getAction() {
        return action;
    }
    public void setAction(Action action) {
        this.action = action;
    }
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public int getQuant() {
        return quant;
    }
    public void setQuant(int quant) {
        this.quant = quant;
    }

    @Override
    public String toString() {
        return "PortfolioAction( "+action+" "+symbol+" "+quant+ " )";
    }

}
