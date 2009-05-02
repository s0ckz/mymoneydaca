package mymoney.model.external.importer;

import java.util.regex.Pattern;

public class CSVImporter extends Importer {
	
	protected static final String TOKEN_CSV = "\"([^\"]*)\"";
	
	@Override
	protected Pattern makeOperationPattern() {
		String regex = TOKEN_CSV;
		for (int i = 1; i < NUM_GROUPS; i++) {
			regex += "," + TOKEN_CSV;
		}
		Pattern p = Pattern.compile(regex);
		return p;
	}

}
