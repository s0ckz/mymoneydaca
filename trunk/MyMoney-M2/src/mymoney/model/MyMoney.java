package mymoney.model;

import java.io.IOException;
import java.util.Date;

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

public interface MyMoney {

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
	 * @param mail
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
	void registerUser(String login, String password, String name,
			String gender, String mail) throws MissingArgumentException,
			InvalidArgumentException, InvalidEmailException,
			DuplicatedLoginException;

	/**
	 * Remove um usuario do sistema.
	 * 
	 * @param login
	 *            O login do usuario a ser removido.
	 * @throws UserUnregisteredException
	 *             Se o usuario nao tiver sido cadastrado no sistema.
	 */
	void removeUser(String login) throws UserUnregisteredException,
			LoginUnregisteredException;

	/**
	 * Recupera o e-mail do usuario.
	 * 
	 * @param login
	 *            O login do usuario.
	 * @return O e-mail do usuario.
	 * @throws UserUnregisteredException
	 *             Se o usuario nao tiver sido cadastrado no sistema.
	 */
	String getUserEmail(String login) throws UserUnregisteredException;

	/**
	 * Recupera o sexo do usuario.
	 * 
	 * @param login
	 *            O login do usuario.
	 * @return O sexo do usuario.
	 * @throws UserUnregisteredException
	 *             Se o usuario nao tiver sido cadastrado no sistema.
	 */
	String getUserGender(String login) throws UserUnregisteredException;

	/**
	 * Recupera o nome de um usuario.
	 * 
	 * @param login
	 *            O login do usuario.
	 * @return O nome do usuario.
	 * @throws UserUnregisteredException
	 *             Se o usuario nao tiver sido cadastrado no sistema.
	 */
	String getUserName(String login) throws UserUnregisteredException;

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
	void doLogin(String login, String password)
			throws PasswordMismatchException, InvalidArgumentException,
			LoginUnregisteredException, UserAlreadyLoggedException;

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
	long createAccount(String login, String label, String agency, String account)
			throws MissingArgumentException, DuplicatedAccountException;

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
	boolean isLogged(String login) throws LoginUnregisteredException;

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
	String getOperationType(long opId);

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
	String getoperationWay(long opId);

	/**
	 * Metodo de acesso a quantia que foi movimentada por uma operacao.
	 * 
	 * @param opId
	 *            Identificador da operacao.
	 * @return Um numero maior do que zero.
	 */
	double getOperationAmount(long opId);

	/**
	 * Metodo de acesso ao numero total de operacoes que o usuario tem, ou seja,
	 * eh o somatorio de operacoes de todas as contas.
	 * 
	 * @param login
	 *            Login do usuario
	 * @return Numero maior ou igual a zero.
	 */
	long getNumberOfOperations(String login);

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
	long addOperationIntoDefaultAccount(String login, String type, String way,
			double amount, String date) throws BusinessException,
			PermissionDeniedException, AccountNotFoundException,
			InvalidDateException;

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
	long addOperation(String login, long accId, String type, String way,
			double amount, String date) throws BusinessException,
			PermissionDeniedException, AccountNotFoundException,
			InvalidDateException;

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
	double getDefAccOverallAmount(String login)
			throws PermissionDeniedException, AccountNotFoundException;

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
	double getAccOverallAmount(String login, long accId)
			throws PermissionDeniedException, AccountNotFoundException;

	/**
	 * Remove uma operacao.
	 * 
	 * @param login
	 *            Login do usuario.
	 * @param opId
	 *            Identificador da operacao a ser removida.
	 * @throws PermissionDeniedException
	 *             Caso a operacao nao pertenca a esse usuario.
	 * @throws UnknownOperationException
	 *             Caso a operacao nao exista.
	 */
	void removeOperation(String login, long opId)
			throws PermissionDeniedException, UnknownOperationException;

	/**
	 * Remove uma conta a partir do seu identificador e do login do usuario que
	 * e dono dessa conta.
	 * 
	 * @param login
	 *            Login do usuario.
	 * @param accId
	 *            Identificador dessa conta.
	 * @throws PermissionDeniedException
	 *             Caso o usuario tente remover uma conta que nao pertence a
	 *             ele.
	 * @throws AccountNotFoundException
	 *             Caso a conta nao seja encontrada.
	 */
	void removeAccount(String login, long accId)
			throws PermissionDeniedException, AccountNotFoundException;

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
	void doLogout(String login, String password)
			throws InvalidArgumentException, LoginUnregisteredException,
			PasswordMismatchException, UserNotLoggedException;

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
	 * @param mail
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
	void updateUser(String login, String name, String gender, String mail)
			throws MissingArgumentException, InvalidEmailException,
			InvalidArgumentException, UserUnregisteredException;

