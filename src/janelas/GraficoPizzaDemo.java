/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janelas;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import org.jfree.chart.*;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


public class GraficoPizzaDemo extends ApplicationFrame {
	/**
	 * 
	 */
    
    
    
    
	private static final long serialVersionUID = 1L;
	public GraficoPizzaDemo() {
		super(null);
		this.setTitle("Grafico de Pizza");
		JPanel jpanel = PanelDemostracao();
		jpanel.setPreferredSize(new Dimension(500, 270));
		setContentPane(jpanel);
	}
	private static PieDataset criaDadosGrafico() {
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		defaultpiedataset.setValue("Conteúdo 1", 43.23D);
		defaultpiedataset.setValue("Conteúdo 2", 10D);
		defaultpiedataset.setValue("Conteúdo 3", 27.5D);
		defaultpiedataset.setValue("Conteúdo 4", 17.5D);
		defaultpiedataset.setValue("Conteúdo 5", 11D);
		defaultpiedataset.setValue("Conteúdo 6", 19.39D);
		return defaultpiedataset;
	}
	private static JFreeChart criaGrafico(PieDataset piedataset) {
		JFreeChart jfreechart = ChartFactory.createPieChart(
				"Gráfico Pizza Demo ", piedataset, true, true, false);
		PiePlot plotagem = (PiePlot) jfreechart.getPlot();
		plotagem.setLabelGenerator(new StandardPieSectionLabelGenerator(
				"{0} ({2})"));//define porcentagem no gráfico
		plotagem.setLabelBackgroundPaint(new Color(220, 220, 220));
		return jfreechart;
	}
	public static JPanel PanelDemostracao() {
		JFreeChart jfreechart = criaGrafico(criaDadosGrafico());
		return new ChartPanel(jfreechart);
	}
        
        public static void gerarGrafico(){
            GraficoPizzaDemo demo = new GraficoPizzaDemo();
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);
        }
}