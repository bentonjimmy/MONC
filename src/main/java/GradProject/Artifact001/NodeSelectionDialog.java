package GradProject.Artifact001;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.Document;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class NodeSelectionDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField tfNodeLabel;
	private Integer nodeID = null;
	private JButton okButton;
	private JButton cancelButton;

	/**
	 * Create the dialog.
	 */
	public NodeSelectionDialog() {
		setAlwaysOnTop(true);
		setTitle("Enter Seed Node");
		setResizable(false);
		setBounds(100, 100, 309, 212);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JTextPane txtpnEnterTheLabel = new JTextPane();
			txtpnEnterTheLabel.setText("Enter the label of the seed node:");
			contentPanel.add(txtpnEnterTheLabel);
		}
		{
			tfNodeLabel = new JTextField();
			contentPanel.add(tfNodeLabel);
			tfNodeLabel.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(this);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(this);
			}
		}
	}
	
	/**
	 * Displays the dialog
	 * @return
	 */
	public Integer showDialog()
	{
		nodeID = null;
		setVisible(true);
		
		return nodeID;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if(source == okButton)
		{
			Document doc = tfNodeLabel.getDocument();
			String text = doc.toString();
			if(text.trim().isEmpty() == false) //A value was entered
			{
				try
				{
					nodeID = Integer.getInteger(text);
				}
				catch(NumberFormatException nfe)
				{
					System.err.println("Value entered is not an integer");
				}
			}
		}
		setVisible(false);
	}
	

}
