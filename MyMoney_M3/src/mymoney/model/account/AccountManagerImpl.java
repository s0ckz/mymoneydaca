package mymoney.model.account;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import mymoney.model.exceptions.AccountNotFoundException;
import mymoney.model.exceptions.BusinessException;
import mymoney.model.exceptions.DuplicatedAccountException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.exceptions.PermissionDeniedException;
import mymoney.model.exceptions.UnknownOperationException;
import mymoney.model.util.ExceptionUtil;
import mymoney.model.util.HibernateUtil;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

/**
 * Implementacao do gerenciador de contas e operacoes.
 */
public class AccountManagerImpl implements AccountManager {

	private static final String DEFAULT_ACCOUNT = "default";

	public long addOperation(String login, long accId, String type, String way,
			double amount, Date date) throws BusinessException, PermissionDeniedException, AccountNotFoundException {
		if (amount < 0.0)
			throw new BusinessException("negative amount");
		else if (amount == 0.0)
			throw new BusinessException("zero amount");
		Account account = getAccount(login, accId);
		Operation operation = new Operation(account, type, way, amount, date);
		return (Long) HibernateUtil.save(operation);
	}

	public long addOperationIntoDefaultAccount(String login, String type,
			String way, double amount, Date date) throws BusinessException, PermissionDeniedException, AccountNotFoundException {
		long defaultAccount = getDefaultAccount(login).getId();
		return addOperation(login, defaultAccount, type, way, amount, date);
	}

	public long createAccount(String login, String label, String agency,
			String account) throws MissingArgumentException, DuplicatedAccountException {
		ExceptionUtil.checkMissingArguments("login", login, "label", label, "agency", 
				agency, "account", account);
		if (getAccount(login, label, agency, account) != null)
			throw new DuplicatedAccountException();
		Account accountToBeCreated = new Account(login, label, agency, account);
		return (Long) HibernateUtil.save(accountToBeCreated);
	}

	public String getOperationType(long opId) {
		Operation operation = getOperation(opId);
		if (operation == null)
			return "";
		return operation.getType();
	}

	public String getOperationWay(long opId) {
		Operation operation = getOperation(opId);
		if (operation == null)
			return "";
		return operation.getWay();
	}

	@SuppressWarnings("unchecked")
	public long getNumberOfOperations(String login) {
		Collection<SimpleExpression> expressions = 
			Arrays.asList(Restrictions.eq("login", login));
		
		List<Account> accounts = (List<Account>) HibernateUtil.createQueryBasedOnExpressions(Account.class, expressions);
		
		long numberOfOperations = 0;
		for (Account account : accounts) {
			numberOfOperations += account.getOperations().size();
		}
		return numberOfOperations;
	}

	public double getOperationAmount(long opId) {
		Operation operation = getOperation(opId);
		if (operation == null)
			return 0.0;
		return operation.getAmount();
	}

	public Date getOperationDate(long opId) {
		Operation operation = getOperation(opId);
		if (operation == null)
			return new Date();
		return operation.getDate();
	}
	
	public double getAccOverallAmount(String login, long accId) throws PermissionDeniedException, AccountNotFoundException {
		Account account = getAccount(login, accId);
		double amount = 0.0;
		for (Operation op : account.getOperations()) {
			amount += op.correctAmount();
		}
		return amount;
	}

	public double getDefAccOverallAmount(String login) throws PermissionDeniedException, AccountNotFoundException {
		long defaultAccount = getDefaultAccount(login).getId();
		return getAccOverallAmount(login, defaultAccount);
	}

	public void removeAccount(String login, long id) throws PermissionDeniedException, AccountNotFoundException {
		Account account = getAccount(login, id);
		HibernateUtil.delete(account);
	}

	public void removeOperation(String login, long opId) throws UnknownOperationException, PermissionDeniedException {
		Operation operation = getOperation(login, opId);
		HibernateUtil.delete(operation);
	}

	/**
	 * Metodo de acesso a uma certa conta a partir de seus identificadores.
	 * @param login Login do usuario.
	 * @param label Descricao da conta.
	 * @param agency Agencia da conta.
	 * @param account Numero da conta.
	 * @return <code>null</code> caso a conta nao exista.
	 */
	@SuppressWarnings("unchecked")
	private Account getAccount(String login, String label, String agency,
			String account) {
		Collection<SimpleExpression> expressions = 
			Arrays.asList(Restrictions.eq("login", login), 
					Restrictions.eq("label", label), 
					Restrictions.eq("agency", agency), 
					Restrictions.eq("account", account));
		
		List<Account> list = (List<Account>) HibernateUtil.createQueryBasedOnExpressions(Account.class, expressions);
		
		if (list.isEmpty())
			return null;
		else
			return list.get(0);
	}

