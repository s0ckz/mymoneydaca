package events
{
	import flash.events.Event;

	public class CadastrarContaEvent extends Event
	{
		
		public static const CANCELAR:String = "cadastrarContaCancelar";
		public static const CADASTRAR:String = "cadastrarContaCadastrar";
		public static const CADASTRADA:String = "cadastrarContaCadastrada";
		
		public function CadastrarContaEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}