

package org.drools.examples.broker;

import org.drools.examples.broker.events.Event;
import org.drools.examples.broker.events.EventReceiver;
import org.drools.examples.broker.model.Company;
import org.drools.examples.broker.model.CompanyRegistry;
import org.drools.examples.broker.model.StockTick;
import org.drools.examples.broker.ui.BrokerWindow;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;

/**
 * The broker application
 */
public class Broker implements EventReceiver, BrokerServices {

   private BrokerWindow window;
    private CompanyRegistry companies;
    private KieSession session;
    private EntryPoint tickStream;

    public Broker(BrokerWindow window,
                  CompanyRegistry companies) {
        super();
        this.window = window;
        this.companies = companies;
        this.session = createSession();
        this.tickStream = this.session.getEntryPoint( "StockTick stream" );
    }

    @SuppressWarnings("unchecked")
    public void receive(Event<?> event) {
        try {
            StockTick tick = ((Event<StockTick>) event).getObject();
            Company company = this.companies.getCompany( tick.getSymbol() );
            this.tickStream.insert( tick );
            this.session.getAgenda().getAgendaGroup( "evaluation" ).setFocus();
            this.session.fireAllRules();
            window.updateCompany( company.getSymbol() );
            
        } catch ( Exception e ) {
            System.err.println("=============================================================");
            System.err.println("Unexpected exception caught: "+e.getMessage() );
            e.printStackTrace();
        }
    }
    
    private KieSession createSession() {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();
        session = kc.newKieSession("BrokerKS");

        session.setGlobal( "services", this );
        for( Company company : this.companies.getCompanies() ) {
            session.insert( company );
        }
        session.fireAllRules();
        return session;
    }

    public void log(String message) {
        window.log( message );
    }

}
