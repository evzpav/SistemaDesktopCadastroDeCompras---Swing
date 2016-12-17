package br.com.solvus;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.SwingUtilities;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import br.com.solvus.viewSwing.util.DateLabelFormatter;
import br.com.solvus.viewSwing.util.MainFrame;

public class App {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() { 
				try {
					new MainFrame();
					
										
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
	}
}
