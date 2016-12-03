package br.com.solvus;
import java.sql.SQLException;

import javax.swing.SwingUtilities;

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
