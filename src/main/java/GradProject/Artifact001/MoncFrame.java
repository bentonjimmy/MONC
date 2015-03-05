package GradProject.Artifact001;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

public class MoncFrame extends JFrame {

	protected static MoncFrame frame;
	protected JPanel graphPanel;
	protected JFileChooser filechooser;
	private Integer nodeID;
	private static Settings settings = new Settings();
	private ViewController vc;
	protected JRadioButtonMenuItem rdbtnNetwork;
	protected JRadioButtonMenuItem rdbtnDendrogram;
	private String className = "MoncFrame";
	private String graphType = ViewController.NETWORK_GRAPH;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewController cont = new ViewController(Toolkit.getDefaultToolkit().getScreenSize());
					Adapter adapter = new Adapter();
					adapter.setSettings(settings);
					Controller controller = new Controller(cont, adapter);
					cont.setController(controller);
					frame = new MoncFrame(cont);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MoncFrame(ViewController vc) 
	{
		this.vc = vc;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, vc.getDim().width-25, vc.getDim().height-25);
		this.getContentPane().setLayout(new BorderLayout());
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		mntmOpen.addActionListener(new OpenFileListener());
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		mntmExit.addActionListener(new ExitListener());
		
		JMenu mnProcessData = new JMenu("Process Data");
		menuBar.add(mnProcessData);
		
		JMenuItem mntmSelectNode = new JMenuItem("Select Node");
		mnProcessData.add(mntmSelectNode);
		mntmSelectNode.addActionListener(new SelectNodeListener());
		
		JMenu mnGraph = new JMenu("Graph");
		menuBar.add(mnGraph);
		
		rdbtnNetwork = new JRadioButtonMenuItem("Network");
		rdbtnNetwork.setSelected(true);
		rdbtnNetwork.addActionListener(new GraphButtonListener());
		mnGraph.add(rdbtnNetwork);
		
		rdbtnDendrogram = new JRadioButtonMenuItem("Dendrogram");
		rdbtnDendrogram.addActionListener(new GraphButtonListener());
		mnGraph.add(rdbtnDendrogram);
		
		ButtonGroup bttngroup = new ButtonGroup();
		bttngroup.add(rdbtnNetwork);
		bttngroup.add(rdbtnDendrogram);
		
		filechooser = new JFileChooser();
		graphPanel = new JPanel();
		this.getContentPane().add(graphPanel, BorderLayout.CENTER);
	}
	
	class ExitListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			//Ask user if they want to exit
			int result = JOptionPane.showConfirmDialog(null, "Do you want to exit the program?", "Exit", JOptionPane.YES_NO_OPTION);
			//If they want to exit
			if(result == JOptionPane.YES_OPTION)
			{
				System.exit(0);
			}
		}
		
	}
	
	class OpenFileListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			int returnVal = filechooser.showOpenDialog(MoncFrame.this);
			if(returnVal == JFileChooser.APPROVE_OPTION)
			{
				File file = filechooser.getSelectedFile();
				if(file.canRead() && file.canWrite())
				{
					settings.setFileName(file.getName());
					settings.setFilePath(file.getParent());
				}
				else //file can't be read or written to
				{
					System.err.println(className+": File cannot be read/write");
				}
			}
		}
	}
	
	class SelectNodeListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			String results = JOptionPane.showInputDialog(MoncFrame.this, "Enter the label of the seed node:", "Enter Seed Node", JOptionPane.PLAIN_MESSAGE);
			if(results != null) //Test if user Canceled the OptionPane
			{
				nodeID = null;
				if(results.trim().isEmpty() == false) //A value was entered
				{
					//returns a null value if the result is not an integer
					try
					{
						nodeID = Integer.valueOf(results);
					}
					catch(NumberFormatException nfe)
					{
						System.err.println("Non-Integer value entered");
					}
					if(nodeID != null)
					{
						processData();
					}
				}
				else
				{
					System.err.println("No value was entered");
				}
				if(settings.getFileName() == null || settings.getFilePath() == null)
				{
					JOptionPane.showMessageDialog(MoncFrame.this, "Please select a file first", "No Data Selected", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	class GraphButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == rdbtnNetwork && settings.getFileName() != null) //The the network button is pressed
			{
				System.out.println(className+": Changing to network graph");
				graphType = ViewController.NETWORK_GRAPH;
				graphPanel = vc.changeGraph(graphType);
			}
			else if(e.getSource() == rdbtnDendrogram && settings.getFileName() != null) //Dendrogram button pressed
			{
				System.out.println(className+": Changing to dendrogram graph");
				graphType = ViewController.DENDROGRAM_GRAPH;
				graphPanel = vc.changeGraph(graphType);
			}
			else
			{
				System.err.println(className+": Error in Graph Selection or no data selected");
			}
			frame.getContentPane().add(graphPanel, BorderLayout.CENTER);
			frame.revalidate();
			
		}
		
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}
	
	protected void processData()
	{
		graphPanel = vc.updateView(nodeID, graphType);
		frame.getContentPane().add(graphPanel, BorderLayout.CENTER);
		frame.revalidate();
		
	}

}
