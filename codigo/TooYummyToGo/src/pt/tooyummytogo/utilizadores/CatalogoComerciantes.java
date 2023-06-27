package pt.tooyummytogo.utilizadores;
//v4
import java.awt.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
//v3
import java.util.HashMap;
import java.util.Map;

import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;

public class CatalogoComerciantes {
	private Map<String, Comerciante> comerciantes = new HashMap<>();
	private Map<String, ComercianteInfo> comerciantesInfo = new HashMap<>();
	
	
	private static CatalogoComerciantes instance;
	
	public static CatalogoComerciantes getInstance() {
		if(instance == null) {
			instance = new CatalogoComerciantes();
		}
		return instance;
	}
	
	private CatalogoComerciantes() {
		//Não faz nada
	}
	
	public void clear() {
		comerciantes = new HashMap<>();
	}
	
	public void registarComerciante(String u, String pw, PosicaoCoordenadas p) {
		Comerciante comerciante = new Comerciante(u,pw,p);
		ComercianteInfo comercianteInfo = new ComercianteInfo(u,p);
		comerciantes.put(u, comerciante);
		comerciantesInfo.put(u, comercianteInfo);
	}
	public Comerciante getComerciante(String u) {
		return comerciantes.get(u);
	}
	
	public ComercianteInfo getComercianteInfo(String u) {
		return comerciantesInfo.get(u);
	}
	
	/**
    * 
    * @param coordinate
    * @param raio
    * @return
    */
   public ArrayList<ComercianteInfo> getComerciantesDisponiveis(PosicaoCoordenadas coordinate, int raio, LocalDateTime inicio, LocalDateTime fim) {
       
       ArrayList<ComercianteInfo> lcd = new ArrayList<>();
       for(ComercianteInfo c : comerciantesInfo.values()) {
         
           boolean b = (c.estaDisponivel((c.getLocalizacao().distanciaEmMetros(coordinate)), raio));
           if (b) {
        	   boolean d = false; 
        	   for (int i=0; i<c.getListaProdutos().size(); i++) {
        	    	
        	    	if ((c.getListaProdutos().get(i).getInicio().compareTo(inicio)<=0) && (c.getListaProdutos().get(i).getFim().compareTo(fim)>=0)){
        	    		d = true;
        	    	}
        	    }
        	   if(d) {
        		   lcd.add(c);
        	   }
           }
       }
       return lcd;
   }
   
   /**
    * Devolve lista de comerciantesInfo
    * 
    * @return lista de comerciantesInfo
    */
   public ArrayList<ComercianteInfo> listaComercianteInfo() {
       ArrayList<ComercianteInfo> comerciantes = new ArrayList<ComercianteInfo>(this.comerciantesInfo.values());
       return comerciantes;
   }
}
