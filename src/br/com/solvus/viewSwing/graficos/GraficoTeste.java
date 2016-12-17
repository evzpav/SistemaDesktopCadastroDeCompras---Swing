package br.com.solvus.viewSwing.graficos;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;

public class GraficoTeste extends JPanel {
	
	public GraficoTeste(){
		
		
		this.setLayout( new BorderLayout());
		
		PieChartDemo1 demoPie = new PieChartDemo1("teste demo pie");
		
		this.add(demoPie, BorderLayout.CENTER);
		this.validate();
	}
	
}
