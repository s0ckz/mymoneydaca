package mymoney.flex;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import mymoney.model.MyMoney;
import mymoney.model.MyMoneyImpl;
import mymoney.model.account.AccountManager;
import mymoney.model.exceptions.AccountNotFoundException;
import mymoney.model.exceptions.BusinessException;
import mymoney.model.exceptions.CommitmentException;
import mymoney.model.exceptions.DuplicatedAccountException;
import mymoney.model.exceptions.DuplicatedLoginException;
import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.InvalidDateException;
import mymoney.model.exceptions.InvalidEmailException;
import mymoney.model.exceptions.LoginUnregisteredException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.exceptions.MisunderstandingFileContentException;
import mymoney.model.exceptions.PasswordMismatchException;
import mymoney.model.exceptions.PermissionDeniedException;
import mymoney.model.exceptions.UnknownOperationException;
import mymoney.model.exceptions.UserAlreadyLoggedException;
import mymoney.model.exceptions.UserNotLoggedException;
import mymoney.model.exceptions.UserUnregisteredException;
import mymoney.model.util.DateUtils;
import mymoney.model.util.HibernateUtil;

public class FlexFacade {
	
	private MyMoney myMoney; 
	
	public FlexFacade() {
		myMoney = new MyMoneyImpl();
	}

	// Utils - utilizado para testes do Selenium

	/**
	 * Limpa o banco de dados.
	 */
	public void cleanAll() {
		HibernateUtil.cleanAll();
	}

	/**
	 * Realiza cadastro de um novo usuario no sistema. <br>
	 * Para que o cadastro seja efetuado com sucesso, o parametro login deve ser
	 * uma cadeia de caracteres de tamanho maior ou igual a 4 contendo apenas
	 * caracteres que podem ser alfa numericos, underline, hifen ou ponto.
	 * 
	 * O e-mail deve ser vazio ou respeitar o seguinte padrao:
	 * $usuario@$dominio.$extensao, em que $usuario, $dominio devem ser uma
	 * cadeira de caracteres qualquer de tamanho maior ou igual a 1 e $extensao
	 * deve ser uma cadeia de letras minusculas.
	 * 
	 * @param login
	 *            O login do usuario.
	 * @param name
	 *            O nome do usuario.
	 * @param gender
	 *            O sexo do usuario.
	 * @param eMail
	 *            O e-mail do usuario.
	 * @throws MissingArgumentException
	 *             Se login e/ou nome forem nulos ou vazios.
	 * @throws InvalidArgumentException
	 *             Se login for invalido.
	 * @throws InvalidEmailException
	 *             Se o e-mail for invalido.
	 * @throws DuplicatedLoginException
	 *             Se ja houver um login identico cadastrado.
	 */
	public void registerUser(String login, String password, String name,
			String gender, String mail) throws MissingArgumentException, InvalidArgumentException, InvalidEmailException, DuplicatedLoginException {
		myMoney.registerUser(login, password, name, gender, mail);
	}
	
	/**
	 * Recupera o nome de um usuario.
	 * 
	 * @param login
	 *            O login do usuario.
	 * @return O nome do usuario.
	 * @throws UserUnregisteredException
	 *             Se o usuario nao tiver sido cadastrado no sistema.
	 */
	public String getUserName(String login) throws UserUnregisteredException {
		return myMoney.getUserName(login);
	}

	/**
	 * Recupera o sexo do usuario.
	 * 
	 * @param login
	 *            O login do usuario.
	 * @return O sexo do usuario.
	 * @throws UserUnregisteredException
	 *             Se o usuario nao tiver sido cadastrado no sistema.
	 */
	public String getUserGender(String login) throws UserUnregisteredException {
		return myMoney.getUserGender(login);
	}

	/**
	 * Recupera o e-mail do usuario.
	 * 
	 * @param login
	 *            O login do usuario.
	 * @return O e-mail do usuario.
	 * @throws UserUnregisteredException
	 *             Se o usuario nao tiver sido cadastrado no sistema.
	 */
	public String getUserEmail(String login) throws UserUnregisteredException {
		return myMoney.getUserEmail(login);
	}

