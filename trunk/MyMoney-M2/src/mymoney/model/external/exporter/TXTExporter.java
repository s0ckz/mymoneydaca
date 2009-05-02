package mymoney.model.external.exporter;

public class TXTExporter extends Exporter {

	@Override
	protected String makeOperationString(long accId, String type, String way,
			double amount) {
		return accId + "\t" + type + "\t" + way + "\t" + amount+NEW_LINE;
	}

}