	/**
	 * Metodo de acesso a conta padrao de um dado usuario.
	 * @param login Login do usuario.
	 * @return A conta padrao do usuario.
	 */
	public Account getDefaultAccount(String login) {
		Account account = getAccount(login, DEFAULT_ACCOUNT, DEFAULT_ACCOUNT, DEFAULT_ACCOUNT);
		if (account == null) {
			try {
				createAccount(login, DEFAULT_ACCOUNT, DEFAULT_ACCOUNT, DEFAULT_ACCOUNT);
			} catch (MissingArgumentException e) {
			} catch (DuplicatedAccountException e) {
			}
			return getAccount(login, DEFAULT_ACCOUNT, DEFAULT_ACCOUNT, DEFAULT_ACCOUNT);
		}
		return account;
	}

	/**
	 * Metodo de acesso a uma conta.
	 * @param login Login do usuario.
	 * @param accId Identificador da conta.
	 * @return A conta do usuario.
	 * @throws PermissionDeniedException Caso a conta nao pertenca a esse usuario.
	 * @throws AccountNotFoundException Caso a conta nao exista.
	 */
	public Account getAccount(String login, long accId) throws PermissionDeniedException, AccountNotFoundException {
		Account account = (Account) HibernateUtil.load(Account.class, accId);
		if (account == null)
			throw new AccountNotFoundException();
		if (!account.getLogin().equals(login))
			throw new PermissionDeniedException("you do not own this account");
		return account;
	}

	/**
	 * Metodo de acesso a uma operacao.
	 * @param opId Identificador da operacao.
	 * @return <code>null</code> caso a operacao nao exista.
	 */
	private Operation getOperation(long opId) {
		Operation operation = (Operation) HibernateUtil.load(Operation.class, opId);
		return operation;
	}
	
	/**
	 * Metodo de cesso a uma operacao.
	 * @param login Login do usuario.
	 * @param opId Identificador da operacao.
	 * @return Uma operacao.
	 * @throws PermissionDeniedException Caso a operacao nao pertenca ao usuario.
	 * @throws UnknownOperationException Caso a operacao nao exista.
	 */
	private Operation getOperation(String login, long opId) throws PermissionDeniedException, UnknownOperationException {
		Operation operation = getOperation(opId);
		if (operation == null)
			throw new UnknownOperationException();
		if (!operation.getAccount().getLogin().equals(login))
			throw new PermissionDeniedException("you do not own this operation");
		return operation;
	}
	
	public Collection<Long> getAllOperations(String login, long accId) throws PermissionDeniedException, AccountNotFoundException {
		Account account = getAccount(login, accId);
		Collection<Operation> operations = account.getOperations();
		Collection<Long> operationsToReturn = new LinkedList<Long>();
		for (Operation o : operations) {
			operationsToReturn.add(o.getId());
		}
		return operationsToReturn;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Long> getAllAccountsIds(String login) {
		Collection<SimpleExpression> expressions = 
			Arrays.asList(Restrictions.eq("login", login)); 
		List<Account> list = (List<Account>) HibernateUtil.createQueryBasedOnExpressions(Account.class, expressions);
		Collection<Long> accountsIds = new LinkedList<Long>();
		for (Account a : list) {
			accountsIds.add(a.getId());
		}
		return accountsIds;
	}

	@Override
	public String getAccountLabel(String login, Long accId) throws PermissionDeniedException, AccountNotFoundException {
		Account account = getAccount(login, accId);
		return account.getLabel();
	}
	
//	@SuppressWarnings("unchecked")
//	public Collection<Operation> getAllOperations(String login, long accId) {
//		Account account = getAccount(login, accId);
//		Collection<SimpleExpression> expressions = 
//			Arrays.asList(Restrictions.eq("account_fk", account)); 
//		List<Operation> list = (List<Operation>) HibernateUtil.createQueryBasedOnExpressions(Operation.class, expressions);
//		return list;
//	}

}