	/**
	 * Remove um usuario do sistema.
	 * 
	 * @param login
	 *            O login do usuario a ser removido.
	 * @throws UserUnregisteredException
	 *             Se o usuario nao tiver sido cadastrado no sistema.
	 */
	public void removeUser(String login) throws UserUnregisteredException,
			LoginUnregisteredException {
		myMoney.removeUser(login);
	}

	/**
	 * Atualiza informacoes de um usuario no sistema. <br>
	 * O atributo login nao pode ser atualizado.
	 * 
	 * @param login
	 *            O login do usuario.
	 * @param name
	 *            O novo nome do usuario.
	 * @param gender
	 *            O novo sexo do usuario.
	 * @param eMail
	 *            O novo e-mail do usuario.
	 * @throws UserUnregisteredException
	 *             Se o usuario nao tiver sido cadastrado no sistema.
	 * @throws MissingArgumentException
	 *             Se login e/ou nome forem nulos ou vazios.
	 * @throws InvalidArgumentException
	 *             Se login for invalido.
	 * @throws InvalidEmailException
	 *             Se o e-mail for invalido.
	 */
	public void updateUser(String login, String name, String gender,
			String eMail) throws MissingArgumentException,
			InvalidEmailException, InvalidArgumentException,
			UserUnregisteredException {
		myMoney.updateUser(login, name, gender, eMail);
	}

	// US-02

	/**
	 * Executa login no sistema. Essa operacao permite que o usuario possa usar
	 * todas as funcionalidades do MyMoney, por exemplo: cadastrar uma conta,
	 * adicionar uma operacao financeira, etc.
	 * 
	 * @param login
	 *            O login do usuario.
	 * @param password
	 *            A senha do usuario.
	 * @throws PasswordMismatchException
	 *             Se a senha nao conferir com a cadastrada no sistema.
	 * @throws InvalidArgumentException
	 *             Se login e/ou senha forem invalidos.
	 * @throws LoginUnregisteredException
	 *             Se o login nao tiver sido cadastrado no sistema.
	 * @throws UserAlreadyLoggedException
	 *             Se o usuario ja estiver logado no sistema.
	 */
	public void doLogin(String login, String password)
			throws PasswordMismatchException, InvalidArgumentException,
			LoginUnregisteredException, UserAlreadyLoggedException {
		myMoney.doLogin(login, password);
	}

	/**
	 * Retorna <code>true</code> se o usuario estiver logado no sistema, caso
	 * contrario, retorna <code>false</code>.
	 * 
	 * @param login
	 *            O login do usuario.
	 * @return Se o usuario estiver logado.
	 * @throws LoginUnregisteredException
	 *             Se o login nao tiver sido cadastrado no sistema
	 */
	public boolean isLogged(String login) throws LoginUnregisteredException {
		return myMoney.isLogged(login);
	}

	// US-03

	/**
	 * Metodo para criar uma nova conta. Todos os campos sao obrigatorios e
	 * nenhum usuario pode ter duas contas com os mesmos atributos.
	 * 
	 * @param login
	 *            Login do usuario que eh dono dessa conta.
	 * @param label
	 *            Descricao dessa conta.
	 * @param agency
	 *            Agencia dessa conta.
	 * @param account
	 *            Numero dessa conta.
	 * @return Um numero maior ou igual a um que identifica essa conta.
	 * @throws MissingArgumentException
	 *             Caso algum dos quatro parametros que sao passados para esse
	 *             metodo estejam faltando.
	 * @throws DuplicatedAccountException
	 *             Caso jah exista uma conta com os mesmos <code>login</code>,
	 *             <code>label</code>, <code>agency</code> e
	 *             <code>account</code>.
	 */
	public long createAccount(String login, String label, String agency,
			String account) throws MissingArgumentException,
			DuplicatedAccountException {
		System.out.println("criando conta login = " + login + ", label = " + label + ", agency = " + agency + ", account = " + account);
		return myMoney.createAccount(login, label, agency, account);
	}

