package com.seifernet.firegoblin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seifernet.firegoblin.dispatcher.ContextDispatcher;

/**
 * Simple dispatch servlet implementation
 * 
 * @author Seifer ( Cuauhtemoc Herrera Muñoz )
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class ContextServlet extends DispatcherServlet{
	
	private static final long serialVersionUID = 1L;
	
	public static final int HTTP_RESPONSE 	= 1;
	public static final int HTML_RESPONSE 	= 2; 
	public static final int	HTTP_REDIRECT 	= 3; 
	public static final int JSON_RESPONSE 	= 4;
	public static final int NO_RESPONSE		= 5;
	
	/**
	 * Here must be referenced the dispatcherHelper implementation
	 * used to dispatch servlet request action.
	 * 
	 * @return The dispatcherHelper object
	 */
	protected abstract ContextDispatcher getDispatcher( );
	
	/**
	 * Sends a response based on DispatcherHelper getResponseType
	 * value
	 * 
	 * @param request - The http request
	 * @param response - The http response
	 * @param data - Data to be sent ( Can be URL or DATA/ENCODED DATA )
	 */
	protected void sendResponse( HttpServletRequest request, HttpServletResponse response, String data, int responseType ) throws ServletException, IOException{
		switch( responseType ){
			case HTTP_RESPONSE:
				httpResponse( request, response, data );
				break;
			case HTTP_REDIRECT:
				httpRedirect( request, response, data );
				break;
			case HTML_RESPONSE:
				htmlResponse( request, response, data );
				break;
			case JSON_RESPONSE:
				jsonResponse( request, response, data );
				break;
			case NO_RESPONSE:
				noResponse( request, response, data );
		}
	}
	
	/**
	 * Simple listen implementation for tiles, gets the action from request URL, then the action
	 * dispatch is delegated to the dispatcherHelper using object's dispatchAction method.
	 */
	@Override
	protected void listen( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		String 				action 			= getRequestContext( request );
		ContextDispatcher 	dispatcher		= getDispatcher( );
		String 				actionResponse 	= dispatcher.dispatchAction( request, response, action );
		
		sendResponse( request, response, actionResponse,dispatcher.getResponseType( ) );
	}

}
