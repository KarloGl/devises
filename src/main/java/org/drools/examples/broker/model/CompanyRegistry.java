
package org.drools.examples.broker.model;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A helper class to load and return the list of companies
 */
public class CompanyRegistry {

    private Map<String, Company> companies;

    public CompanyRegistry() {
        this.companies = new HashMap<String, Company>();
        this.companies.put( "RHT",
                            new Company( "Red Hat Inc",
                                         "RHT" ) );
        this.companies.put( "JAVA",
                            new Company( "Sun Microsystems",
                                         "JAVA" ) );
        this.companies.put( "MSFT",
                            new Company( "Microsoft Corp",
                                         "MSFT" ) );
        this.companies.put( "ORCL",
                            new Company( "Oracle Corp",
                                         "ORCL" ) );
        this.companies.put( "SAP",
                            new Company( "SAP",
                                         "SAP" ) );
        this.companies.put( "GOOG",
                            new Company( "Google Inc",
                                         "GOOG" ) );
        this.companies.put( "YHOO",
                            new Company( "Yahoo! Inc",
                                         "YHOO" ) );
        this.companies.put( "IBM",
                            new Company( "IBM Corp",
                                         "IBM" ) );
    }
    
    public Collection<Company> getCompanies() {
        return Collections.unmodifiableCollection( companies.values() );
    }
    
    public Company getCompany( String symbol ) {
        return this.companies.get( symbol );
    }
    
}
