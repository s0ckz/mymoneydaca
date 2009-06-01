package events
{
	public class GerarExtratoEvent
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