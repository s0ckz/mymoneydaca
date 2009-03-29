package mymoney;

import mymoney.exceptions.BancoDeDadosException;
import mymoney.exceptions.CampoExistenteException;
import mymoney.exceptions.CampoInexistenteException;
import mymoney.exceptions.CampoInvalidoException;

public interface EasyAcceptFacade {
	
	public void limparBancoDeDados()
		throws BancoDeDadosException;
	
	public void adicionarUsuario(int id, String login, String nome, String senha) 
		throws CampoInvalidoException, CampoExistenteException, BancoDeDadosException;

	public void modificarUsuario(int id, String novoLogin, String novoNome, String novaSenha) 
		throws 	CampoInvalidoException, CampoInexistenteException, 
				CampoExistenteException, BancoDeDadosException;
	
	public void removerUsuario(int id) 
		throws CampoInvalidoException, CampoInexistenteException, BancoDeDadosException;
	
	public String getUsuarioLogin(int id) throws BancoDeDadosException;
	
	public String getUsuarioNome(int id) throws BancoDeDadosException;
	
	public String getUsuarioSenha(int id) throws BancoDeDadosException;
	
}
