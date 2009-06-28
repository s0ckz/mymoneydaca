package controllers
{
	import mx.rpc.AsyncToken;
	import mx.rpc.Responder;
	import mx.rpc.soap.mxml.WebService;
	
	import remote.MyMoneyWebService;

	public class CadastrarContaController
	{
		private static var instance:CadastrarContaController = null;
		
		private var remoteObject:WebService = null;
		

		public function CadastrarContaController()
		{
			this.remoteObject = MyMoneyWebService.getInstance();
		}
		
		public static function getInstance():CadastrarContaController {
			if (instance == null) {
				instance = new CadastrarContaController();
			}
			return instance;
		}

		public function createAccount(login:String, label:String, agencia:String, conta:String, result:Function, fault:Function):void {
			var async:AsyncToken = remoteObject.createAccount(login, label, agencia, conta);
			async.addResponder(new Responder(result, fault));
		}
		
	}
}