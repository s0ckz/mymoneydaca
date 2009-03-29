package mymoney;

import mymoney.exceptions.BancoDeDadosException;
import mymoney.exceptions.CampoExistenteException;
import mymoney.exceptions.CampoInexistenteException;
import mymoney.exceptions.CampoInvalidoException;

public interface EasyAcceptFacade {
	
	public void limparBancoDeDados()
		throws BancoDeDadosException;
	
	// US 01
	
	public int adicionarUsuario(String login, String nome, String senha) 
		throws CampoInvalidoException, CampoExistenteException, BancoDeDadosException;

	public void modificarUsuario(int id, String novoLogin, String novoNome, String novaSenha) 
		throws 	CampoInvalidoException, CampoInexistenteException, 
				CampoExistenteException, BancoDeDadosException;
	
	public void removerUsuario(int id) 
		throws CampoInvalidoException, CampoInexistenteException, BancoDeDadosException;
	
	public String getUsuarioLogin(int id) 
		throws CampoInexistenteException, BancoDeDadosException;
	
	public String getUsuarioNome(int id) 
		throws CampoInexistenteException, BancoDeDadosException;
	
	public String getUsuarioSenha(int id) 
		throws CampoInexistenteException, BancoDeDadosException;
	
	public int getId(String login) 
		throws CampoInvalidoException, CampoInexistenteException, BancoDeDadosException;
	
	// US 02
	
    public int adicionarConta(int idDono, String descricao) 
    	throws CampoInvalidoException, CampoExistenteException, BancoDeDadosException;

    public void removerConta(int idConta) 
    	throws BancoDeDadosException;

    public void modificarConta(int idConta, String novaDescricao) 
    	throws 	CampoInvalidoException, CampoInexistenteException, 
    			CampoExistenteException, BancoDeDadosException;

    public int getContaDono(int idConta) 
    	throws CampoInexistenteException, BancoDeDadosException;

    public String getContaDescricao(int idConta) 
    	throws CampoInexistenteException, BancoDeDadosException;

    public int[] getContas(int idUsuario) 
    	throws CampoInexistenteException, BancoDeDadosException;
	
}
