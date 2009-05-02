package mymoney.model.external.exporter;

/**
 * Prove exportacao das operacoes financeiras de um conta para um 
 * arquivo no formato CSV.
 */
public class CSVExporter extends Exporter {

	
	/**
	 * Construtor default.
	 */
	public CSVExporter() {
		super();
	}

	@Override
	protected String operationToString(long accId, String type, String way,
			double amount) {
		return "\""+accId+"\",\""+type+"\",\""+way+"\",\""+amount+NEW_LINE;
	}

}
