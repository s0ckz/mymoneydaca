package events
{
	import flash.events.Event;
	
	public class GerarExtratoEvent extends Event
	{
		
		public static const GERAREVENTO:String = "gerarEvento";
		public static const EVENTOGERADO:String = "eventoGerado";	
		public static const CANCELAR:String = "eventoCancelar"; 
		
		public function GerarExtratoEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type,bubbles,cancelable); 
		}

	}
}