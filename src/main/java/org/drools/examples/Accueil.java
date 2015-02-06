/*
 * Copyright 2011 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.examples;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.drools.examples.broker.BrokerExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Accueil extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3450861392092514319L;

	public static void main(String[] args) {
        Accueil droolsJbpmIntegrationExamplesApp = new Accueil();
        droolsJbpmIntegrationExamplesApp.pack();
        droolsJbpmIntegrationExamplesApp.setVisible(true);
    }

  
	protected final transient Logger logger = LoggerFactory.getLogger(getClass());

	public Accueil() {
        super("Logiciel de tests règles métiers");
        setContentPane(createContentPane());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logger.info("programme lancé !");
    }

    private Container createContentPane() {
        JPanel contentPane = new JPanel(new GridLayout(0, 1));
        contentPane.add(new JLabel("Quel exemple lancer?"));

        
        contentPane.add(new JButton(new AbstractAction("Fréquence d'évolution élevée (devises)") {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
                new BrokerExample().init(false);
            }
        }));
        
        
        

        return contentPane;
    }

}
