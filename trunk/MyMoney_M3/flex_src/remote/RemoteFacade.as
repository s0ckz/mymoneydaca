package remote
{
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.rpc.AsyncToken;
	import mx.rpc.Responder;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.remoting.mxml.RemoteObject;
	
	public class RemoteFacade
	{
		private static var instance:RemoteFacade = null;
		
		private var remoteObject:RemoteObject = null;
		
		// infelizmente construtores nao podem ser privados.
		// tem como forcar a usar o outro, mas achei o codigo porco, entao
		// deixa assim.
		public function RemoteFacade() {
			this.remoteObject = new RemoteObject("remoteFacade");
			this.remoteObject.showBusyCursor = false;
		}
		
		public static function getInstance():RemoteFacade {
			if (instance == null) {
				instance = new RemoteFacade();
			}
			return instance;
		}
		
		public function cleanAll():void {
			var async:AsyncToken = remoteObject.cleanAll();
		}
		
		public function teste(result: Function):void {
			var async:AsyncToken = remoteObject.teste();
			async.addResponder(new Responder(result, defaultFaultHandler));
		}
		
		public function registerUser(login:String, senha:String, nome:String, 
				gender:String, email:String, result:Function, fault: Function):void {
			var async:AsyncToken = remoteObject.registerUser(login, senha, nome, gender, email);
			async.addResponder(new Responder(result, fault));
		}
		
		public function doLogin(login:String, senha:String, result:Function, fault:Function):void {
			var async:AsyncToken = remoteObject.doLogin(login, senha);
			async.addResponder(new Responder(result, fault));
		}
		
		public function doLogout(login:String, senha:String, result:Function, fault:Function):void {
			var async:AsyncToken = remoteObject.doLogoff(login, senha);
			async.addResponder(new Responder(result, fault));
		}		

		public function createAccount(login:String, label:String, agencia:String, conta:String, result:Function, fault:Function):void {
			var async:AsyncToken = remoteObject.createAccount(login, label, agencia, conta);
			async.addResponder(new Responder(result, fault));
		}
		
		public function getAccountLabel(login:String, accId:Number, result:Function, fault:Function):void {
			var async:AsyncToken = remoteObject.getAccountLabel(login, accId);
			async.addResponder(new Responder(result, fault));
		}
		
		public function getAllAccountsIds(login:String, result:Function, fault:Function):void {
			var async:AsyncToken = remoteObject.getAllAccountsIds(login);
			async.addResponder(new Responder(result, fault));
		}
		
		public function getAccountsLabels(login:String, accIds:ArrayCollection, result:Function, fault:Function):void {
			var async:AsyncToken = remoteObject.getAccountsLabels(login, accIds);
			async.addResponder(new Responder(result, fault));
		}
		
		public function removeAccount(login:String, accId:Number, result:Function, fault:Function):void {
			var async:AsyncToken = remoteObject.removeAccount(login,accId);
			async.addResponder(new Responder(result, fault));
		}
		
		public function adicionarTransacao(login:String, accId:Number, tipo:String, forma:String, 
				valor:Number, data:String, result:Function, fault:Function):void {
			var async:AsyncToken = remoteObject.addOperation(login,accId, tipo, forma, valor, data);
			async.addResponder(new Responder(result, fault));
		}
		
		public function getAllOperations(login:String, accId:Number, result:Function, fault:Function):void {
			var async:AsyncToken = remoteObject.getAllOperations(login, accId);
			async.addResponder(new Responder(result, fault));
		}
		
		public function adicionarCompromisso(login:String, descricao:String, data:String, valor:Number, 
				tipo:String, freq:String, result:Function, fault:Function):void {
			var async:AsyncToken = remoteObject.addCommitment(login, descricao, data, valor, tipo, freq);
			async.addResponder(new Responder(result, fault));
		}
		
		public function getAllCompromissos(login:String, result:Function, fault:Function):void {
			var async:AsyncToken = remoteObject.getAllCommitments(login);
			async.addResponder(new Responder(result, fault));
		}
		
		public function removeCommitment(login:String, id:Number, result:Function, fault:Function):void {
			var async:AsyncToken = remoteObject.removeCommitment(login, id);
			async.addResponder(new Responder(result, fault));
		}
		
		private function defaultFaultHandler(e:FaultEvent):void{
			Alert.show(e.fault.toString(), e.fault.faultString);
		}
		

	}
}