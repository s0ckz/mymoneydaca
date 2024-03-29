package com.thoughtworks.selenium;

import java.util.Arrays;

/*
 * The FlashSelenium is the component adding Flash 
 * communication capabilities to the Selenium framework. 
 * Basically, the FlashSelenium is a Selenium RC Client 
 * driver extension for helping exercise the tests 
 * against the Flash component.  
 **/
public class FlashSelenium implements IFlashSelenium {
	private static final String FUNCTION_TO_BE_TESTED = "PercentLoaded";
	private Selenium selenium;
	private String flashJSStringPrefix = null;
	private final String flashObjectId;

	public FlashSelenium(Selenium selenium, String flashObjectId) {
		this.selenium = selenium;
		this.flashObjectId = flashObjectId;
	}
	
	// constructor used for test purpose
	FlashSelenium(Selenium browser, String flashObjectId, String flashJSStringPrefix) {
		this.selenium = browser;
		this.flashObjectId = flashObjectId;
		this.flashJSStringPrefix = flashJSStringPrefix;
	}

	
	// creational method used for test purpose
	static FlashSelenium createFlashSeleniumFlashObjAsDocument(Selenium browser, String flashObjectId){
		return new FlashSelenium(browser, flashObjectId, createJSPrefix_document(flashObjectId));
	}
	
	static FlashSelenium createFlashSeleniumFlashObjAsWindowDocument(Selenium browser, String flashObjectId){
		return new FlashSelenium(browser, flashObjectId, createJSPrefix_window_document(flashObjectId));
	}
	
	static String createJSPrefix_window_document(String flashObjectId) {
		return "window.document['"
			+ flashObjectId + "']";
	}

	static String createJSPrefix_document(String flashObjectId) {
		return "document['"
			+ flashObjectId + "']";
	}

	static String createJSPrefix_browserbot(String flashObjectId) {
		return "this.browserbot.findElement(\"" + flashObjectId + "\")";
	}


	private void createFlashJSStringPrefix() {
			if (flashJSStringPrefix == null) {
			String[] prefixes = new String[] {
					createJSPrefix_browserbot(flashObjectId),
					createJSPrefix_window_document(flashObjectId),
					createJSPrefix_document(flashObjectId)
			};
			
			for (int i = 0; i < prefixes.length; i++) {
				if (selenium.getEval(prefixes[i] + " == null").equals("false")) {
					flashJSStringPrefix = prefixes[i];
					break;
				}
			}
			
			if (flashJSStringPrefix == null) {
				throw new RuntimeException("Impossible to access the flash component. " +
						"Tried the following prefixes: " + Arrays.toString(prefixes));
			} else {
				if (!isFunction(flashJSStringPrefix)) {
					String prefix = null;
					boolean functionCaught = false;
					int childNodesLength = Integer.parseInt(selenium.getEval(flashJSStringPrefix + ".childNodes.length"));
					for (int i = 0; i < childNodesLength; i++) {
						prefix = flashJSStringPrefix + ".childNodes[" + i + "]";
						if (isFunction(prefix)) {
							flashJSStringPrefix = prefix;
							functionCaught = true;
							break;
						}
					}
					if (!functionCaught) {
						throw new RuntimeException("The flash component was found, but no functions could " +
								"be acessed. Maybe the Selenium Flex API (SeleniumFlexAPI.swc) isn't present?");
					}
				}
				flashJSStringPrefix += ".";
			}
		}
	}
	
	private boolean isFunction(String prefix) {
		return !selenium.getEval("typeof(" + prefix + "['" + FUNCTION_TO_BE_TESTED + "'])").equals("undefined");
	}

	public String call(String functionName, String ... args) {
		createFlashJSStringPrefix();
		return selenium.getEval(this.jsForFunction(functionName, args));
	}

	String flashJSStringPrefix(){
		return this.flashJSStringPrefix;
	}

