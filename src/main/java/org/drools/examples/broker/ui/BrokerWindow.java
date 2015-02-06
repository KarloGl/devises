
package org.drools.examples.broker.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.drools.examples.broker.model.Company;
import org.drools.examples.broker.model.StockTick;

/**
 * Main window
 */
public class BrokerWindow {

    private final JFrame                    frame;
    private final Map<String, CompanyPanel> companies;
    private final LogPanel logPanel;

    public BrokerWindow(final Collection<Company> companies, boolean exitOnClose) {
        this.logPanel = new LogPanel();
        this.companies = new HashMap<String, CompanyPanel>();
        this.frame = buildFrame( companies, exitOnClose );
    }

    private JFrame buildFrame(final Collection<Company> companies, boolean exitOnClose) {
        JPanel contentPanel = new JPanel(new BorderLayout());

        JPanel companyListPanel = new JPanel(new GridLayout(0, 2));

        for ( Company company : companies ) {
            CompanyPanel panel = new CompanyPanel( company );
            this.companies.put( company.getSymbol(), panel );
            companyListPanel.add(panel);
        }
        contentPanel.add( companyListPanel, BorderLayout.WEST );
        contentPanel.add( logPanel, BorderLayout.CENTER );

        JFrame frame = new JFrame();
        frame.setContentPane(contentPanel);

        frame.setDefaultCloseOperation(exitOnClose ? JFrame.EXIT_ON_CLOSE : JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle( "BRMS projet: règles drools" );
        frame.setResizable( true );
        frame.pack();
        frame.setLocationRelativeTo(null); // Center in screen

        return frame;
    }
    
    public void show() {
        this.frame.setVisible( true );
    }
    
    public void updateCompany( String symbol ) {
        this.companies.get( symbol ).updateCompany();
    }

    public void log( String message ) {
        this.logPanel.log( message );
    }

}
