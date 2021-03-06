

package org.drools.examples.broker.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * A panel to log information
 */
public class LogPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JTextArea log;

    public LogPanel() {
        setLayout(new BorderLayout());
        log = new JTextArea();
        log.setEditable( false );
        
        JScrollPane areaScrollPane = new JScrollPane(log);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        add(areaScrollPane,
                BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setPreferredSize(new Dimension(400, 50));
    }

    public void log(final String text) {
        SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                log.append( text + "\n" );
            }
        } );
    }

}
