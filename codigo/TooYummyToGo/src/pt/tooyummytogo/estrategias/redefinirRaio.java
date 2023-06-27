package pt.tooyummytogo.estrategias;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import pt.tooyummytogo.facade.dto.ComercianteInfo;
import pt.tooyummytogo.facade.handlers.EncomendarHandler;

public class redefinirRaio implements RedefinirStrategy {

	@Override
	public void redefineValor(EncomendarHandler lch, Optional<Integer> raio,
			Optional<LocalDateTime> agora, Optional<LocalDateTime> depois) {
		
		List<ComercianteInfo> cs = lch.redefineRaio(raio.get());
		for (ComercianteInfo i : cs) {
			System.out.println(i);
		}
	}

}
