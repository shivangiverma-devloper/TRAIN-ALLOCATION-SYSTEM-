import java.awt.BorderLayout;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.Color;

import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JPanel;

import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddTrain {
	
	
	
	
	

	public AddTrain(JFrame oldFrame, Database database) throws SQLException {
		
		
		
	
		JFrame frame =new JFrame("Add Train");
		frame.setSize(750, 400);
		frame.getContentPane().setLayout(new BorderLayout ());
		frame.setLocationRelativeTo(oldFrame);
		frame.getContentPane().setBackground(Color.decode("#EBFFD8"));
		
		JPanel panel = new JPanel (new GridLayout(4, 2, 20,20));
		panel.setBackground(null);
		panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 30, 50));
		
		panel.add(GUI.JLabel("ID"));
		JLabel id= GUI.JLabel(String.valueOf(TrainsDatabase.getNextID(database)));
		panel.add(id);
		
		
		panel.add(GUI.JLabel("Capacity:"));
		JTextField capacity = GUI.JTextField();
		panel.add(capacity);
		
		
		
		panel.add(GUI.JLabel("Description"));
		JTextField description = GUI.JTextField();
		panel.add(description);
		
		JButton cancel =GUI.JButton ("Cancel");
		cancel.addActionListener(new ActionListener (){
			
			public void actionPerformed(ActionEvent e) {
			frame.dispose();
			
		}
		});
		panel.add(cancel);
		
		JButton  sumbit =GUI.JButton ("Sumbit");
		sumbit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				   try {
			            Train t = new Train();
			            t.setID(Integer.parseInt(id.getText()));
			            t.setCapacity(Integer.parseInt(capacity.getText()));
			            t.setDescription(description.getText());
			            TrainsDatabase.AddTrain(t, database);
			            JOptionPane.showMessageDialog(frame, "Train added successfully");
			            frame.dispose();
			        } catch (Exception ex) {
			            ex.printStackTrace(); // Print the stack trace to the console
			            JOptionPane.showMessageDialog(frame, "Operation failed: " + ex.getMessage());
			        }
			    }
			});
		panel.add(sumbit);
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		
		
	}
	
}
