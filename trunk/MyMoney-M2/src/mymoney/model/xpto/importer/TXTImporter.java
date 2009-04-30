package mymoney.model.xpto.importer;

import java.util.regex.Pattern;

public class TXTImporter extends Importer {

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
