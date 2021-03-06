
package jsat.guitool;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import jsat.DataSet;
import jsat.classifiers.DataPoint;
import jsat.linear.Vec;

/**
 *
 * @author Edward Raff
 */
public class SingleVariableViewDialog extends JDialog
{

	private static final long serialVersionUID = -3635281795623474239L;
	final JPanel panel;
    final List<Vec> dataSets;
    final String[] titles;

    public SingleVariableViewDialog(DataSet dataSet)
    {
        panel = new JPanel(new BorderLayout());
        this.dataSets = new ArrayList<Vec>(dataSet.getSampleSize());
        for(DataPoint dp : (List<DataPoint>) dataSet.getDataPoints())
            this.dataSets.add(dp.getNumericalValues());
        this.titles = new String[dataSet.getNumNumericalVars()];
        for(int i = 0; i < titles.length; i++)
            titles[i] = dataSet.getNumericName(i);

        final JComboBox jc = new JComboBox(titles);

//        JTextField
        final JTextField minLabel = new JTextField();
        minLabel.setEditable(false);
        final JTextField maxLabel = new JTextField();
        maxLabel.setEditable(false);
        final JTextField meanLabel = new JTextField();
        meanLabel.setEditable(false);
        final JTextField medianLabel = new JTextField();
        medianLabel.setEditable(false);
        final JTextField stndDevLabel = new JTextField();
        stndDevLabel.setEditable(false);

        jc.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                int index = jc.getSelectedIndex();
                setTitle("Singl Var Stat: " + titles[index]);
                minLabel.setText(dataSets.get(index).min() + "");
                maxLabel.setText(dataSets.get(index).max() + "");
                medianLabel.setText(dataSets.get(index).median() + "");
                meanLabel.setText(dataSets.get(index).mean() + "");
                stndDevLabel.setText(dataSets.get(index).standardDeviation() + "");
            }
        });

        jc.setSelectedIndex(0);

        minLabel.setBorder(BorderFactory.createTitledBorder("Min:"));
        maxLabel.setBorder(BorderFactory.createTitledBorder("Max:"));
        meanLabel.setBorder(BorderFactory.createTitledBorder("Mean:"));
        medianLabel.setBorder(BorderFactory.createTitledBorder("Meadian:"));
        stndDevLabel.setBorder(BorderFactory.createTitledBorder("Standard Deviation:"));

        panel.add(jc, BorderLayout.NORTH);
        JPanel subPanel = new JPanel(new GridLayout(5, 1));
        subPanel.add(minLabel);
        subPanel.add(maxLabel);
        subPanel.add(medianLabel);
        subPanel.add(meanLabel);
        subPanel.add(stndDevLabel);
        panel.add(new JScrollPane(subPanel), BorderLayout.CENTER);


        setContentPane(panel); 
    }


}
