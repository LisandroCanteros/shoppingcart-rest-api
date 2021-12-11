package com.informatorio.shoppingcart.controller;

import com.informatorio.shoppingcart.dto.CartOperation;
import com.informatorio.shoppingcart.entity.Cart;
import com.informatorio.shoppingcart.entity.InvoiceLine;
import com.informatorio.shoppingcart.entity.Product;
import com.informatorio.shoppingcart.entity.User;
import com.informatorio.shoppingcart.repository.CartRepository;
import com.informatorio.shoppingcart.repository.ProductRepository;
import com.informatorio.shoppingcart.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class CartController {
    private CartRepository cartRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;

    public CartController(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @GetMapping(value = "user/{idUser}/cart")
    public ResponseEntity<?> getCartsFrom(@PathVariable Long idUser){
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        List<Cart> carts = user.getCarts();
        return new ResponseEntity(carts, HttpStatus.OK);
    }

    @GetMapping(value = "/cart/{idCart}")
    public ResponseEntity<?> getCart(@PathVariable Long idCart){
        Cart cart = cartRepository.findById(idCart)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return new ResponseEntity(cart, HttpStatus.OK);
    }

    @PostMapping("/user/{idUser}/cart")
    public ResponseEntity<?> createCart(@PathVariable Long idUser, @RequestBody Cart cart){
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        cart.setUser(user);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/cart/{idCart}")
    public ResponseEntity<?> addProduct(@PathVariable Long idCart,
                                        @RequestBody CartOperation cartOperation){
        Cart cart = cartRepository.findById(idCart)
                .orElseThrow(() -> new EntityNotFoundException("Cart not found"));
        Product product = productRepository.findById(cartOperation.getProdId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        InvoiceLine invoiceLine = new InvoiceLine();
        invoiceLine.setCart(cart);
        invoiceLine.setProduct(product);
        invoiceLine.setAmount(cartOperation.getAmount());
        cart.addInvoiceLine(invoiceLine);
        return new ResponseEntity(cartRepository.save(cart), HttpStatus.CREATED);

    }

}
