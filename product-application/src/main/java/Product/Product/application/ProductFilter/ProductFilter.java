package Product.Product.application.ProductFilter;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;


import java.io.IOException;
import java.util.Enumeration;


public class ProductFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        // Log all headers to check the Authorization header
        Enumeration<String> headers = httpServletRequest.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headerName = headers.nextElement();
            System.out.println("Header: " + headerName + " = " + httpServletRequest.getHeader(headerName));
        }

        // Retrieve the Authorization header
        String authHeader = httpServletRequest.getHeader("Authorization");
        System.out.println("Authorization Header: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            throw new ServletException("Token missing or invalid");
        } else {
            String token = authHeader.substring(7); // Remove "Bearer " from the start
            Claims claims = Jwts.parser().setSigningKey("securitykeyfordemo").parseClaimsJws(token).getBody();
            String email = claims.get("UserEmail").toString();
            System.out.println("Extracted email from token: " + email); // Log the extracted email

            httpServletRequest.setAttribute("attr1", email);
            httpServletRequest.setAttribute("attr2", claims.get("UserRole"));
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletResponse httpServletResponse=(HttpServletResponse) servletResponse;
//        HttpServletRequest httpServletRequest=(HttpServletRequest) servletRequest;
//        String authhead=httpServletRequest.getHeader("Authorization");
//        if(authhead==null || !authhead.startsWith("Bearer"))
//        {
//            throw  new ServletException("token missing");
//        }
//        else {
//
//            String token = authhead.substring(7);
//            // Bearer eyJhbGciOiJIUzUxMiJ9.eyJVc2VyUm9sZSI6IlVzZXItUm9sZSIsInVzZXJFbWFpbCI6ImZpdmVAZ21haWwu
//            // Y29tIiwiaWF0IjoxNjgxMTkxMzA1fQ.yNwcGWP5BlBePVRIAzl36fTlmmNM8NIIk9
//            // Esh8Vk4yKp2sIy7CG9co0-Yqh_WUYt4Dnn5gkb1CSebU8c9v3HYA
//            try {
//                Claims claims = Jwts.parser().setSigningKey("securitykeyfordemo").parseClaimsJws(token).getBody();
//                System.out.println("Decoded JWT Claims: " + claims);
//                String email = (String) claims.get("UserEmail");  // Extract email from the token
//                if (email == null) {
//                    System.out.println("Email is missing in the token.");
//                    throw new ServletException("Missing email in token.");
//                }
//
//                // Log the extracted email to verify
//                System.out.println("Extracted Email from Token: " + email);
//                System.out.println("Extracted email from token: " + claims.get("UserEmail"));
//
//                httpServletRequest.setAttribute("attr1", claims.get("UserEmail"));
//                httpServletRequest.setAttribute("attr2", claims.get("UserRole"));
//
//            } catch (Exception e){
//                System.out.println("Error extracting claims: " + e.getMessage());  // Log any errors
//            }
//        }
//        filterChain.doFilter(httpServletRequest,httpServletResponse);
//
//    }
}
