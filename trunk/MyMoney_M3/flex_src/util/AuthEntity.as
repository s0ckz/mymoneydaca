package util
{
	public class AuthEntity
	{
		private var _login:String;
		
		private var _senha:String;
		
		private var _accId:Number;
		
		private static var instance:AuthEntity = null;		
		
		public function AuthEntity()
		{
			resetAccId();
		}
		
		public static function getInstance():AuthEntity {
			if (instance == null) {
				instance = new AuthEntity();
			}
			return instance;
		}
		
		public function isAccIdReseted():Boolean {
			return accId == -1;
		}
		
		public function resetAccId():void {
			accId = -1;
		}
		
		public function get accId():Number {
			return _accId;
		}
		
		public function set accId(accId:Number):void {
			this._accId = accId;
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