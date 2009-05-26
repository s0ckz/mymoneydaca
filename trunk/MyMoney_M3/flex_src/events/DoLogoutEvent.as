package events
{
	import flash.events.Event;
	
	public class DoLogoutEvent extends Event
	{		
		public static const LOGOUT:String = "doLogoutLogout";
		
		public function DoLogoutEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}

	}
}