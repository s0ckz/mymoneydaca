package controllers
{
	import mx.rpc.AsyncToken;
	import mx.rpc.Responder;
	import mx.rpc.soap.mxml.WebService;
	
	import remote.MyMoneyWebService;

	public class AdicionarTransacaoController
	{
		private static var instance:AdicionarTransacaoController = null;
		
		private var remoteObject:WebService = null;
		

		public function AdicionarTransacaoController()
		{
			this.remoteObject = MyMoneyWebService.getInstance();
		}
		
		public static function getInstance():AdicionarTransacaoController {
			if (instance == null) {
				instance = new AdicionarTransacaoController();
			}
			return instance;
		}
		
		public function adicionarTransacao(login:String, accId:Number, tipo:String, forma:String, 
				valor:Number, data:String, result:Function, fault:Function):void {
			var async:AsyncToken = remoteObject.addOperation(login,accId, tipo, forma, valor, data);
			async.addResponder(new Responder(result, fault));
		}
	}
}