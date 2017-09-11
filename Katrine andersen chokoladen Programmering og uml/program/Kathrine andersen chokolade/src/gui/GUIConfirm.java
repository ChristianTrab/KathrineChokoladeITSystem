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

public class GUIConfirm extends JDialog implements ActionListener {
	private int confirmResult;
	
	public GUIConfirm(String confirmText) {
		setTitle("Bekræftigelse");
		setSize(501, 311);
		getContentPane().setLayout(null);
		
		JLabel lblLabel = new JLabel(confirmText);
		lblLabel.setBounds(54, 86, 365, 16);
		getContentPane().add(lblLabel);
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setConfirmResult(1);
				setVisible(false);
			}
		});
		btnOk.setBounds(152, 157, 119, 46);
		setLocationRelativeTo(null);
		getContentPane().add(btnOk);
	}
	
	public int getConfirmResult(){
		return confirmResult;
	}
	
	public void setConfirmResult(int confirmResult){
		this.confirmResult = confirmResult;
	}
}
