
package org.drools.examples.broker.ui;

import java.awt.Color;
import java.text.NumberFormat;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.drools.examples.broker.model.Company;

/**
 * A class that manages a company UI panel
 */
public class CompanyPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int FIELD_COLUMN_SIZE = 8;

    private final Company model;

    private final JTextField currentField;
    private final JTextField previousField;

    private NumberFormat format = NumberFormat.getCurrencyInstance();

    public CompanyPanel(Company model) {
        this.model = model;
        GroupLayout formLayout = new GroupLayout(this);
        setLayout(formLayout);
        formLayout.setAutoCreateGaps(true);
        formLayout.setAutoCreateContainerGaps(true);

        JLabel companyNameField = new JLabel(model.getName());
        companyNameField.setFont(companyNameField.getFont().deriveFont(companyNameField.getFont().getSize() + 2.0F));

        JLabel currentLabel = new JLabel("Actuel: ");
        currentField = new JTextField(format.format( model.getCurrentPrice() ), FIELD_COLUMN_SIZE);
        currentField.setEditable(false);
        JLabel previousLabel = new JLabel("Dernier:");
        previousField = new JTextField(format.format( model.getPreviousPrice() ), FIELD_COLUMN_SIZE);
        previousField.setEditable(false);

        formLayout.setHorizontalGroup(
                formLayout.createParallelGroup()
                        .addComponent(companyNameField)
                        .addGroup(formLayout.createSequentialGroup()
                                .addGap(10)
                                .addComponent(currentLabel)
                                .addGap(10)
                                .addComponent(currentField))
                        .addGroup(formLayout.createSequentialGroup()
                                .addGap(10)
                                .addComponent(previousLabel)
                                .addGap(10)
                                .addComponent(previousField))
        );
        formLayout.setVerticalGroup(
                formLayout.createSequentialGroup()
                        .addComponent(companyNameField)
                        .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addGap(10)
                                .addGroup(formLayout.createSequentialGroup()
                                        .addComponent(currentLabel)
                                        .addComponent(previousLabel))
                                .addGap(10)
                                .addGroup(formLayout.createSequentialGroup()
                                        .addComponent(currentField)
                                        .addComponent(previousField))
                        ));
    }

    public void updateCompany() {
        SwingUtilities.invokeLater( new Runnable() {
            public void run() {
                currentField.setText( format.format( model.getCurrentPrice() ) );
                previousField.setText( format.format( model.getPreviousPrice() ) );
                if ( model.getCurrentPrice() > model.getPreviousPrice() ) {
                    currentField.setForeground( Color.BLUE );
                } else {
                    currentField.setForeground( Color.RED );
                }
            }
        } );
    }
}
