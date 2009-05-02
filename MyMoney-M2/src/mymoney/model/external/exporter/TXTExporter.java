package mymoney.model.external.exporter;

/**
 * Prove exportacao das operacoes financeiras de um conta para um 
 * arquivo no formato TXT.
 */
public class TXTExporter extends Exporter {

	/**
	 * Construtor default.
	 */
	public TXTExporter() {
		super();
	}

	@Override
	protected String operationToString(long accId, String type, String way,
			double amount) {
		return accId + "\t" + type + "\t" + way + "\t" + amount+NEW_LINE;
	}

}
