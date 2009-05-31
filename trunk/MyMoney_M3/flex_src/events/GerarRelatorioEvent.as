package events
{
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	
	public class GerarRelatorioEvent extends Event
	{
		
		public static const GERARRELATORIO:String = "gerarRelatorio";
		public static const CANCELAR:String = "gerarRelatorioCancelar"; 
		
		public function GerarRelatorioEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type,bubbles,cancelable); 
		}

	}
}