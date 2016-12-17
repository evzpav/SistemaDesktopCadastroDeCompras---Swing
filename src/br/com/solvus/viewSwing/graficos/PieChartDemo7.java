package br.com.solvus.viewSwing.graficos;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;

public class PieChartDemo7 extends ApplicationFrame {

	public PieChartDemo7(String title) {

		super(title);
		JPanel panel = new JPanel(new GridLayout(2, 2));
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Section 1", 23.3);
		dataset.setValue("Section 2", 56.5);
		dataset.setValue("Section 3", 43.3);
		dataset.setValue("Section 4", 11.1);

		JFreeChart chart1 = ChartFactory.createPieChart("Chart 1", dataset, false, false, false);
	
		 panel.add(new ChartPanel(chart1));
	

		panel.setPreferredSize(new Dimension(800, 600));
		setContentPane(panel);

	}
}
