package mymoney;

import java.util.ArrayList;
import java.util.List;

import easyaccept.EasyAcceptFacade;

public class EasyAcceptRunner {

	public static void main(String[] args) {
		List<String> files = new ArrayList<String>();
		files.add("acceptanceTests/tests/US01_TA01-CriacaoUsuarios.txt");
		files.add("acceptanceTests/tests/US01_TA02-ModificacaoUsuarios.txt");
		files.add("acceptanceTests/tests/US01_TA03-RemocaoUsuarios.txt");
		files.add("acceptanceTests/tests/US01_TA04-CriacaoUsuariosErro.txt");
		files.add("acceptanceTests/tests/US01_TA05-ModificacaoUsuariosErro.txt");
		files.add("acceptanceTests/tests/US02_TA01-Contas.txt");
		files.add("acceptanceTests/tests/US02_TA02-ContasErro.txt");
		files.add("acceptanceTests/tests/US03_TA01-Transacoes.txt");
		files.add("acceptanceTests/tests/US03_TA02-TransacoesErro.txt");
		files.add("acceptanceTests/tests/US04_TA01-ContasAPagarEReceber.txt");
		files.add("acceptanceTests/tests/US04_TA02-ContasAPagarEReceberErro.txt");
		files.add("acceptanceTests/tests/US05_TA01-ExportacaoImportacao.txt");
		files.add("acceptanceTests/tests/US05_TA02-ExportacaoImportacaoErro.txt");
		files.add("acceptanceTests/tests/US06_TA01-GeracaoRelatorios.txt");
		files.add("acceptanceTests/tests/US06_TA02-GeracaoRelatoriosErro.txt");
		files.add("acceptanceTests/tests/US07_TA01-Notificacao.txt");
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(
				new EasyAcceptFacadeImpl(), files);
		eaFacade.executeTests();
		System.out.println(eaFacade.getCompleteResults());
	}

}
