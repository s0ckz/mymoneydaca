package MyMoneyComps
{
	import flash.events.Event;

	public class DoLoginEvent extends Event
	{
		
		public static const CADASTRO:String = "doLoginCadastro";
		
		public function DoLoginEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}