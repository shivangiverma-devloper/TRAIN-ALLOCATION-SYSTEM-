import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.sql.SQLException;
import java.awt.event.*;



public class EditTrain {
 
	
	public EditTrain(JFrame parent ,Database database) throws SQLException {
		JFrame frame =new JFrame("Edit Train");
		frame.setSize(750, 400);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setLocationRelativeTo(parent);
		frame.getContentPane().setBackground(Color.decode("#EBFFD8"));
		
		
		
		JPanel panel =new JPanel(new GridLayout (4, 2, 20, 20));
		panel.setBackground(null);
		panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 30, 50));
	
	
	panel.add(GUI.JLabel("ID :"));
	
	JComboBox<String> id = GUI.JComboBox(TrainsDatabase.getTrainsIDs(database));
	panel.add(id);
	
	panel.add(GUI.JLabel("Capacity: "));
	
	JTextField capacity = GUI.JTextField();
	panel.add(capacity);
	
	panel.add(GUI.JLabel("Description :"));
	JTextField description = GUI.JTextField();
	panel.add(description);
	
	JButton sumbit = GUI.JButton("Submit");
	sumbit.addActionListener(new ActionListener() {
	
	
	
	public void actionPerformed(ActionEvent e) {
		Train t;
		try {
		
		 t =TrainsDatabase.getTrain(id.getSelectedItem().toString(), database);
		 t.setCapacity(Integer.parseInt(capacity.getText()));
	     t.setDescription(description.getText());
		 TrainsDatabase.editTrain(t, database);
			
		}
		catch(SQLException e1) 
		{
			JOptionPane.showMessageDialog(frame, e.toString());
			frame.dispose();
		}
		
	}
	});
		
		panel.add(sumbit);
	
	
	
	
	JButton delete = GUI.JButton("Delete");
	panel.add(delete);
	
    id.addActionListener(new ActionListener() {
    	
    
    	public void actionPerformed(ActionEvent e) {
			
			try {
                
                String selectedId = id.getSelectedItem().toString();
                
               
                Train t = TrainsDatabase.getTrain(selectedId, database);
                
               
                if (t != null) {
                    capacity.setText(String.valueOf(t.getCapacity()));
                    description.setText(t.getDescription());
                } else {
                    capacity.setText("");
                    description.setText("");
                    System.out.println("Train with ID " + selectedId + " not found.");
                }
            } catch (SQLException e1)
			{
                e1.printStackTrace();
            }
               
    	}
    });
   


	
	
	frame.getContentPane().add(panel ,BorderLayout.CENTER);
	frame.setVisible(true);
	}
	
}
