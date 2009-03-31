package mymoney;

import mymoney.exceptions.BancoDeDadosException;
import mymoney.exceptions.CampoExistenteException;
import mymoney.exceptions.CampoInexistenteException;
import mymoney.exceptions.CampoInvalidoException;
import mymoney.exceptions.TransacaoInvalidaException;
import mymoney.exceptions.TransacaoJahConcluidaException;
import mymoney.exceptions.TransacaoNaoConcluidaException;

public class EasyAcceptFacadeImpl implements EasyAcceptFacade {

	@Override
	public int addTransacao(int idConta, String tipo, String descricao,
			double valor, String data) throws CampoInexistenteException,
			CampoInvalidoException, BancoDeDadosException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addTransacaoFutura(int idConta, String tipo, String descricao,
			double valor, String dataVencimento)
			throws CampoInexistenteException, CampoInvalidoException,
			BancoDeDadosException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int adicionarConta(int idDono, String descricao)
			throws CampoExistenteException, BancoDeDadosException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int adicionarUsuario(String login, String nome, String senha)
			throws CampoInvalidoException, CampoExistenteException,
			BancoDeDadosException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void concluirTransacaoFutura(int id, String dataConclusao,
			double novoValor) throws TransacaoInvalidaException,
			TransacaoJahConcluidaException, CampoInexistenteException,
			CampoInvalidoException, BancoDeDadosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exportarTransacoes(int idConta, String transacoes,
			String arquivoCSV) throws CampoInexistenteException,
			CampoInvalidoException, BancoDeDadosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int geraNotificacaoDespesas(int idConta, String dataInicio,
			String dataFim) throws CampoInvalidoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int geraNotificacaoReceitas(int idConta, String dataInicio,
			String dataFim) throws CampoInvalidoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int geraNotificacoesTodasContasUsuario(int idUsuario,
			String dataInicio, String dataFim) throws CampoInvalidoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int gerarRelatorioDespesas(int idConta, String dataInicio,
			String dataFim) throws CampoInvalidoException,
			BancoDeDadosException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int gerarRelatorioReceitas(int idConta, String dataInicio,
			String dataFim) throws CampoInvalidoException,
			BancoDeDadosException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int gerarRelatorioTodasAsContas(int idUsuario, String dataInicio,
			String dataFim) throws CampoInvalidoException,
			BancoDeDadosException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int gerarRelatorioTransacoes(int idConta, String dataInicio,
			String dataFim) throws CampoInvalidoException,
			BancoDeDadosException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getContaDescricao(int idConta)
			throws CampoInexistenteException, BancoDeDadosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getContaDono(int idConta) throws CampoInexistenteException,
			BancoDeDadosException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getContaNumeroTransacoes(int idConta)
			throws CampoInexistenteException, BancoDeDadosException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getContaSaldo(int idConta) throws CampoInexistenteException,
			BancoDeDadosException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getContas(int idUsuario) throws CampoInexistenteException,
			BancoDeDadosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getId(String login) throws CampoInvalidoException,
			CampoInexistenteException, BancoDeDadosException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getRelatorioDespesas(int id, String dataInicio, String dataFim)
			throws CampoInexistenteException, BancoDeDadosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRelatorioReceitas(int id, String dataInicio, String dataFim)
			throws CampoInexistenteException, BancoDeDadosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRelatorioTransacoes(int id, String dataInicio,
			String dataFim) throws CampoInvalidoException,
			BancoDeDadosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTransacaoData(int id) throws CampoInexistenteException,
			BancoDeDadosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTransacaoDataConclusao(int id)
			throws CampoInexistenteException, BancoDeDadosException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getTransacaoDescricao(int id)
			throws CampoInexistenteException, BancoDeDadosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTransacaoTipo(int id) throws CampoInexistenteException,
			BancoDeDadosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getTransacaoValor(int id) throws CampoInexistenteException,
			BancoDeDadosException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTransacaoValorConclusao(int id)
			throws CampoInexistenteException, BancoDeDadosException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getTransacoes(int idConta) throws CampoInexistenteException,
			BancoDeDadosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTransacoesEntre(int idConta, String dataInicio,
			String dataFim) throws CampoInexistenteException,
			CampoInvalidoException, BancoDeDadosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsuarioLogin(int id) throws CampoInexistenteException,
			BancoDeDadosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsuarioNome(int id) throws CampoInexistenteException,
			BancoDeDadosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsuarioSenha(int id) throws CampoInexistenteException,
			BancoDeDadosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String importarTransacoes(int idConta, String arquivoCSV)
			throws CampoInexistenteException, CampoInvalidoException,
			BancoDeDadosException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isTransacaoConcluida(int id)
			throws CampoInexistenteException, BancoDeDadosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTransacaoFutura(int id) throws CampoInexistenteException,
			BancoDeDadosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void limparBancoDeDados() throws BancoDeDadosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarConclusaoTransacao(int id, String novaDataConclusao,
			double novoValor) throws TransacaoInvalidaException,
			TransacaoNaoConcluidaException, CampoInexistenteException,
			CampoInvalidoException, BancoDeDadosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarConta(int idConta, String novaDescricao)
			throws CampoInexistenteException, CampoExistenteException,
			BancoDeDadosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarTransacao(int id, String novoTipo,
			String novaDescricao, double novoValor, String novaData)
			throws CampoInexistenteException, CampoInvalidoException,
			BancoDeDadosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modificarUsuario(int id, String novoLogin, String novoNome,
			String novaSenha) throws CampoInvalidoException,
			CampoInexistenteException, CampoExistenteException,
			BancoDeDadosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removerConta(int idConta) throws CampoInexistenteException,
			BancoDeDadosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int removerRelatorio(int idRelatorio)
			throws CampoInexistenteException, BancoDeDadosException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removerTransacao(int id) throws CampoInexistenteException,
			BancoDeDadosException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removerUsuario(int id) throws CampoInexistenteException,
			BancoDeDadosException {
		// TODO Auto-generated method stub
		
	}

}