	/**
	 * Metodo que adiciona um compromisso
	 * 
	 * @param login
	 *            o login do dono do compromisso
	 * @param label
	 *            o label do compromisso
	 * @param date
	 *            a data do compromisso
	 * @param amount
	 *            o montante do compromisso
	 * @param type
	 *            o tipo do compromisso
	 * @param frequency
	 *            a frequencia do compromisso
	 * @return code the code of the commitment added
	 * @throws MissingArgumentException
	 *             Caso algum argumento nao seja passado
	 **/
	long addCommitment(String login, String label, String date, double amount,
			String type, String frequency) throws MissingArgumentException;

	/**
	 * Metodo que retorna o label de um compromisso
	 * 
	 * @param login
	 *            o login do dono do compromisso
	 * @param id
	 *            a id do compromisso
	 * @return label o label do compromisso
	 * @throws CommitmentException
	 *             Caso haja algum evento inesperado com o compromisso.
	 */
	String getCommitmentLabel(String login, long id) throws CommitmentException;

	/**
	 * Metodo que retorna a data de um compromisso
	 * 
	 * @param login
	 *            O login do dono do compromisso
	 * @param id
	 *            o id do compromisso
	 * @return date a data do compromisso
	 * @throws CommitmentException
	 *             Caso haja algum evento inesperado com o compromisso.
	 */
	String getCommitmentDate(String login, long id) throws CommitmentException;

	/**
	 * Retorna o montante do compromisso
	 * 
	 * @param login
	 *            o login do usuario dono do compromisso
	 * @param id
	 *            a id do compromisso
	 * @return amount o montante do comprimisso
	 * @throws CommitmentException
	 *             Caso haja algum evento inesperado com o compromisso.
	 */
	double getCommitmentAmount(String login, long id)
			throws CommitmentException;

	/**
	 * Retorna o tipo do compromisso
	 * 
	 * @param login
	 *            o login do dono do compromisso
	 * @param id
	 *            o id do compromisso
	 * 
	 * @return type o tipo do compromisso
	 * @throws CommitmentException
	 *             Caso haja algum evento inesperado com o compromisso.
	 */
	String getCommitmentType(String login, long id) throws CommitmentException;

	/**
	 * Retorna a frequencia do comprimisso
	 * 
	 * @param login
	 *            o login do dono do compromisso
	 * @param id
	 *            o id do compromisso
	 * @return frequency a frequencia do compromisso
	 * @throws CommitmentException
	 *             Caso haja algum evento inesperado com o compromisso.
	 */
	String getCommitmentFrequency(String login, long id)
			throws CommitmentException;

	/**
	 * Retorna o numero de compromissos de um usuario
	 * 
	 * @param login
	 *            o login do usuario
	 * 
	 * @return number Um inteiro que representa o numero de compromissos do
	 *         usuario
	 */
	int numberOfCommitments(String login);

	/**
	 * Metodo que remove um comentario
	 * 
	 * @param login
	 *            o login do usuario a que pertence o compromisso
	 * @param id
	 *            o id do compromisso
	 * 
	 * @throws CommitmentException
	 *             Caso haja algum evento inesperado com o compromisso.
	 */
	void removeCommitment(String login, long id) throws CommitmentException;

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
	long[] submitBankOperationsCSV(String login, String fileContent)
			throws BusinessException, PermissionDeniedException,
			AccountNotFoundException, MisunderstandingFileContentException;

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
	long[] submitBankOperationsTXT(String login, String fileContent)
			throws MisunderstandingFileContentException, BusinessException,
			PermissionDeniedException, AccountNotFoundException;

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
	void exportBankOperationsCSV(String login, long accId, String pathToFile)
			throws IOException, PermissionDeniedException,
			AccountNotFoundException;

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
	void exportBankOperationsTXT(String login, long accId, String pathToFile)
			throws IOException, PermissionDeniedException,
			AccountNotFoundException;

	/**
	 * Metodo de acesso a data que de uma operacao.
	 * 
	 * @param opId
	 *            Identificador da operacao
	 * @return Uma data no formato <code>"dd/MM/yyyy HH:mm:ss"</code>.
	 */
	String getOperationDate(long opId);

	/**
	 * @param login
	 * @param begin
	 * @param end
	 * @param idAccount
	 * @param typeOperation
	 * @return
	 * @throws MissingArgumentException
	 * @throws InvalidDateException 
	 * @throws AccountNotFoundException 
	 * @throws PermissionDeniedException 
	 */
	long[] generateReport(String login, String begin, String end, long idAccount,
			String typeOperation) throws MissingArgumentException, PermissionDeniedException, AccountNotFoundException, InvalidDateException;

}
