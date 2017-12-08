/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janelas;

import DAO.GraficoDAO;
import DAO.TarefaDAO;
import entidade.Grafico;
import entidade.Tarefa;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
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
    int parametro = 0;
       public void windowClosed( WindowEvent e )
    {
        System.out.println( "Fechado!!!" );
    }
       
        public void windowClosing( WindowEvent e )
    {
        this.dispose();
    }

    private static final long serialVersionUID = 1L;

    public GraficoPizzaDemo() {
        super(null);
        this.setTitle("HelpEasy - Gráfico tarefas");
        this.parametro = parametro;
        JPanel jpanel = PanelDemostracao();
        jpanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(jpanel);
    }

    private static PieDataset criaDadosGrafico() {
        TarefaDAO tarefaDAO = new TarefaDAO();
        Tarefa tarefa = new Tarefa();

        // ArrayList<Tarefa> tarefas = tarefaDAO.listarParaGrafico(tarefa);
        DefaultPieDataset defaultpiedataset = new DefaultPieDataset();

        ArrayList<Grafico> graficos = new ArrayList<>();
        GraficoDAO graficoDAO = new GraficoDAO();
        graficos = graficoDAO.consultar();
        
        for (int i = 0; i < graficos.size(); i++) {
            defaultpiedataset.setValue(graficos.get(i).getDescricaoProjeto(), graficos.get(i).getQuantidade());
        }


        return defaultpiedataset;
    }

    private static JFreeChart criaGrafico(PieDataset piedataset) {
        JFreeChart jfreechart = ChartFactory.createPieChart(
                "Gráfico tarefas por projeto ", piedataset, true, true, false);
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

    public static void gerarGrafico() {
        GraficoPizzaDemo demo = new GraficoPizzaDemo();
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
