package Utils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.stream.Collectors;

public class AuthFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    private static final String[] adminURILS = {
            "/add-librarian", "/update-days", "/get-fine", "/update-fine"
    };

//  Function to check if the URL is admin only accessible url
    private boolean isAdminURL(String path){
        for(String url : adminURILS){
            if(path.equals(url)){
                return true;
            }
        }
        return false;
    }

//  Function to throw Unauthorized error
    private void unAuthorized(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.write(Util.createErrorJson("UnAuthorized"));
        response.setStatus(HttpURLConnection.HTTP_UNAUTHORIZED);
    }

//  Function to check for authorization in request header
    private boolean checkHeader(HttpServletRequest request){
        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String headerValue = headerNames.nextElement();
            if(headerValue.equals("authorization")){
                return true;
            }
        }
        return false;
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getServletPath();
        if(path.equals("/login")){
            request.getRequestDispatcher(path).forward(request, response);
            return;
        }
        if(!checkHeader(httpRequest)){
            unAuthorized((HttpServletResponse) response);
        }
        String token = httpRequest.getHeader("Authorization").split(" ")[1];
        if(isAdminURL(path)){
            if(Util.isAdmin(token)){
                request.getRequestDispatcher(((HttpServletRequest) request).getServletPath()).forward(request, response);
            }
            else{
                unAuthorized((HttpServletResponse) response);
            }
        }
        if(Util.verifyAuth(token)){
            request.getRequestDispatcher(((HttpServletRequest) request).getServletPath()).forward(request, response);
        }
        else{
            unAuthorized((HttpServletResponse) response);
        }

    }

}
