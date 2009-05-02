package mymoney.model.external.importer;

import java.util.regex.Pattern;

/**
 * Prove exportacao das operacoes financeiras de um conta para um 
 * arquivo no formato TXT.
 */
public class TXTImporter extends Importer {

	protected static final String TOKEN_TXT = "(\"[^\"]*\"|[^\\s]*)";
	
	/**
	 * Construtor default.
	 */
	public TXTImporter() {
		super();
	}


	@Override
	protected Pattern makeOperationPattern() {
		String regex = TOKEN_TXT;
		for (int i = 1; i < NUM_GROUPS; i++) {
			regex += "\\s+" + TOKEN_TXT;
		}
		Pattern p = Pattern.compile(regex);
		return p;
	}

}
