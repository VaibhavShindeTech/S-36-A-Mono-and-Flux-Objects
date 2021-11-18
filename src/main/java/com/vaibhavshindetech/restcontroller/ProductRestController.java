package com.vaibhavshindetech.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaibhavshindetech.response.Product;

import reactor.core.publisher.Mono;

/**
 * 
 * @author Vaibhav
 *
 */
@RestController
public class ProductRestController {
	@GetMapping(value = { "/product" })
	public ResponseEntity<Mono<Product>> getProduct() {
		Product product = new Product();
		product.setId(101);
		product.setName("Vivo y-73");
		Mono<Product> monoObj = Mono.just(product);
		return new ResponseEntity<Mono<Product>>(monoObj, HttpStatus.OK);
	}

}
