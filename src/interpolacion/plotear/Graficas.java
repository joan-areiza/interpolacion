/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpolacion.plotear;

import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author desarrollo
 */
public class Graficas extends JFrame {
    JFrame VentanaGrafica = new JFrame();//
    JFreeChart grafica;
    XYSeriesCollection datos=new XYSeriesCollection();
    String titulo;
    String tx;
    String ty;
    public final static int LINEAL=1;    
    public final static int POLAR=2;
    public final static int DISPERSION=3;
    public final static int AREA=4;    
    public final static int LOGARITMICA=5;
    public final static int SERIETIEMPO=6;
    public final static int PASO=7;
    public final static int PASOAREA=8;
    
    public Graficas(int tipo, String titulo, String tx, String ty)
    {
        
        
        this.titulo=titulo;
        this.tx=tx;
        this.ty=ty;
        tipoGrafica(tipo);
        
    }
    
    public void tipoGrafica(int tipo)
    {
        switch (tipo)
        {
            case LINEAL:
                
                grafica=ChartFactory.createXYLineChart(titulo, tx, ty, datos, PlotOrientation.VERTICAL, true, true, true);
                break;
            case POLAR:
                grafica=ChartFactory.createPolarChart(titulo, datos, true, true, true);
                break;
            case DISPERSION:
                grafica=ChartFactory.createScatterPlot(titulo, tx, ty, datos, PlotOrientation.VERTICAL, true, true, true);
                break;
            case AREA:
                grafica=ChartFactory.createXYAreaChart(titulo, tx, ty, datos, PlotOrientation.VERTICAL, true, true, true);
                break;
            case LOGARITMICA:
                grafica=ChartFactory.createXYLineChart(titulo, tx, ty, datos, PlotOrientation.VERTICAL, true, true, true);
                //Se cambia a escala logarítmica.
                XYPlot ejes=grafica.getXYPlot();
                NumberAxis rango=new LogarithmicAxis(ty);
                ejes.setRangeAxis(rango);
                break;
            case SERIETIEMPO:
                grafica=ChartFactory.createTimeSeriesChart(titulo, tx, ty, datos, true, true, true);
                break;
            case PASO:
                grafica=ChartFactory.createXYStepAreaChart(titulo, tx, ty, datos, PlotOrientation.VERTICAL, true, true, true);
                break;
            case PASOAREA:
                grafica=ChartFactory.createXYStepChart(titulo, tx, ty, datos, PlotOrientation.VERTICAL, true, true, true);
                break;    
        }
    }
        
        //Método para agregar las gráficas.
        public void agregarGrafica(String id, double[] x, double[] y)
        {
            
            XYSeries s=new XYSeries (id);
            int n=x.length;
            for (int i=0;i<n;i++)
            {
                s.add(x[i],y[i]);
            }
            datos.addSeries(s);//Se le asignan los datos que se requieren graficar
            
        
        }
        
        public JPanel obtienePanel()
        {
            //Se crea retorna el panel para poderlo asignar a un o
            //para poder mostrar la grafica que estamos realizando.
            return new ChartPanel(grafica);
        }
        
   
    
}
