package events
{
	import flash.events.Event;
	
	public class GerarRelatorioEvent extends Event
	{
		
		public static const GERARRELATORIOCONTA:String = "gerarRelatorioConta";
		public static const CANCELAR:String = "gerarRelatorioCancelar";
		
		public function GerarRelatorioEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type,bubbles,cancelable); 
		}

	}
}