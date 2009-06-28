package controllers
{
	import mx.rpc.AsyncToken;
	import mx.rpc.Responder;
	import mx.rpc.soap.mxml.WebService;
	
	import remote.MyMoneyWebService;

	public class CadastrarUsuarioController
	{
		private static var instance:CadastrarUsuarioController = null;
		
		private var remoteObject:WebService = null;
		

		public function CadastrarUsuarioController()
		{
			this.remoteObject = MyMoneyWebService.getInstance();
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