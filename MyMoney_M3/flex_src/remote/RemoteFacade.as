package remote
{
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.Responder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.remoting.mxml.RemoteObject;
	
	public class RemoteFacade
	{
		private static var instance:RemoteFacade = null;
		
		private var remoteObject:RemoteObject = null;
		
		// infelizmente construtores nao podem ser privados.
		// tem como forcar a usar o outro, mas achei o codigo porco, entao
		// deixa assim.
		public function RemoteFacade() {
			this.remoteObject = new RemoteObject("remoteFacade");
			this.remoteObject.showBusyCursor = false;
		}
		
		public static function getInstance():RemoteFacade {
			if (instance == null) {
				instance = new RemoteFacade();
			}
			return instance;
		}
		
		public function teste(result: Function):void {
			var async:AsyncToken = remoteObject.teste();
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		private function defaultFaultHandler(e:FaultEvent):void{
			Alert.show(e.fault.toString(), e.fault.faultString);
		}

	}
}