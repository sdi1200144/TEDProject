package web;

import user_interface.UserBean;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter("/faces/restricted/guest/*")

public class GuestFilter implements Filter 
{

    private static final boolean debug = true;
    private static String   wasInvoked = " *no**";

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig;

    public GuestFilter() 
    {
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException 
    {
        if (debug) 
        {
            log("GuestFilter:DoBeforeProcessing");
        }

        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response) throws IOException, ServletException 
    {
        if (debug) 
        {
            log("GuestFilter:DoAfterProcessing");
        }

        // Write code here to process the request and/or response after
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log the attributes on the
        // request object after the request has been processed. 
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException 
    {

        if (debug) 
        {
            log("GuestFilter:doFilter()");
        }

        doBeforeProcessing(request, response);

        Throwable problem = null;
        try 
        {
            // Casting ServletRequest/Response to HttpServletRequest/Response
            // objects to use getSession() method
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            UserBean user = (UserBean) req.getSession().getAttribute("user");

            if(user.isLoggedIn() && user.getRole().equals("Host")) {
            	res.sendRedirect(req.getContextPath() + "/faces/index.xhtml");
            	 wasInvoked = " **yes**";
            }
//            else if(user.isLoggedIn()) {
//            	//System.out.println(" user is " + user.getRole());
//            	
//            	if(user.getRole().equals("Guest") || user.getRole().equals("Host") || user.getRole().equals("Host&Guest")) {
//            		// res.sendRedirect(req.getContextPath() + "/faces/restricted/user_home.xhtml");
//            	}
//            	wasInvoked = " **yes**" + user.getRole();
//            	 chain.doFilter(request, response);
//            }
            else
            {
                // Move on to next filter in chain (if any)
                chain.doFilter(request, response);
            }

        } 
        catch (Throwable t) 
        {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }

        doAfterProcessing(request, response);

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) 
        {
            if (problem instanceof ServletException) 
            {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) 
            {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() 
    {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) 
    {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() 
    {
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) 
    {
        this.filterConfig = filterConfig;
        if (filterConfig != null) 
        {
            if (debug) 
            {
                log("GuestFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() 
    {
        if (filterConfig == null) 
        {
            return ("GuestFilter()");
        }
        StringBuffer sb = new StringBuffer("GuestFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) 
    {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) 
        {
            try 
            {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } 
            catch (Exception ex) 
            {
            }
        } 
        else 
        {
            try
            {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } 
            catch (Exception ex) 
            {
            }
        }
    }

    public static String getStackTrace(Throwable t) 
    {
        String stackTrace = null;
        try 
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } 
        catch (Exception ex) 
        {
        }
        return stackTrace;
    }

    public void log(String msg) 
    {
        filterConfig.getServletContext().log(msg + wasInvoked);
    }

}