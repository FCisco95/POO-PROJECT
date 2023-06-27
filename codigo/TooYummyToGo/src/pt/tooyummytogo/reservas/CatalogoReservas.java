package pt.tooyummytogo.reservas;

import java.util.ArrayList;
import java.util.List;

import pt.tooyummytogo.exeptions.DoesNotExistException;

public class CatalogoReservas {
	
	private List<Reserva> reservas = new ArrayList<>();
	
	private static CatalogoReservas instance;
	
	public static CatalogoReservas getInstance() {
		if(instance == null) {
			instance = new CatalogoReservas();
		}
		return instance;
	}
	
	private CatalogoReservas() {
		//DOES NOTHING
	}

	public void registarReserva(Reserva reservaCorrente) {
		reservas.add(reservaCorrente);
		
	}

	public void clear() {
		reservas = new ArrayList<>();
	}


	public Reserva getReserva(String codigo) throws DoesNotExistException {
		for(Reserva r: reservas) {
			if(r.getCodigo().equals(codigo) )
				return r;
		}
		throw new DoesNotExistException("Não existe reserva com codigo:" + codigo);
	}
	
	public String tamanho() {
		return String.valueOf(reservas.size());
	}
}
