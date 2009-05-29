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
		
		public static function getInstance():AuthEntity {
			if (instance == null) {
				instance = new AuthEntity();
			}
			return instance;
		}
		
		public function get login():String {
			return _login;
		}
		
		public function set login(login:String):void {
			this._login = login;
		}

		public function get senha():String {
			return _senha;
		}
		
		public function set senha(senha:String):void {
			this._senha = senha;
		}

	}
}