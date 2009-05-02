package mymoney.model.external.exporter;

public class CSVExporter extends Exporter {

	@Override
	protected String makeOperationString(long accId, String type, String way,
			double amount) {
		return "\""+accId+"\",\""+type+"\",\""+way+"\",\""+amount+NEW_LINE;
	}

}
