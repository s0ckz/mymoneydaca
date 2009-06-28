package util
{
	import mx.collections.ArrayCollection;
	
	public class WebServicesTypes
	{
		public function WebServicesTypes()
		{
		}
		
		public static function getArrayCollection(o: Object):ArrayCollection {
			if (o is ArrayCollection)
				return (o as ArrayCollection);
			else {
				var collection:ArrayCollection = new ArrayCollection();
				collection.addItem(o);
				return collection;			
			}
		}

	}
}