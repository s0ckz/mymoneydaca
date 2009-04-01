package mymoney;

import mymoney.exceptions.BancoDeDadosException;
import mymoney.exceptions.CampoExistenteException;
import mymoney.exceptions.CampoInexistenteException;
import mymoney.exceptions.CampoInvalidoException;
import mymoney.exceptions.TransacaoInvalidaException;
import mymoney.exceptions.TransacaoJahConcluidaException;
import mymoney.exceptions.TransacaoNaoConcluidaException;

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
		throws CampoInexistenteException, BancoDeDadosException;
	
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
    	throws CampoExistenteException, BancoDeDadosException;

    public void modificarConta(int idConta, String novaDescricao) 
    	throws 	CampoInexistenteException, CampoExistenteException, BancoDeDadosException;

    public void removerConta(int idConta) 
    	throws CampoInexistenteException, BancoDeDadosException;

    public int getContaDono(int idConta) 
    	throws CampoInexistenteException, BancoDeDadosException;

    public String getContaDescricao(int idConta) 
    	throws CampoInexistenteException, BancoDeDadosException;
    
    public double getContaSaldo(int idConta)
		throws CampoInexistenteException, BancoDeDadosException;

    // FORMATO DE SAIDA: [1, 2, 3, ...]
    public String getContas(int idUsuario) 
    	throws CampoInexistenteException, BancoDeDadosException;
	
	public int getContaNumeroTransacoes(int idConta)
		throws CampoInexistenteException, BancoDeDadosException;
    
    // US 03 - 04
    
    public int addTransacao(int idConta, String tipo, String descricao, double valor, String data)
    	throws CampoInexistenteException, CampoInvalidoException, BancoDeDadosException;
    
    public int addTransacaoFutura(int idConta, String tipo, String descricao, double valor, String dataVencimento)
    	throws CampoInexistenteException, CampoInvalidoException, BancoDeDadosException;
    
    public void modificarTransacao(int id, String novoTipo, String novaDescricao, double novoValor, 
    		String novaData) 
    	throws CampoInexistenteException, CampoInvalidoException, BancoDeDadosException;
	
	public void removerTransacao(int id) 
		throws CampoInexistenteException, BancoDeDadosException;
	
	public String getTransacaoTipo(int id)
		throws CampoInexistenteException, BancoDeDadosException;
	
	public String getTransacaoDescricao(int id)
		throws CampoInexistenteException, BancoDeDadosException;
	
	public double getTransacaoValor(int id)
		throws CampoInexistenteException, BancoDeDadosException;
	
	public String getTransacaoData(int id)
		throws CampoInexistenteException, BancoDeDadosException;

    // FORMATO DE SAIDA: [1, 2, 3, ...]
	public String getTransacoes(int idConta)
		throws CampoInexistenteException, BancoDeDadosException;

    // FORMATO DE SAIDA: [1, 2, 3, ...]
	public String getTransacoesEntre(int idConta, String dataInicio, String dataFim)
		throws CampoInexistenteException, CampoInvalidoException, BancoDeDadosException;

	// TRANSACOES FUTURAS = CONTAS A PAGAR / RECEBER
    
    public void modificarConclusaoTransacao(int id, String novaDataConclusao, double novoValor)
		throws 	TransacaoInvalidaException, TransacaoNaoConcluidaException, 
				CampoInexistenteException, CampoInvalidoException, BancoDeDadosException;
    
    public void concluirTransacaoFutura(int id, String dataConclusao, double novoValor)
		throws 	TransacaoInvalidaException, TransacaoJahConcluidaException, CampoInexistenteException, 
				CampoInvalidoException, BancoDeDadosException;
	
	public boolean isTransacaoFutura(int id)
		throws CampoInexistenteException, BancoDeDadosException;
	
	public boolean isTransacaoConcluida(int id)
		throws CampoInexistenteException, BancoDeDadosException;
	
	public double getTransacaoValorConclusao(int id)
		throws CampoInexistenteException, BancoDeDadosException;
	
	public double getTransacaoDataConclusao(int id)
		throws CampoInexistenteException, BancoDeDadosException;
	
    // US-05

    // FORMATO DE SAIDA: [1, 2, 3, ...]
    public String importarTransacoes(int idConta, String arquivoCSV) 
    	throws CampoInexistenteException, CampoInvalidoException, BancoDeDadosException;

    // FORMATO DE ENTRADA PARA O PARAMETRO TRANSACOES: [1, 2, 3, ...]
    public void exportarTransacoes(int idConta, String transacoes, String arquivoCSV) 
    	throws CampoInexistenteException, CampoInvalidoException, BancoDeDadosException;

    //US-06 
    
    public int gerarRelatorioTransacoes(int idConta , String dataInicio, String dataFim)
    	throws CampoInvalidoException, BancoDeDadosException;
    
    public int gerarRelatorioReceitas(int idConta , String dataInicio, String dataFim) 
    	throws CampoInvalidoException, BancoDeDadosException ;
    
    public int gerarRelatorioDespesas(int idConta , String dataInicio, String dataFim)
    	throws CampoInvalidoException, BancoDeDadosException;

    // FORMATO DE SAIDA: [1, 2, 3, ...]
    public int gerarRelatorioTodasAsContas(int idUsuario , String dataInicio, String dataFim)
    	throws CampoInvalidoException, BancoDeDadosException;

    // FORMATO DE SAIDA: [1, 2, 3, ...]
    public String getRelatorioTransacoes(int id, String dataInicio, String dataFim)
    	throws CampoInvalidoException, BancoDeDadosException;

    // FORMATO DE SAIDA: [1, 2, 3, ...]
    public String getRelatorioDespesas(int id, String dataInicio, String dataFim)	
    	throws CampoInexistenteException , BancoDeDadosException;

    // FORMATO DE SAIDA: [1, 2, 3, ...]
    public String getRelatorioReceitas(int id, String dataInicio, String dataFim)	
    throws CampoInexistenteException , BancoDeDadosException;
    
    public int removerRelatorio(int idRelatorio)	
    	throws CampoInexistenteException , BancoDeDadosException;
    
    //US-07
    public String getNotificacaoReceitas(int idConta, String dataInicio, String dataFim )
    	throws CampoInvalidoException;
    
    public String getNotificacaoDespesas(int idConta, String dataInicio, String dataFim)
    	throws CampoInvalidoException;
        
    public String getNotificacoesPorUsuario(int idUsuario, String dataInicio, String dataFim)
    	throws CampoInvalidoException;
    
    public void alterarDataSistema(String novaData)throws CampoInvalidoException;
    
}
