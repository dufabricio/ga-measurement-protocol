package com.edufabricio.lab.server;

import com.edufabricio.lab.model.ProductDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Service
@Api(value = "Product", basePath = "/services/product", description = "Operations about information for products.", position = 1)
public class ProductServiceRest {

    public ProductServiceRest() {
    }

    @GET
    @Path("/{productId}")
    @ApiOperation(
            position = 1,
            value = "Return product by id",
            response = ProductDTO.class)
    @Produces({MediaType.APPLICATION_JSON})
    public ProductDTO getProduct(@PathParam("productId") Long productId){return null;}

    @POST
    @Path("/")
    @ApiOperation(
            value = "insert a product by id",
            response = ProductDTO.class)
    @Produces({MediaType.APPLICATION_JSON})
    public ProductDTO insertProduct(ProductDTO product){return null;}

    @PUT
    @Path("/{productId}")
    @ApiOperation(
            position = 1,
            value = "update a product by id",
            response = ProductDTO.class)
    @Produces({MediaType.APPLICATION_JSON})
    public ProductDTO updateProduct(@PathParam("productId") Long productId, ProductDTO product){return null;}

}
