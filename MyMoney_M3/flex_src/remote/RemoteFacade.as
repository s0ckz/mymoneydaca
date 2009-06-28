package remote
{
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.Responder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.soap.mxml.WebService;
	
	public class RemoteFacade
	{
		private static var instance:RemoteFacade = null;
		
		private var remoteObject:WebService = null;
		
		// infelizmente construtores nao podem ser privados.
		// tem como forcar a usar o outro, mas achei o codigo porco, entao
		// deixa assim.
		public function RemoteFacade() {
			this.remoteObject = MyMoneyWebService.getInstance();
		}
		
		public static function getInstance():RemoteFacade {
			if (instance == null) {
				instance = new RemoteFacade();
			}
			return instance;
		}
		
		public function cleanAll():void {
			var async:AsyncToken = remoteObject.cleanAll();
		}
		
		public function teste(result: Function):void {
			var async:AsyncToken = remoteObject.teste();
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		

		public function getAccountLabel(login:String, accId:Number, result:Function, fault:Function):void {
			var async:AsyncToken = remoteObject.getAccountLabel(login, accId);
			async.addResponder(new Responder(result, fault));
		}
		
		private function defaultFaultHandler(e:FaultEvent):void{
			Alert.show(e.fault.toString(), e.fault.faultString);
		}
		
		public function exportBankOperationsTXT(login:String,accId:Number,pathToFile:String,result:Function, fault:Function):void
		
		{
			var async : AsyncToken = remoteObject.exportBankOperationsTXT(login, accId,pathToFile)
			async.addResponder(new Responder(result, fault));
		}

		public function exportBankOperationsCSV(login:String,accId:Number,pathToFile:String,result:Function, fault:Function):void
		
		{
			var async : AsyncToken = remoteObject.exportBankOperationsCSV(login, accId,pathToFile)
			async.addResponder(new Responder(result, fault));
		}

	}
}