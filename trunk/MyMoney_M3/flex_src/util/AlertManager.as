package util
{
	import flash.external.ExternalInterface;
	
	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	
	public class AlertManager
	{
		
		private static var lastErrorMessage:String = null;
		
		public static function init():void {
			ExternalInterface.addCallback("getLastErrorMessage", getLastErrorMessage);
		}
		
		public static function getLastErrorMessage():String {
			return lastErrorMessage;
		}
		
		public static function showError(e: FaultEvent):void {
			lastErrorMessage = e.fault.faultString;
			Alert.show(e.toString());
		}
		
	}
}