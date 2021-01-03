package com.elearning.beans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.PieChartModel;

import com.elearning.database.CoursDAO;

@ManagedBean
@ViewScoped
public class PieChartBean {
    private PieChartModel pieModel;
    
    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieChartModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }
    
    public void lister() {
    	CoursDAO dao;
    	ArrayList<Bean> liste ;
    	
    	try {
    		dao = new CoursDAO();
			liste=dao.lister();
			graficar(liste);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
    
    private void graficar(List<Bean> liste) {
    	pieModel = new PieChartModel();
    	for (Bean bean: liste) {
    		pieModel.set(bean.getSujet_id(),bean.getNombre());
    	}
    	pieModel.setTitle("Pourcentage des cours par matiere");
    	pieModel.setFill(false);
    	pieModel.setLegendPosition("e");
    	pieModel.setShowDataLabels(true);
    	pieModel.setDiameter(120);
    	
    }
    
}