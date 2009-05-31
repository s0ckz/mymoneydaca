package events
{
	import flash.events.Event;

	public class AdicionarCompromissoEvent extends Event
	{
		
		public static const ADICIONAR:String = "adicionarCompromissoAdicionar";
		public static const ADICIONADO:String = "adicionarCompromissoAdicionado";
		public static const CANCELADO:String = "adicionarCompromissoCancelado";
		
		public function AdicionarCompromissoEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}