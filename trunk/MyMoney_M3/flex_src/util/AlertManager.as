package util
{
	import flash.external.ExternalInterface;
	
	import mx.controls.Alert;
	import mx.messaging.messages.ErrorMessage;
	import mx.rpc.events.FaultEvent;
	
	public class AlertManager
	{
		
		private static var lastErrorMessage:ErrorMessage = null;
		
		public static function init():void {
			ExternalInterface.addCallback("getLastErrorMessage", getLastErrorMessage);
		}
		
		public static function getLastErrorMessage():String {
			return lastErrorMessage.rootCause.message;
		}
		
		public static function showError(e: FaultEvent):void {
			lastErrorMessage = e.message as ErrorMessage;
			Alert.show(e.toString());
		}
		
	}
}