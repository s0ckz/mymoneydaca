package events
{
	import flash.events.Event;

	public class CadastrarUsuarioEvent extends Event
	{
		
		public static const CANCELAR:String = "cadastrarUsuarioCancelar";
		public static const CADASTRADO:String = "cadastrarUsuarioCadastrado";
		
		public function CadastrarUsuarioEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}