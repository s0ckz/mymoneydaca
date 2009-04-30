/**
 * 
 */
package mymoney.model.commitment;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import mymoney.model.exceptions.CommitmentException;
import mymoney.model.exceptions.DuplicatedLoginException;
import mymoney.model.exceptions.InvalidArgumentException;
import mymoney.model.exceptions.InvalidEmailException;
import mymoney.model.exceptions.MissingArgumentException;
import mymoney.model.user.UserManager;
import mymoney.model.user.UserManagerImpl;
import mymoney.model.util.ExceptionUtil;
import mymoney.model.util.HibernateUtil;

import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

/**
 * @author Danilo de S�
 * @author Leandro Jos�
 * @author Rodrigo Bruno
 * 
 */
public class CommitmentManagerImpl implements CommitmentManager {

	
	
	
	public CommitmentManagerImpl(){
		
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mymoney.model.commitment.CommitmentManager#addCommitment(java.lang.String
	 * , java.lang.String, java.lang.String, double, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public long addCommitment(String login, String label, String date,
			double amount, String type, String frequency)
			throws MissingArgumentException {
		ExceptionUtil.checkMissingArguments("login", login, "label", label,
				"date", date, "type", type, "frequency", frequency);
		Commitment newCommitment = new Commitment(login, amount, date,
				frequency, label, type);
		return (Long) HibernateUtil.save(newCommitment);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mymoney.model.commitment.CommitmentManager#getCommitmentAmount(java.lang
	 * .String, long)
	 */
	@Override
	public double getCommitmentAmount(String login, long id)
			throws CommitmentException {

		return getCommitment(login, id).getAmount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mymoney.model.commitment.CommitmentManager#getCommitmentDate(java.lang
	 * .String, long)
	 */
	@Override
	public String getCommitmentDate(String login, long id)
			throws CommitmentException {

		return getCommitment(login, id).getData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mymoney.model.commitment.CommitmentManager#getCommitmentFrequency(java
	 * .lang.String, long)
	 */
	@Override
	public String getCommitmentFrequency(String login, long id)
			throws CommitmentException {

		return getCommitment(login, id).getFrequency();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mymoney.model.commitment.CommitmentManager#getCommitmentLabel(java.lang
	 * .String, long)
	 */
	@Override
	public String getCommitmentLabel(String login, long id)
			throws CommitmentException {

		return getCommitment(login, id).getLabel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mymoney.model.commitment.CommitmentManager#getCommitmentType(java.lang
	 * .String, long)
	 */
	@Override
	public String getCommitmentType(String login, long id)
			throws CommitmentException {

		return getCommitment(login, id).getType();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mymoney.model.commitment.CommitmentManager#numberOfCommitments(java.lang
	 * .String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public long numberOfCommitments(String login) {
		Collection<SimpleExpression> expressions = Arrays.asList(Restrictions
				.eq("login", login));
		List<Commitment> list = (List<Commitment>) HibernateUtil
				.createQueryBasedOnExpressions(Commitment.class, expressions);

		return list.size();

	}

	@SuppressWarnings("unchecked")
	private Commitment getCommitment(String login, long commitmentCode)
			throws CommitmentException {
		Collection<SimpleExpression> expressions = Arrays.asList(Restrictions
				.eq("login", login), Restrictions.eq("code", commitmentCode));

		List<Commitment> list = (List<Commitment>) HibernateUtil
				.createQueryBasedOnExpressions(Commitment.class, expressions);

		if (list.isEmpty()) {
			throw new CommitmentException("None Commitment with the given id");
		}

		return list.get(0);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * mymoney.model.commitment.CommitmentManager#removeCommitment(java.lang
	 * .String, long)
	 */
	@Override
	public void removeCommitment(String login, long id)
			throws CommitmentException {
		if(numberOfCommitments(login) == 0){
			throw new CommitmentException("None Commitment registered");
		}
		Commitment commitment = getCommitment(login, id);
		HibernateUtil.delete(commitment);
	}

	
	public static void main(String[] args) throws MissingArgumentException, InvalidArgumentException, InvalidEmailException, DuplicatedLoginException {
		UserManager u = new UserManagerImpl();
		CommitmentManager c = new CommitmentManagerImpl();
//		u.register("login1", "nome", "M", "rodrigobls@balhblah.com");
//		c.addCommitment("login1","blah", "blah", 200, "blah","blah");
		System.out.println(c.numberOfCommitments("login1"));
		
	}
}
