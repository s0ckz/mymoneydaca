package mymoney.model.external.exporter;

import java.util.Date;

import mymoney.model.util.DateUtils;

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
			double amount, Date date) {
		return accId + "\t" + type + "\t" + way + "\t" + amount+ "\t" + DateUtils.toString(date) + NEW_LINE;
	}

}
