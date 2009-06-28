package remote
{
	import mx.collections.ArrayCollection;
	import mx.rpc.soap.mxml.WebService;
	import mx.rpc.xml.SchemaTypeRegistry;
	
	public class MyMoneyWebService
	{
		private var ws:WebService = null;
		private static var instance:MyMoneyWebService = null;
		
		// infelizmente construtores nao podem ser privados.
		// tem como forcar a usar o outro, mas achei o codigo porco, entao
		// deixa assim.
		public function MyMoneyWebService()
		{
			configurarTipos();
			this.ws = new WebService();
			this.ws.wsdl = "/axis2/services/MyMoneyService?wsdl";
			this.ws.showBusyCursor = false;
			this.ws.loadWSDL();
		}
		
		public function configurarTipos():void {
//			SchemaTypeRegistry.getInstance().registerCollectionClass(new QName("http://util.java/xsd","Collection"),mx.collections.ArrayCollection);
//			SchemaTypeRegistry.getInstance().registerClass(new QName("http://model.mynamespace","Country"),myflex.model.Country);
		}
		
		public static function getInstance():WebService {
			if (instance == null) {
				instance = new MyMoneyWebService();
			}
			return instance.ws;
		}
		
	}
}