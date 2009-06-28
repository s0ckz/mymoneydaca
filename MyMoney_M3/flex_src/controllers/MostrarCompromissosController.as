package controllers
{
	import mx.rpc.AsyncToken;
	import mx.rpc.Responder;
	import mx.rpc.soap.mxml.WebService;
	
	import remote.MyMoneyWebService;

	public class MostrarCompromissosController
	{
		private static var instance:MostrarCompromissosController = null;
		
		private var remoteObject:WebService = null;
		

		public function MostrarCompromissosController()
		{
			this.remoteObject = MyMoneyWebService.getInstance();
		}
		
		public static function getInstance():MostrarCompromissosController {
			if (instance == null) {
				instance = new MostrarCompromissosController();
			}
			return instance;
		}

		public function getAllCompromissos(login:String, result:Function, fault:Function):void {
			var async:AsyncToken = remoteObject.getAllCommitments(login);
			async.addResponder(new Responder(result, fault));
		}

		public function removeCommitment(login:String, id:Number, result:Function, fault:Function):void {
			var async:AsyncToken = remoteObject.removeCommitment(login, id);
			async.addResponder(new Responder(result, fault));
		}
	}
}