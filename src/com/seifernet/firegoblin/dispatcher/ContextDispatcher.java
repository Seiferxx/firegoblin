package com.seifernet.firegoblin.dispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This interface represents main dispatcher who
 * gets the action as a String and redirects the
 * task to a specialized helper
 * 
 * @author Seifer ( Cuauhtemoc Herrera Muñoz )
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ContextDispatcher {
	
	/**
	 * Returns the value of responseType variable
	 * used to change sendResponse method behavior
	 * 
	 * @return the responseType
	 */
	public int getResponseType( );
	
	/**
	 * Main method who gets the action as a String and redirects
	 * the task to a specialized helper
	 * 
	 * @param request - The http request
	 * @param response - The http response
	 * @param action - String with the specific request action
	 * @return Response data
	 */
	public String dispatchAction( HttpServletRequest request, HttpServletResponse response, String action );

}
