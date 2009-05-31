package util
{
	public class CommitmentEntry
	{
		
		private var _label:String;
		private var _valor:Number;
		private var _date:String;
		private var _type:String;
		private var _frequency:String;
		private var _id:Number;
		
		public function CommitmentEntry()
		{
		}
		
		public function get label():String {
			return _label;
		}
		
		public function set label(label:String):void {
			this._label = label;
		}
		
		public function get valor():Number {
			return _valor;
		}
		
		public function set valor(valor:Number):void {
			this._valor = valor;
		}

		public function get date():String {
			return _date;
		}
		
		public function set date(date:String):void {
			this._date = date;
		}

		public function get type():String {
			return _type;
		}
		
		public function set type(type:String):void {
			this._type = type;
		}

		public function get frequency():String {
			return _frequency;
		}
		
		public function set frequency(frequency:String):void {
			this._frequency = frequency;
		}
		
		public function get id():Number {
			return _id;			
		}
		
		public function set id(id:Number):void {
			this._id = id;
		}
	}
}