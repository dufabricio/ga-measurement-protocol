package com.edufabricio.lab.server;

import com.edufabricio.lab.exception.AuthenticationRequiredException;
import com.edufabricio.lab.exception.BadRequestException;
import com.edufabricio.lab.exception.PermissionDeniedException;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.cxf.common.i18n.Exception;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

@Component
public class MockApplicationServiceRouteBuilder extends RouteBuilder {


    @Override
    public void configure() throws Exception {

        onException(BadRequestException.class).handled(true).process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                Throwable caused = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
                Response response = Response.status(400).entity("{\"error\":\"" + caused.getMessage() + "\"}").build();
                exchange.getOut().setBody(response);
                exchange.setProperty(Exchange.ROUTE_STOP, Boolean.TRUE);
            }
        });

        onException(NotFoundException.class).handled(true).process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                Throwable caused = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
                Response r = Response.status(404).entity("{\"error\":\"" + caused.getMessage() + "\"}").build();
                exchange.getOut().setBody(r);
                exchange.setProperty(Exchange.ROUTE_STOP, Boolean.TRUE);
            }
        });

        onException(AuthenticationRequiredException.class).handled(true).process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                Throwable caused = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
                Response response = Response.status(401).entity("{\"error\":\"" + caused.getMessage() + "\"}").build();
                exchange.getOut().setBody(response);
                exchange.setProperty(Exchange.ROUTE_STOP, Boolean.TRUE);
            }
        });

        onException(PermissionDeniedException.class).handled(true).process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                Throwable caused = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
                Response response = Response.status(403).entity("{\"error\":\"" + caused.getMessage() + "\"}").build();
                exchange.getOut().setBody(response);
                exchange.setProperty(Exchange.ROUTE_STOP, Boolean.TRUE);
            }
        });




        configureInstructionService();
    }


    private void configureInstructionService() {

        from("cxfrs:bean:product?bindingStyle=SimpleConsumer")
                .recipientList(simple("direct:${header.operationName}")).process(new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                exchange.getIn().removeHeader("Content-Length");
            }
        });

        from("direct:getProduct")
                .to("bean:productService?method=getProduct(*)");

        from("direct:updateProduct")
                .to("bean:productService?method=updateProduct(*)");

        from("direct:insertProduct")
                .to("bean:productService?method=insertProduct(*)");
    }




}