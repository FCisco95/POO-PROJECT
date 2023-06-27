package pt.tooyummytogo.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import pt.tooyummytogo.Sessao;
import pt.tooyummytogo.facade.TooYummyToGo;
import pt.tooyummytogo.facade.dto.PosicaoCoordenadas;
import pt.tooyummytogo.facade.handlers.AdicionarTipoDeProdutoHandler;
import pt.tooyummytogo.facade.handlers.ColocarProdutoHandler;
import pt.tooyummytogo.facade.handlers.RegistarUtilizadorHandler;

class TestLogin {

TooYummyToGo app;
	
	PosicaoCoordenadas p;
	
	@Before
	public void setUp() throws Exception {
		app = new TooYummyToGo();
		p = new PosicaoCoordenadas(1,1);
	}

	@After
	public void tearDown() throws Exception {
		app = null;
		p = null;
	}

	@Test
	public void test() {
		app.getRegistarUtilizadorHandler().registarAdmin("admin", "pw");
		app.getRegistarUtilizadorHandler().registarUtilizador("cliente", "pw");
		app.getRegistarComercianteHandler().registarComerciante("comerciante", "pw", p);
		
		assertTrue(app.autenticar("admin", "pw").isPresent());
		assertTrue(app.autenticar("cliente", "pw").isPresent());
		assertTrue(app.autenticar("comerciante", "pw").isPresent());
		assertFalse(app.autenticar("fake", "account").isPresent());
		System.out.println("Success");
	}
	
	@Test
	/*Verifica se um utilizador consegue aceder ao adicionarTipoDeProdutoHandler*/
	public void test1() {
		RegistarUtilizadorHandler regHandler = app.getRegistarUtilizadorHandler();
		regHandler.registarUtilizador("user", "password");
		Optional<Sessao> talvezSessao = app.autenticar("user", "password");
		talvezSessao.ifPresent( (Sessao s) -> {
		AdicionarTipoDeProdutoHandler atp = s.adicionarTipoDeProdutoHandler();
		assertNull(atp);
		});
	}
	
	@Test
	/*Verifica se um utilizador consegue aceder ao colocarProdutoHandler*/
	public void test2() {
		RegistarUtilizadorHandler regHandler = app.getRegistarUtilizadorHandler();
		regHandler.registarUtilizador("user", "password");
		Optional<Sessao> talvezSessao = app.autenticar("user", "password");
		talvezSessao.ifPresent( (Sessao s) -> {
		ColocarProdutoHandler cp = s.getColocarProdutoHandler();
		assertNull(cp);
		});
	}

}
