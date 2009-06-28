package controllers
{
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	import mx.rpc.Responder;
	import mx.rpc.soap.mxml.WebService;
	
	import remote.MyMoneyWebService;

	public class GerarRelatorioController
	{
		private static var instance:GerarRelatorioController = null;
		
		private var remoteObject:WebService = null;
		

		public function GerarRelatorioController()
		{
			this.remoteObject = MyMoneyWebService.getInstance();
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