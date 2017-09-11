package gui;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.ScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.List;
import java.awt.Color;
import javax.swing.border.TitledBorder;

public class GUIWarning extends JDialog implements ActionListener {
	private int warningResult;

	public GUIWarning(String warningText) {
		setTitle("Advarsel");
		setSize(501, 311);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(warningText);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(28, 86, 421, 16);
		getContentPane().add(lblNewLabel);
		
		JButton btnAnuller = new JButton("Anuller");
		btnAnuller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setWarningResult(0);
				setVisible(false);
			}
		});
		btnAnuller.setBounds(62, 158, 113, 46);
		getContentPane().add(btnAnuller);
		
		JButton btnJa = new JButton("Ja");
		btnJa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setWarningResult(1);
				setVisible(false);
			}
		});
		btnJa.setBounds(297, 158, 119, 46);
		getContentPane().add(btnJa);
		setLocationRelativeTo(null);
	}

	public int getWarningResult() {
		return warningResult;
	}
	
	public void setWarningResult(int warningResult) {
		this.warningResult = warningResult;
	}
}
