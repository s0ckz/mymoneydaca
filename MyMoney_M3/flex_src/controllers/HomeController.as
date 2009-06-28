package controllers
{
	import mx.collections.ArrayCollection;
	import mx.rpc.AsyncToken;
	import mx.rpc.Responder;
	import mx.rpc.soap.mxml.WebService;
	
	import remote.MyMoneyWebService;

	public class HomeController
	{
		private static var instance:HomeController = null;
		
		private var remoteObject:WebService = null;
		

		public function HomeController()
		{
			this.remoteObject = MyMoneyWebService.getInstance();
		}
		
		public static function getInstance():HomeController {
			if (instance == null) {
				instance = new HomeController();
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

		public function getAllOperations(login:String, accId:Number, result:Function, fault:Function):void {
			var async:AsyncToken = remoteObject.getAllOperations(login, accId);
			async.addResponder(new Responder(result, fault));
		}

		public function doLogout(login:String, senha:String, result:Function, fault:Function):void {
			var async:AsyncToken = remoteObject.doLogoff(login, senha);
			async.addResponder(new Responder(result, fault));
		}		

		public function removeAccount(login:String, accId:Number, result:Function, fault:Function):void {
			var async:AsyncToken = remoteObject.removeAccount(login,accId);
			async.addResponder(new Responder(result, fault));
		}

		public function removeOperation(login:String, opId:Number, result:Function, fault:Function):void {
			var async:AsyncToken = remoteObject.removeOperation(login,opId);
			async.addResponder(new Responder(result, fault));
		}

	}
}