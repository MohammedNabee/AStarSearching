package display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import searchSpace.ISearchSpace;
import searchSpace.Node;
import searchTechnique.AStar;
import validation.ValidateInput;

/**
 * GUIDisplay     Provides a GUI display for the search and it operations.
 * @author        Mohammed Nabee
 */
public class GUIDisplay extends Display
{
	private static final long serialVersionUID = 1L;
	
	private JLabel timeLbl = new JLabel();
	private JLabel movesLbl = new JLabel();
	private JLabel costLbl = new JLabel();
	private JButton button = new JButton("Open");
	private JPanel pathPanel = new JPanel();
	private JScrollPane scrollPane = new JScrollPane();
	private JPanel holderPanel = new JPanel();
	private JPanel statsPanel = new JPanel();
	private JPanel btnPanel = new JPanel();

	/**
	 * Constructor Initializes its search space and creates the 
	 * GUI frame.
	 */ 
	public GUIDisplay(ISearchSpace searchSpace) 
	{
		super(searchSpace);
		
		displaySearchSpace(new Object());

		/* the frame configuration */
		super.setTitle("AStar Optimal Path");
		super.setSize(400, 500);
		super.setLocationRelativeTo(null);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setVisible(true);

		/* Button Action Listener */
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				/* Open a file if the search space is empty */
				if (displayableSpace.getVerticalLength() == 0 || inputfile.isEmpty())
				{
					JFileChooser fileChooser = new JFileChooser();
					if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
					{
						String buttonText = "Calculate";
						File file = fileChooser.getSelectedFile();
						inputfile = file.getAbsolutePath();
						int idx = inputfile.lastIndexOf('\\');
						outputFile = inputfile.substring(0,idx) + "\\output.txt";

						ValidateInput validateInput = new ValidateInput(inputfile);
						try 
						{
							if (validateInput.isValid())
							{	
								try 
								{
									displayableSpace.loadSearchSpace(inputfile);	
								} 
								catch (IOException e1) 
								{
									JOptionPane.showMessageDialog(new JPanel(), e1.getMessage(), "IO Error", JOptionPane.ERROR_MESSAGE);
									buttonText = "Open";
								}
							}
							else
							{
								buttonText = "Open";
							}
						} 
						catch (Exception e1) 
						{
							JOptionPane.showMessageDialog(new JPanel(), e1.getMessage(), "IO Error", JOptionPane.ERROR_MESSAGE);
							buttonText = "Open";
						}

						populate();
						button.setText(buttonText);
					}

				}
				/* Action to perform when in the calculate state */
				else if (button.getText().equals("Calculate"))
				{
					AStar astar = new AStar(displayableSpace);
					long startTime = System.currentTimeMillis();
					astar.search();
					long endTime = System.currentTimeMillis();
					displayableSpace.printShortestPath();
					populate();
					setStatistics (endTime-startTime,
							displayableSpace.shortestPathLength(), 
							displayableSpace.costOfShortestPath());
					button.setText("Exit");
					Display fileDisplay = new FileDisplay(displayableSpace);
					((FileDisplay)fileDisplay).setStatistics (endTime-startTime,
							displayableSpace.shortestPathLength(), 
							displayableSpace.costOfShortestPath());
					fileDisplay.displaySearchSpace(outputFile);
				}
				else if (button.getText().equals("Exit"))
				{
					System.exit(0);
				}
			}
		});

	}

	/**
	 * @function displaySearchSpace Displays the search space
	 * in its current state, on the GUI.
	 * 
 	 * @param arg Not used.
 	 * 
	 * @exception None
	 * @return None.
	 ***********************************************/ 
	public void displaySearchSpace(Object arg) 
	{
		/* Set the panel layouts */
		holderPanel.setLayout(new BorderLayout());
		statsPanel.setLayout(new GridLayout(3,2,5,2));

		/* Populate panel with search space to display */
		populate();

		/* Configure statistic fields */
		statsPanel.setBorder(new TitledBorder("Statistics"));
		statsPanel.add(new JLabel("Time (ms) :"));
		statsPanel.add(timeLbl);
		statsPanel.add(new JLabel("Number of moves :"));
		statsPanel.add(movesLbl);
		statsPanel.add(new JLabel("Total Cost of Path :"));
		statsPanel.add(costLbl);

		/* Add the button to the button panel */
		button.setMaximumSize(getMaximumSize());
		button.setFocusable(true);
		btnPanel.add(button);
		btnPanel.setBorder(new LineBorder(Color.BLACK));

		/* Add components to the frame */
		JPanel p = new JPanel(new FlowLayout());
		p.add(statsPanel);
		p.add(button);
		holderPanel.add(p, BorderLayout.SOUTH);
		holderPanel.add(scrollPane, BorderLayout.CENTER);
		
		/* Add the components to the main frame */
		add(holderPanel);
	}

	/**
	 * @function setStatistics Sets the statistics for this 
	 * display.
	 * 
 	 * @param time The time taken to calculate the shortest 
 	 * path.
 	 * 
 	 * @param moves The number of moves taken to reach the goal
 	 * in the shortest path.
 	 * 
 	 * @param cost The cost of the shortest path.
 	 * 
	 * @exception None.
	 * @return None.
	 ***********************************************/ 
	public void setStatistics (double time, int moves, double cost)
	{
		this.timeLbl.setText(""+time);
		this.movesLbl.setText(""+moves);
		this.costLbl.setText(""+cost);
	}
	
	/**
	 * Helper function to populate the search space on the GUI.
	 */ 
	private void populate()
	{
		if (displayableSpace.getVerticalLength() != 0)
		{
			/* Fist remove components already on the panel */
			pathPanel.removeAll();
			pathPanel.setLayout(new GridLayout(displayableSpace.getVerticalLength(),
					displayableSpace.getHorizontalLength()));

			/* display the search space on the frame */
			ArrayList<ArrayList<Node>> matrix = displayableSpace.getNodeMatrix();
			
			for (ArrayList<Node> list: matrix)
			{
				for (Node node: list)
				{
					JLabel lbl = new JLabel(""+node.getRepresentation());
					pathPanel.add(lbl);
				}
			}
			/* Reset the JScrollPane with the updated Panel */
			scrollPane.setViewportView(pathPanel);
		}
	}
}
