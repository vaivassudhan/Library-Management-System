package Filters;

import Utils.Util;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
    public static Set<String> errors;
//  function to check regex
    public static boolean checkRegex(String value, String regex){
        Pattern pattern = Pattern.compile(regex);
        if(value == null){
            return false;
        }
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
    public static void validateFields(JSONObject validationObject, String requestKey, String requestValue){
        if(validationObject.getBoolean("required")){
           if(requestValue == null){
               errors.add(requestKey + ErrorMessages.REQUIRED);
           }
        }

//        if(validationObject.has("min-length") && validationObject.has("max-length")){
//            if(requestValue.length() < validationObject.getInt("min-length")){
//                errors.add(requestKey + ", " + ErrorMessages.MIN_LENGTH);
//            }
//            if(requestValue.length() > validationObject.getInt("max-length")){
//                errors.add(requestKey + ", " + ErrorMessages.MAX_LENGTH);
//            }
//        }
        if(!checkRegex(requestValue,validationObject.getString("regex"))){
            errors.add(requestKey + ", " + ErrorMessages.REGEX);
        }

    }

    public static void validateData(JSONObject validatorObject, JSONObject requestObject){
//        System.out.println(requestObject);
        Iterator<String> keys = requestObject.keys();
        while(keys.hasNext()) {
            String key = keys.next();

            if(!validatorObject.has(key)){
                errors.add(ErrorMessages.ADDITIONAL_FIELD);
                continue;
            }


            validateFields((JSONObject) validatorObject.get(key), key , (requestObject.get(key)).toString());

        }

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        errors = new HashSet<>();
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getServletPath();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        JSONObject jsonSchema = new JSONObject(new JSONTokener(Objects.requireNonNull(classLoader.getResourceAsStream("/validator.json"))));
        if(!jsonSchema.has(path)){
            request.getRequestDispatcher(((HttpServletRequest) request).getServletPath()).forward(request, response);
        }
        jsonSchema = (JSONObject) jsonSchema.get(path);
        String jb = Util.jsonRequestHandler(httpRequest);
        JSONObject jsonObject = new JSONObject(jb);

        validateData(jsonSchema,jsonObject);
        System.out.println("TEST " + errors.size());
        if(errors.size() > 0 ){
            System.out.println("HELLO ERROR FOUND");
            PrintWriter out = response.getWriter();
            JsonObject errorsJson = new JsonObject();
            JSONArray errorJsonArray = new JSONArray(errors.toArray());
            errorsJson.addProperty("message", String.valueOf(errorJsonArray));
            out.write(String.valueOf(errorsJson));
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(HttpURLConnection.HTTP_BAD_REQUEST);
            request.getRequestDispatcher("/error");
            httpResponse.setContentType("application/json");

            out.flush();

            return;
        }
        if(errors.size() == 0){
            System.out.println("NO Error");
            request.setAttribute("requestJson",jb);
//            chain.doFilter(httpRequest,response);
            request.getRequestDispatcher(((HttpServletRequest) request).getServletPath()).forward(request, response);
        }




    }

}