	/**
	 * Retorna uma colecao com todas as contas de um dado usuario.
	 * @param login O login do usuario.
	 */
	public Collection<Long> getAllAccountsIds(String login) {
		return myMoney.getAllAccountsIds(login);
	}

	public Collection<String> getAccountsLabels(String login, Collection<Number> accountsIds) throws PermissionDeniedException, AccountNotFoundException {
		Collection<String> labels = new LinkedList<String>();
		for (Number accId : accountsIds) {
			labels.add(getAccountLabel(login, accId.longValue()));
		}
		return labels;
	}
	
	/**
	 * Recupera o label de uma conta de um dado usuario.
	 * @param login O login do usuario dono da conta.
	 * @param accId O id da conta.
	 * @throws PermissionDeniedException Se o usuario nao for o dono da conta.
	 * @throws AccountNotFoundException Se a conta nao estiver cadastrada no sistema.
	 */
	public String getAccountLabel(String login, Long accId) throws PermissionDeniedException, AccountNotFoundException {
		return myMoney.getAccountLabel(login, accId);
	}

	// US-04

	/**
	 * Adiciona uma nova operacao a conta padrao.
	 * 
	 * @param login
	 *            Login do usuario.
	 * @param type
	 *            Tipo da operacao, podendo ser <code>debit</code> ou
	 *            <code>credit</code>.
	 * @param way
	 *            Modo de pagamento, podendo ser <code>cash</code>,
	 *            <code>creditcard</code> ou <code>check</code>.
	 * @param amount
	 *            Quantia de dinheiro que essa operacao movimentou. Deve ser um
	 *            valor positivo.
	 * @param date
	 *            Data da operacao no formato padrao: "dd/MM/yyyy HH:mm:ss".
	 * @see DateUtils
	 * @return Um identificador para essa operacao.
	 * @throws BusinessException
	 *             Caso a quantia seja menor ou igual a zero.
	 * @throws PermissionDeniedException
	 *             Caso o usuario tente adicionar a uma conta que nao o
	 *             pertence.
	 * @throws AccountNotFoundException
	 *             Caso a conta padrao nao exista.
	 * @throws InvalidDateException
	 *             Caso o formato da data seja invalido.
	 */
	public long addOperationIntoDefaultAccount(String login, String type,
			String way, double amount, String date) throws BusinessException,
			PermissionDeniedException, AccountNotFoundException,
			InvalidDateException {
		return myMoney.addOperationIntoDefaultAccount(login, type, way, amount,
				date);
	}

	/**
	 * Adiciona uma nova operacao a uma dada conta.
	 * 
	 * @param login
	 *            Login do usuario.
	 * @param accId
	 *            Identificador da conta.
	 * @param type
	 *            Tipo da operacao, podendo ser <code>debit</code> ou
	 *            <code>credit</code>.
	 * @param way
	 *            Modo de pagamento, podendo ser <code>cash</code>,
	 *            <code>creditcard</code> ou <code>check</code>.
	 * @param amount
	 *            Quantia de dinheiro que essa operacao movimentou. Deve ser um
	 *            valor positivo.
	 * @param date
	 *            Data da operacao no formato padrao: "dd/MM/yyyy HH:mm:ss".
	 * @return Um identificador para essa operacao.
	 * @throws BusinessException
	 *             Caso a quantia seja menor ou igual a zero.
	 * @throws PermissionDeniedException
	 *             Caso o usuario tente adicionar a uma conta que nao o
	 *             pertence.
	 * @throws AccountNotFoundException
	 *             Caso a conta padrao nao exista.
	 * @throws InvalidDateException
	 *             Caso o formato da data seja invalido.
	 */
	public long addOperation(String login, long accId, String type, String way,
			double amount, String date) throws BusinessException,
			PermissionDeniedException, AccountNotFoundException,
			InvalidDateException {
		return myMoney.addOperation(login, accId, type, way, amount, date);	
	}

	/**
	 * Metodo de acesso ao tipo da operacao.
	 * 
	 * @param opId
	 *            Identificador da conta.
	 * @return O tipo da operacao.
	 * @see AccountManager#addOperation(String, long, String, String, double,
	 *      Date)
	 * @see AccountManager#addOperationIntoDefaultAccount(String, String,
	 *      String, double, Date)
	 */
	public String getOperationType(long opId) {
		return myMoney.getOperationType(opId);
	}

