package pt.tooyummytogo.estrategias;

import java.time.LocalDateTime;
import java.util.Optional;

import pt.tooyummytogo.facade.handlers.EncomendarHandler;

public interface RedefinirStrategy {
	
	public void redefineValor(EncomendarHandler lch, Optional<Integer> raio, Optional<LocalDateTime> agora, Optional<LocalDateTime> depois);
	
}
