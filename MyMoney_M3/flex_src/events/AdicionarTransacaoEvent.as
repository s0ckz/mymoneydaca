package events
{
	import flash.events.Event;

	public class AdicionarTransacaoEvent extends Event
	{
		
		public static const ADICIONAR:String = "adicionarTransacaoAdicionar";
		public static const ADICIONADA:String = "adicionarTransacaoAdicionada";
		public static const CANCELADA:String = "adicionarTransacaoCancelada";
		
		public function AdicionarTransacaoEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}