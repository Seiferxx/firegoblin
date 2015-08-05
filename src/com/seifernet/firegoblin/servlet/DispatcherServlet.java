package com.seifernet.firegoblin.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Base framework servlet that provides simple common utilities
 * 
 * @author Seifer ( Cuauhtemoc Herrera Muñoz )
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class DispatcherServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	public static final String CONTENT_PLAIN_TEXT 		= "text/plain";
	public static final String CONTENT_HTML_TEXT 		= "text/html";
	public static final String CONTENT_JSON_APPLICATION = "application/json";
	
	public static final String UTF8_CHARSET 			= "utf-8";

	/**
	 * Via get call to servlet, this method redirects to listen which must be implemented
	 * 
	 * @param request - The http request
	 * @param response - The http response
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		listen( request, response );
	}

	/**
	 * Via post call to servlet, this method redirects to listen which must be implemented
	 * 
	 * @param request - The http request
	 * @param response - The http response
	 * @throws IOException
	 * @throws ServletException
	 */
	protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		listen( request, response );
	}
	
	/**
	 * This method is the main servlet dispatcher, must be implemented to delegate
	 * requests to a backend helper that implements DispatcherHelper
	 * <br>
	 * This delegation can be performed by using the method getRequestContext to 
	 * obtain the path and making a classification of that path.
	 * <br>
	 * When request is correctly dispatched and solved, response can be send to implemented
	 * response methods
	 * 
	 * @param request - The http request
	 * @param response - The http response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected abstract void listen( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException;
	
	/**
	 * Sends a JSON encoded response
	 * 
	 * @param request - The http request
	 * @param response - The http response
	 * @param encodedString - The json encoded string to be sent
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void jsonResponse( HttpServletRequest request, HttpServletResponse response, String encodedString ) throws IOException{
		PrintWriter printWriter =  response.getWriter( );
		
		response.setHeader( "Content-Type", CONTENT_JSON_APPLICATION + "; " + UTF8_CHARSET );
		printWriter.write( encodedString );
	}
	
	/**
	 * Doesn't execute any response type
	 * operation, this method must be used only if
	 * the response has been handled in another
	 * method inside the application.
	 * 
	 * @param request
	 * @param response
	 * @param data
	 */
	protected void noResponse( HttpServletRequest request, HttpServletResponse response, String data ) throws IOException{
		//NOTHING TO DO
	}
	
	/**
	 * Sends an HTML encoded response
	 * 
	 * @param request - The http request
	 * @param response - The http response
	 * @param encodedString - The html encoded string to be sent
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void htmlResponse( HttpServletRequest request, HttpServletResponse response, String encodedString ) throws IOException{
		PrintWriter printWriter = response.getWriter( );
		
		response.setHeader( "Content-Type", CONTENT_HTML_TEXT + "; " + UTF8_CHARSET );
		printWriter.write( encodedString );
	}
	
	/**
	 * Sends a HTTP response
	 * 
	 * @param request - The http request
	 * @param response - The http response
	 * @param url - The target url for dispatcher
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void httpResponse( HttpServletRequest request, HttpServletResponse response, String url ) throws ServletException, IOException{
		RequestDispatcher dispatcher =  request.getRequestDispatcher( url );
		
		dispatcher.forward( request, response );
	}
	
	/**
	 * Sends a HTTP response creating new
	 * request object
	 * 
	 * @param request - The http request
	 * @param response - The http response
	 * @param url - The target url for redirect
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void httpRedirect( HttpServletRequest request, HttpServletResponse response, String url ) throws ServletException, IOException{
		
		response.sendRedirect( url );
	}
	
	/**
	 * This method obtains the context of request URL
	 * <br>Example<br>
	 * Request URL: http://example.com/myproject/myservlet/actiongroup/action<br>
	 * Context: /actiongroup/action   
	 * 
	 * @param request -The http request
	 * @return The request URL context
	 */
	protected String getRequestContext( HttpServletRequest request ){
		String context = request.getPathInfo( );
		
		if( context == null ){
			return "/";
		} else if( context.equals( "" ) ){
			return "/";
		}
		return context;
	}


}
