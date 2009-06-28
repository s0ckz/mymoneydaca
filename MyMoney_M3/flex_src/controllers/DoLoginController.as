package controllers
{
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.Responder;
	import mx.rpc.soap.mxml.WebService;
	
	import remote.MyMoneyWebService;

	public class DoLoginController
	{
		private static var instance:DoLoginController = null;
		
		private var remoteObject:WebService = null;
		

		public function DoLoginController()
		{
			this.remoteObject = MyMoneyWebService.getInstance();
		}
		
		public static function getInstance():DoLoginController {
			if (instance == null) {
				instance = new DoLoginController();
			}
			return instance;
		}

		public function doLogin(login:String, senha:String, result:Function, fault:Function):void {
			var async:AsyncToken = remoteObject.doLogin(login, senha);
			async.addResponder(new Responder(result, fault));
		}
	}
}