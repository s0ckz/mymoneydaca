package controllers
{
	import mx.rpc.AsyncToken;
	import mx.rpc.Responder;
	import mx.rpc.remoting.mxml.RemoteObject;

	public class DoLoginController
	{
		private static var instance:DoLoginController = null;
		
		private var remoteObject:RemoteObject = null;
		

		public function DoLoginController()
		{
			this.remoteObject = new RemoteObject("remoteFacade");
			this.remoteObject.showBusyCursor = false;
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