
package org.drools.examples.broker.events;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.drools.examples.broker.model.Company;
import org.drools.examples.broker.model.CompanyRegistry;
import org.drools.examples.broker.model.StockTick;


public class EventGenerator {

    private static final String DATA_FILE = "src/main/resources/org/drools/examples/broker/data/stocktickstream.dat";
    
    // creating 3 random objects to avoid interference among them on the distribution of values
    private static Random steps = new Random( System.currentTimeMillis() );
    private static Random symbols = new Random( System.currentTimeMillis() );
    private static Random prices = new Random( System.currentTimeMillis() );
    
    public static void main(String args[]) throws IOException {
        // 20 minutes
        long timespam = 20 * 60 * 1000;
        long[] interval = new long[]{ 20, 200 };
        // price changes: +- 10%
        double[] priceChanges = new double[] { -0.1, 0.1 };
        // starting price range
        double[] startingPrices = new double[]{ 50, 120 };
        // companies
        Company[] companies = (new CompanyRegistry()).getCompanies().toArray( new Company[0] );
        // persister helper
        StockTickPersister persister = new StockTickPersister();
        persister.openForSave( new FileWriter( DATA_FILE ) );
        
        System.out.print("Generation des données pendant 20 min...");

        // initializing starting prices
        for( Company company : companies ) {
            company.setCurrentPrice( nextStartingPrice( startingPrices ) );
            StockTick tick = new StockTick( company.getSymbol(),
                                            company.getCurrentPrice(),
                                            0 );
            persister.save( tick );
        }
        
        
        for( long offset = 0; offset < timespam; offset += nextStep( interval ) ) {
            int company = symbols.nextInt( companies.length );
            double price = companies[company].getCurrentPrice() * (1 + nextPriceChange( priceChanges ));
            StockTick tick = new StockTick(companies[company].getSymbol(),
                                           price,
                                           offset );
            persister.save( tick );
            companies[company].setCurrentPrice( price );
        }
        persister.close();
        
        System.out.println("fait.");
    }

    private static double nextStartingPrice(double[] startingPrices) {
        double range = startingPrices[1]-startingPrices[0];
        return ( prices.nextDouble() * range ) + startingPrices[0];
    }

    private static long nextStep(long[] interval) {
        long range = interval[1]-interval[0];
        return (long) ( ( steps.nextFloat() * range ) + interval[0] );
    }

    private static double nextPriceChange(double[] interval) {
        double range = interval[1]-interval[0];
        return ( prices.nextDouble() * range ) + interval[0];
    }

}
