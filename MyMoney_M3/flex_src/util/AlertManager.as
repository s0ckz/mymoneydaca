package util
{
	import flash.display.Sprite;
	import flash.external.ExternalInterface;
	
	import mx.controls.Alert;
	import mx.messaging.messages.ErrorMessage;
	import mx.rpc.events.FaultEvent;
	
	public class AlertManager
	{
		
		private static var lastMessage:String = null;
		
		public static function init():void {
			ExternalInterface.addCallback("getLastMessage", getLastMessage);
		}
		
		public static function getLastMessage():String {
			return lastMessage;
		}
		
		public static function showError(e: FaultEvent):void {
			lastMessage = (e.message as ErrorMessage).rootCause.message;
			showAlert(lastMessage);
		}
		
		public static function showString(text: String):void {
			lastMessage = text;
			showAlert(text);
		}
		
		private static function showAlert(text:String):void {
			Alert.show(text);
		}
		
	}
}