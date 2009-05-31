package events
{
	import flash.events.Event;

	public class MostrarCompromissosEvent extends Event
	{
		
		public static const EXIBIR:String = "mostrarCompromissosExibir";
		public static const CANCELAR:String = "mostrarCompromissosCancelar";
		
		public function MostrarCompromissosEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}