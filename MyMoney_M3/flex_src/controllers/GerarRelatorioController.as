package controllers
{
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	import mx.rpc.Responder;
	import mx.rpc.remoting.mxml.RemoteObject;

	public class GerarRelatorioController
	{
		private static var instance:GerarRelatorioController = null;
		
		private var remoteObject:RemoteObject = null;
		

		public function GerarRelatorioController()
		{
			this.remoteObject = new RemoteObject("remoteFacade");
			this.remoteObject.showBusyCursor = false;
		}
		
		public static function getInstance():GerarRelatorioController {
			if (instance == null) {
				instance = new GerarRelatorioController();
			}
			return instance;
		}

		public function getAllAccountsIds(login:String, result:Function, fault:Function):void {
			var async:AsyncToken = remoteObject.getAllAccountsIds(login);
			async.addResponder(new Responder(result, fault));
		}

		public function getAccountsLabels(login:String, accIds:ArrayCollection, result:Function, fault:Function):void {
			var async:AsyncToken = remoteObject.getAccountsLabels(login, accIds);
			async.addResponder(new Responder(result, fault));
		}

		public function generateReport(login:String, begin:String , end:String , idAccount:Number , tipoOperacao:String , 
										result:Function, fault:Function):void{
			var async:AsyncToken = remoteObject.generateReport(login,begin,end,idAccount,tipoOperacao);
			async.addResponder(new Responder(result, fault));
		}

	}
}