	/**
	 * Metodo de acesso a data que de uma operacao.
	 * 
	 * @param opId
	 *            Identificador da operacao
	 * @return Uma data no formato <code>"dd/MM/yyyy HH:mm:ss"</code>.
	 */
	public String getOperationDate(long opId) {
		return myMoney.getOperationDate(opId);
	}

	/**
	 * Metodo de acesso ao modo de pagamento da operacao.
	 * 
	 * @param opId
	 *            Identificador da conta.
	 * @return O modo de pagamento da operacao.
	 * @see AccountManager#addOperation(String, long, String, String, double,
	 *      Date)
	 * @see AccountManager#addOperationIntoDefaultAccount(String, String,
	 *      String, double, Date)
	 */
	public String getOperationWay(long opId) {
		return myMoney.getoperationWay(opId);
	}

	/**
	 * Metodo de acesso a quantia que foi movimentada por uma operacao.
	 * 
	 * @param opId
	 *            Identificador da operacao.
	 * @return Um numero maior do que zero.
	 */
	public double getOperationAmount(long opId) {
		return myMoney.getOperationAmount(opId);
	}
	
	public Collection<OperationEntry> getAllOperations(String login, long accId) throws PermissionDeniedException, AccountNotFoundException {
		Collection<OperationEntry> entries = new LinkedList<OperationEntry>();
		Collection<Long> operationsIds = myMoney.getAllOperations(login, accId);
		for (long id : operationsIds) {
			OperationEntry entry = new OperationEntry();
			entry.setAccId(accId);
			entry.setId(id);
			entry.setAmount(getOperationAmount(id));
			entry.setType(getOperationType(id));
			entry.setWay(getOperationWay(id));
			entry.setDate(getOperationDate(id));
			entries.add(entry);
		}
		return entries;
	}

	/**
	 * Metodo de acesso ao numero total de operacoes que o usuario tem, ou seja,
	 * eh o somatorio de operacoes de todas as contas.
	 * 
	 * @param login
	 *            Login do usuario
	 * @return Numero maior ou igual a zero.
	 */
	public long getNumberOfOperations(String login) {
		return myMoney.getNumberOfOperations(login);
	}

	// US-05

	/**
	 * Retorna o valor total na conta padrao.
	 * 
	 * @param login
	 *            Login do usuario.
	 * @return Um valor maior ou igual a zero.
	 * @throws PermissionDeniedException
	 *             Caso a conta nao pertence ao login passado.
	 * @throws AccountNotFoundException
	 *             Caso a conta padrao nao exista.
	 */
	public double getDefAccOverallAmount(String login)
			throws PermissionDeniedException, AccountNotFoundException {
		return myMoney.getDefAccOverallAmount(login);
	}

	/**
	 * Retorna o valor total em uma dada conta.
	 * 
	 * @param login
	 *            Login do usuario.
	 * @param accId
	 *            Identificador da conta.
	 * @return Um valor maior ou igual a zero.
	 * @throws PermissionDeniedException
	 *             Caso a conta nao pertence ao login passado.
	 * @throws AccountNotFoundException
	 *             Caso a conta padrao nao exista.
	 */
	public double getAccOverallAmount(String login, long accId)
			throws PermissionDeniedException, AccountNotFoundException {
		return myMoney.getAccOverallAmount(login, accId);
	}

	// US-06

	/**
	 * Remove uma operacao.
	 * 
	 * @param login
	 *            Login do usuario.
	 * @param idOperation
	 *            Identificador da operacao a ser removida.
	 * @throws PermissionDeniedException
	 *             Caso a operacao nao pertenca a esse usuario.
	 * @throws UnknownOperationException
	 *             Caso a operacao nao exista.
	 */
	public void removeOperation(String login, long idOperation)
			throws PermissionDeniedException, UnknownOperationException {
		myMoney.removeOperation(login, idOperation);
	}

	// US-07

