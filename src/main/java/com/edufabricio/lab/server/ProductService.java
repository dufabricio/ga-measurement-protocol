package com.edufabricio.lab.server;

import com.edufabricio.lab.model.ProductDTO;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Service;

@Service
public class ProductService {


    public ProductDTO getProduct(Exchange exnchange){
        return new ProductDTO();
    }

    public ProductDTO insertProduct(Exchange exchange){
        return new ProductDTO();
    }

    public ProductDTO updateProduct(Exchange exchange){
        return new ProductDTO();
    }
}
