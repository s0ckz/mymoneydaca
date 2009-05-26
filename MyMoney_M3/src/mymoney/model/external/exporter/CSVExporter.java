package mymoney.model.external.exporter;

import java.util.Date;

import mymoney.model.util.DateUtils;

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
			double amount, Date date) {
		return "\""+accId+"\",\""+type+"\",\""+way+"\",\""+amount+"\",\""+DateUtils.toString(date)+NEW_LINE;
	}

}
