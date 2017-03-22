package com.edufabricio.lab.server;

import javax.servlet.http.HttpServlet;

/**
 * Implementacao para ImplicitGrant no Swagger, desnecessario para esse projeto em questao.
 */
public class BootStrap extends HttpServlet {
    /*static {

        String uri = ServiceContext.getInstance().getService("s4b-api").getUri();
        StringBuilder sb = new StringBuilder();
        ServiceProvider serviceProvider = ServiceContext.getInstance().getService("s4b-api");
        if(serviceProvider.getProtocol() == null){
            sb.append("http");
        }else{
            sb.append(serviceProvider.getProtocol());
        }
        sb.append("://");
        sb.append(serviceProvider.getHost());
        uri = sb.toString();

        ImplicitGrant implicitGrant = new ImplicitGrant(new LoginEndpoint(uri + "/account/api/authorize-implicit"), "access_token");

        List<GrantType> grantTypes = new ArrayList<GrantType>();
        grantTypes.add(implicitGrant);

        AuthorizationType oauth = new OAuthBuilder().grantTypes(grantTypes).build();

        ConfigFactory.config().addAuthorization(oauth);
    }*/
}