	/**
	 * Remove uma conta a partir do seu identificador e do login do usuario que
	 * e dono dessa conta.
	 * 
	 * @param login
	 *            Login do usuario.
	 * @param id
	 *            Identificador dessa conta.
	 * @throws PermissionDeniedException
	 *             Caso o usuario tente remover uma conta que nao pertence a
	 *             ele.
	 * @throws AccountNotFoundException
	 *             Caso a conta nao seja encontrada.
	 */
	public void removeAccount(String login, long id)
			throws PermissionDeniedException, AccountNotFoundException {
		myMoney.removeAccount(login, id);
	}

	// US-08

	/**
	 * Metodo que adiciona um compromisso
	 */
	public long addCommitment(String login, String label, String date,
			double amount, String type, String frequency)
			throws CommitmentException, MissingArgumentException {
		return myMoney.addCommitment(login, label, date, amount, type,
				frequency);
	}
	
	public Collection<CommitmentEntry> getAllCommitments(String login) throws CommitmentException {
		Collection<CommitmentEntry> commitments = new LinkedList<CommitmentEntry>();
		Collection<Long> ids = myMoney.getAllCommitmentsIds(login);
		for (long id : ids) {
			CommitmentEntry entry = new CommitmentEntry();
			entry.setId(id);
			entry.setDate(getCommitmentDate(login, id));
			entry.setFrequency(getCommitmentFrequency(login, id));
			entry.setLabel(getCommitmentLabel(login, id));
			entry.setType(getCommitmentType(login, id));
			entry.setValor(getCommitmentAmount(login, id));
			commitments.add(entry);
		}
		return commitments;
	}

	/**
	 * Metodo que retorna o label de um compromisso
	 * 
	 * @param login
	 *            O login do usuario
	 * @param id
	 *            o id do compromisso
	 * @return Uma cadeia de caracteres
	 * @throws CommitmentException
	 *             Caso haja algum evento inesperado com o compromisso
	 */
	public String getCommitmentLabel(String login, long id)
			throws CommitmentException {
		return myMoney.getCommitmentLabel(login, id);
	}

	/**
	 * Retorna a data do comprimisso
	 * 
	 * @param login
	 *            O login do usuario
	 * @param id
	 *            o id do compromisso
	 * @return date A data do compromisso
	 * @throws CommitmentException
	 *             Caso haja algum evento inesperado com o compromisso
	 */
	public String getCommitmentDate(String login, long id)
			throws CommitmentException {
		return myMoney.getCommitmentDate(login, id);
	}

	/**
	 * Retorna o montante do compromisso
	 * 
	 * @param login
	 *            O login do usuario
	 * @param id
	 *            o id do compromisso
	 * @return amount O montante do compromisso
	 * @throws CommitmentException
	 *             Caso haja algum evento inesperado com o compromisso
	 */
	public double getCommitmentAmount(String login, long id)
			throws CommitmentException {
		return myMoney.getCommitmentAmount(login, id);
	}

	/**
	 * Retorna o tipo do compromisso
	 * 
	 * @param login
	 *            O login do usuario
	 * @param id
	 *            O id do compromisso
	 * @return type O tipo do compromisso
	 * @throws CommitmentException
	 *             Caso haja algum evento inesperado com o compromisso
	 */
	public String getCommitmentType(String login, long id)
			throws CommitmentException {
		return myMoney.getCommitmentType(login, id);
	}

	/**
	 * Retorna a frequencia do compromisso
	 * 
	 * @param login
	 *            O login do usuario
	 * @param id
	 *            O id do compromisso
	 * @return frequency A frequencia do compromisso
	 * @throws CommitmentException
	 *             Caso haja algum evento inesperado com o compromisso
	 */
	public String getCommitmentFrequency(String login, long id)
			throws CommitmentException {
		return myMoney.getCommitmentFrequency(login, id);
	}

	/**
	 * Retorna o numero de compromissos de um usuario
	 * 
	 * @param login
	 *            O login do usuario
	 * @return inteiro que representa o numero de compromissos
	 * @throws CommitmentException
	 *             Caso haja algum evento inesperado com o compromisso
	 */
	public int numberOfCommitments(String login) throws CommitmentException {
		return myMoney.numberOfCommitments(login);
	}

