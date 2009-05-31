package util
{
	public class OperationEntry
	{
		var _type:String;
		var _way:String;
		var _amount:Number;
		var _date:String;
		var _id:Number;
		
		public function OperationEntry()
		{
		}
		
		public function get type():String {
			return _type;
		}
		
		public function set type(type:String():void {
			this._type = type;
		}

		public function get way():String {
			return _way;
		}
		
		public function set way(way:String():void {
			this._way = way;
		}
		public function get amount():Number {
			return _amount;
		}
		
		public function set amount(amount:Number():void {
			this._amount = amount;
		}
		public function get date():String {
			return _date;
		}
		
		public function set date(date:String():void {
			this._date = date;
		}
		public function get id():Number {
			return _id;
		}
		
		public function set id(id:Number():void {
			this._id = id;
		}
	}
}