	String jsForFunction(String functionName, String ... args) {
		String functionArgs = "";
		if (args.length>0){;
	      for (int i=0;i < args.length; i++) {
	    	  functionArgs = functionArgs + "'" + args[i] + "',";
	      }
	      // remove last comma
	      functionArgs = functionArgs.substring(0, functionArgs.length() -1);
		}
		return flashJSStringPrefix + functionName + "(" + functionArgs + ");";
	}	

	public String GetVariable(String varName) {
		return this.call("GetVariable", varName);
	}
	
	public void GotoFrame(int frameNumber) {
		this.call("GotoFrame", Integer.toString(frameNumber));
	}
	
	public boolean IsPlaying() {
		return "true".equals(this.call("IsPlaying"));
	}
	
	public void LoadMovie(int layerNumber, String url ) {
		this.call("LoadMovie", Integer.toString(layerNumber), url );
	}
	
	public void Pan(int x,int y,int mode) {
		this.call("Pan", Integer.toString(x), Integer.toString(y), Integer.toString(mode));
	}
	
	public int PercentLoaded() {
		return new Integer(this.call("PercentLoaded")).intValue();
	}
	
	public void Play() {
		this.call("Play");
	}
	
	public void Rewind() {
		this.call("Rewind");
	}
	
	public void SetVariable(String varName, String varValue) {
		this.call("SetVariable", varName,  varValue);
	}
	
	public void SetZoomRect(int left,int top,int right,int bottom ) {
		this.call("SetZoomRect", Integer.toString(left), Integer.toString(top), Integer.toString(right), Integer.toString(bottom));
	}	
	
	public void StopPlay() {
		this.call("StopPlay");
	}
	
	public int TotalFrames() {
		return new Integer(this.call("TotalFrames")).intValue();
	}

	public void Zoom(int percent) {
		this.call("Zoom", Integer.toString(percent));
	}	
	
	public void TCallFrame (String target, int frameNumber) {
		this.call("TCallFrame", target, Integer.toString(frameNumber));
	}	
	
	public void TCallLabel (String target, String label ) {
		this.call("TCallLabel", target, label );
	}
	
	public int TCurrentFrame(String target) {
		return new Integer(this.call("TCurrentFrame", target)).intValue();
	}	
	
	public String TCurrentLabel(String target) {
		return this.call("TCurrentLabel", target);
	}	

	public String TGetProperty(String target, String property) {
		return this.call("TGetProperty", target, property);
	}	
	
	public int TGetPropertyAsNumber(String target, String property) {
		return new Integer(this.call("TGetPropertyAsNumber", target, property)).intValue();
	}	
	
	public void TGotoFrame (String target, int frameNumber) {
		this.call("TGotoFrame", target, Integer.toString(frameNumber));
	}	
	
	public void TGotoLabel (String target, String label ) {
		this.call("TGotoLabel", target, label );
	}
	
	public void TPlay (String target ) {
		this.call("TPlay", target );
	}
	
	public void TSetProperty(String target, String property, String value) {
		this.call("TSetProperty", target, property, value);
	}	
	
	public void TStopPlay(String target) {
		this.call("TStopPlay", target);
	}
	
	public void OnProgress(int percent) {
		this.call("OnProgress", Integer.toString(percent));
	}	
	
	public void OnReadyStateChange(int state) {
		this.call("OnReadyStateChange", Integer.toString(state));
	}
	
	public String FSCommand(String command, String ... args) {
		String[] newArgs = new String[args.length+1];
		newArgs[0] = command;
	    for (int i=0;i < args.length; i++) {
	    	newArgs[i+1] = args[i];
	    }
		return this.call("FSCommand", newArgs);
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((selenium == null) ? 0 : selenium.hashCode());
		result = prime
				* result
				+ ((flashJSStringPrefix == null) ? 0 : flashJSStringPrefix
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final FlashSelenium other = (FlashSelenium) obj;
		if (selenium == null) {
			if (other.selenium != null)
				return false;
		} else if (!selenium.equals(other.selenium))
			return false;
		if (flashJSStringPrefix == null) {
			if (other.flashJSStringPrefix != null)
				return false;
		} else if (!flashJSStringPrefix.equals(other.flashJSStringPrefix))
			return false;
		return true;
	}



	
	

}