	/**
	 * Metodo que remove o compromisso de um usuario
	 * 
	 * @param login
	 *            O login do usuario
	 * @param id
	 *            o id do compromisso
	 * @throws CommitmentException
	 *             Caso haja algum evento inesperado com o compromisso
	 */
	public void removeCommitment(String login, long id)
			throws CommitmentException {
		myMoney.removeCommitment(login, id);
	}

	// US - 09

	/**
	 * Importa as operacoes financeiras em formato .csv para a conta de um dado
	 * usuario. <br>
	 * <br>
	 * O arquivo .csv deve ter no seguinte padrao: <br>
	 * "$idDaConta","$tipo","$modo","$valor"
	 * 
	 * <br>
	 * <br>
	 * Exemplo de arquivo: <br>
	 * "1","credit","cash","300.0 <br>
	 * "1","debit","cash","2000.0
	 * 
	 * @param login
	 *            O login do usuario.
	 * @param fileContent
	 *            O conteudo do arquivo.
	 * @return Retorna um vetor com os identificadores das operacoes
	 *         adicionadas.
	 * 
	 * @throws BusinessException
	 *             Se alguma regra de negocio do MyMoney for violada.
	 * @throws PermissionDeniedException
	 *             Se o usuario nao tiver a permissao necessaria para executar
	 *             as operacoes financeiras.
	 * @throws AccountNotFoundException
	 *             Se algum conta especificada no arquivo nao existir no
	 *             sistema.
	 * @throws MisunderstandingFileContentException
	 *             Se houver erro no conteudo do arquivo.
	 */
	public String submitBankOperationsCSV(String login, String fileContent)
			throws BusinessException, PermissionDeniedException,
			AccountNotFoundException, MisunderstandingFileContentException {
		return Arrays.toString(myMoney.submitBankOperationsCSV(login,
				fileContent));
	}

	/**
	 * Importa as operacoes financeiras em formato .txt para a conta de um dado
	 * usuario. <br>
	 * <br>
	 * O arquivo .txt deve ter no seguinte padrao: <br>
	 * $idDaConta $tipo $modo $valor
	 * 
	 * <br>
	 * <br>
	 * Exemplo de arquivo: <br>
	 * 1 credit cash 300.0 <br>
	 * 1 debit cash 2000.0
	 * 
	 * @param login
	 *            O login do usuario.
	 * @param fileContent
	 *            O conteudo do arquivo.
	 * @return Retorna um vetor com os identificadores das operacoes
	 *         adicionadas.
	 * 
	 * @throws BusinessException
	 *             Se alguma regra de negocio do MyMoney for violada.
	 * @throws PermissionDeniedException
	 *             Se o usuario nao tiver a permissao necessaria para executar
	 *             as operacoes financeiras.
	 * @throws AccountNotFoundException
	 *             Se algum conta especificada no arquivo nao existir no
	 *             sistema.
	 * @throws MisunderstandingFileContentException
	 *             Se houver erro no conteudo do arquivo.
	 */
	public String submitBankOperationsTXT(String login, String fileContent)
			throws BusinessException, PermissionDeniedException,
			AccountNotFoundException, MisunderstandingFileContentException {
		return Arrays.toString(myMoney.submitBankOperationsTXT(login,
				fileContent));
	}

	/**
	 * Exporta as operacoes financeiras de uma conta de um usuario para um
	 * arquivo em formato .csv. <br>
	 * <br>
	 * O arquivo .csv tera o seguinte padrao: <br>
	 * "$idDaConta","$tipo","$modo","$valor"
	 * 
	 * <br>
	 * <br>
	 * Exemplo de arquivo: <br>
	 * "1","credit","cash","300.0 <br>
	 * "1","debit","cash","2000.0
	 * 
	 * @param login
	 *            O login do usuario.
	 * @param accId
	 *            O id da conta.
	 * @param pathToFile
	 *            O caminho para o arquivo onde sera gravado.
	 * @throws IOException
	 *             Se ocorrer algum problema de I/O.
	 * @throws PermissionDeniedException
	 *             Se o usuario nao tiver a permissao necessaria para executar
	 *             as operacoes financeiras.
	 * @throws AccountNotFoundException
	 *             Se algum conta especificada no arquivo nao existir no
	 *             sistema.
	 */
	public void exportBankOperationsCSV(String login, long accId,
			String pathToFile) throws IOException, PermissionDeniedException,
			AccountNotFoundException {
		myMoney.exportBankOperationsCSV(login, accId, pathToFile);
	}

