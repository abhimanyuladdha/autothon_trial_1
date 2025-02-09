package com.autothon.stepdefinition.desktop;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton class that provides thread-local storage for context data.
 * Use this class to store data that need to be shared between different feature steps
 */

public class ScenarioContext {

	private static final ScenarioContext instance = new ScenarioContext();
	private ThreadLocal<Map<String, Object>> threadContext = ThreadLocal.withInitial(HashMap::new);

	private ScenarioContext() {
	}

	public static ScenarioContext getInstance() {
		return instance;
	}

	/**
	 * Sets a context value for the current thread. 
	 * 
	 * @param key   The key to associate the value with.
	 * @param value The value to store in the context.
	 */
	public void setContext(String key, Object value) {
		threadContext.get().put(key, value);
	}

	/**
	 * Retrieves the context value associated with the specified key 
	 * 
	 * @param key The key to retrieve the value for.
	 * @return The value associated with the key, or null if the key is not found.
	 */
	public Object getcontext(String key) {
		return threadContext.get().put(key, key);
	}
	
	/**
	 * Clear thecontext value for the current thread. 
	 */
	public void clear() {
		threadContext.get().clear();
	}
}
