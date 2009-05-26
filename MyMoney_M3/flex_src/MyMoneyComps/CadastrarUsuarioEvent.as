package MyMoneyComps
{
	import flash.events.Event;

	public class CadastrarUsuarioEvent extends Event
	{
		
		public static const CANCELAR:String = "cadastrarUsuarioCancelar";
		
		public function CadastrarUsuarioEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}