	/**
	 * Exporta as operacoes financeiras de uma conta de um usuario para um
	 * arquivo em formato .txt. <br>
	 * <br>
	 * O arquivo .txt deve ter no seguinte padrao: <br>
	 * $idDaConta $tipo $modo $valor
	 * 
	 * <br>
	 * <br>
	 * Exemplo de arquivo: <br>
	 * 1 credit cash 300.0 <br>
	 * 1 debit cash 2000.0
	 * 
	 * @param login
	 *            O login do usuario.
	 * @param accId
	 *            O id da conta.
	 * @param pathToFile
	 *            O caminho para o arquivo onde sera gravado.
	 * @throws IOException
	 *             Se ocorrer algum problema de I/O.
	 * @throws PermissionDeniedException
	 *             Se o usuario nao tiver a permissao necessaria para executar
	 *             as operacoes financeiras.
	 * @throws AccountNotFoundException
	 *             Se algum conta especificada no arquivo nao existir no
	 *             sistema.
	 */
	public void exportBankOperationsTXT(String login, long accId,
			String pathToFile) throws IOException, PermissionDeniedException,
			AccountNotFoundException {
		myMoney.exportBankOperationsTXT(login, accId, pathToFile);
	}

	// US-10

	/**
	 * Metodo que gera um relatorio
	 * 
	 * @param login
	 *            O login do usuario
	 * @param begin
	 *            A data de inicio do relatorio
	 * @param end
	 *            A data de fim do relatorio
	 * @param idAccount
	 *            O id da conta
	 * @return long que representa o codigo do relatorio gerado
	 * @throws MissingArgumentException
	 *             Caso algum argumento nao tenha sido passado
	 * @throws InvalidDateException
	 * @throws AccountNotFoundException
	 * @throws PermissionDeniedException
	 */
	public	Collection<OperationEntry> generateReport(String login, String begin,
			String end, long idAccount, String typeOperation)
			throws MissingArgumentException, PermissionDeniedException,
			AccountNotFoundException, InvalidDateException {
		Collection<OperationEntry> entries = new LinkedList<OperationEntry>();
		Collection<Long> operationsIds = myMoney.generateReport(login, begin, end, idAccount,
				typeOperation);;
		for (long id : operationsIds) {
			OperationEntry entry = new OperationEntry();
			entry.setAccId(idAccount);
			entry.setId(id);
			entry.setAmount(getOperationAmount(id));
			entry.setType(getOperationType(id));
			entry.setWay(getOperationWay(id));
			entry.setDate(getOperationDate(id));
			entries.add(entry);
		}
		return entries;
		 
	}

	// US-11

	/**
	 * Executa logout no sistema. Apos executar este metodo, qualquer operacao
	 * que o usuario deseje realizar serah invalida ate que ele volte a fazer
	 * login no sistema.
	 * 
	 * @param login
	 *            O login do usuario.
	 * @param password
	 *            A senha do usuario.
	 * @throws InvalidArgumentException
	 *             Se login e/ou senha forem invalidos.
	 * @throws LoginUnregisteredException
	 *             Se o login nao tiver sido cadastrado no sistema.
	 * @throws PasswordMismatchException
	 *             Se a senha nao conferir com a cadastrada no sistema.
	 * @throws UserNotLoggedException
	 *             Se o usuario nao estiver logado no sistema.
	 */
	public void doLogoff(String login, String password)
			throws InvalidArgumentException, LoginUnregisteredException,
			PasswordMismatchException, UserNotLoggedException {
		myMoney.doLogout(login, password);
	}

}
