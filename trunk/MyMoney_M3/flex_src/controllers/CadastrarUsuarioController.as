package controllers
{
	import mx.rpc.AsyncToken;
	import mx.rpc.Responder;
	import mx.rpc.remoting.mxml.RemoteObject;

	public class CadastrarUsuarioController
	{
		private static var instance:CadastrarUsuarioController = null;
		
		private var remoteObject:RemoteObject = null;
		

		public function CadastrarUsuarioController()
		{
			this.remoteObject = new RemoteObject("remoteFacade");
			this.remoteObject.showBusyCursor = false;
		}
		
		public static function getInstance():CadastrarUsuarioController {
			if (instance == null) {
				instance = new CadastrarUsuarioController();
			}
			return instance;
		}
		
		public function registerUser(login:String, senha:String, nome:String, 
				gender:String, email:String, result:Function, fault: Function):void {
			var async:AsyncToken = remoteObject.registerUser(login, senha, nome, gender, email);
			async.addResponder(new Responder(result, fault));
		}
		
	}
}