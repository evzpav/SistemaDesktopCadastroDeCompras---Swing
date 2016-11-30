package br.com.solvus;
import javax.swing.SwingUtilities;

import br.com.solvus.viewSwing.util.MainFrame;

public class App {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() { 
				new MainFrame();
				
			}
		});
	}
}
