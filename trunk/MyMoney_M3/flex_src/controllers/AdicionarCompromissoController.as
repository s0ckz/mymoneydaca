package controllers
{
	import mx.rpc.AsyncToken;
	import mx.rpc.Responder;
	import mx.rpc.soap.mxml.WebService;
	
	import remote.MyMoneyWebService;

	public class AdicionarCompromissoController
	{
		private static var instance:AdicionarCompromissoController = null;
		
		private var remoteObject:WebService = null;
		

		public function AdicionarCompromissoController()
		{
			this.remoteObject = MyMoneyWebService.getInstance();
		}
		
		public static function getInstance():AdicionarCompromissoController {
			if (instance == null) {
				instance = new AdicionarCompromissoController();
			}
			return instance;
		}
		
		public function adicionarCompromisso(login:String, descricao:String, data:String, valor:Number, 
				tipo:String, freq:String, result:Function, fault:Function):void {
			var async:AsyncToken = remoteObject.addCommitment(login, descricao, data, valor, tipo, freq);
			async.addResponder(new Responder(result, fault));
		}

	}
}