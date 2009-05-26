package util
{
	public class AuthEntity
	{
		private var _login:String;
		
		private var _senha:String;
		
		private static var instance:AuthEntity = null;		
		
		public function AuthEntity()
		{
		}
		
		public static function getInstance():RemoteFacade {
			if (instance == null) {
				instance = new RemoteFacade();
			}
			return instance;
		}
		
		public function get login():String {
			return _login;
		}
		
		public function set login(login:String) {
			this._login = login;
		}

		public function get senha():String {
			return _senha;
		}
		
		public function set senha(senha:String) {
			this._senha = senha;
		}

	}
}