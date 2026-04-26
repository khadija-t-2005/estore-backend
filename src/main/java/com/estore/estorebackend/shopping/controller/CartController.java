package com.estore.estoreProject.shopping.controller;

import com.estore.estoreProject.shopping.entity.Cart;
import com.estore.estoreProject.shopping.entity.CartItem;
import com.estore.estoreProject.shopping.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import com.estore.estoreProject.shopping.dto.CartResponse;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<Cart> createCart(@PathVariable Long userId) {
        Cart cart = cartService.createCart(userId);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/latest/{userId}")
    public ResponseEntity<Cart> getLatestCartByUserId(@PathVariable Long userId) {
        Optional<Cart> cart = cartService.getLatestCartByUserId(userId);

        if (cart.isPresent()) {
            return ResponseEntity.ok(cart.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<CartItem> addProductToCart(@RequestParam Long cartId,
                                                     @RequestParam Long productId,
                                                     @RequestParam Integer quantity) {
        CartItem cartItem = cartService.addProductToCart(cartId, productId, quantity);
        return ResponseEntity.ok(cartItem);
    }

    @GetMapping("/{cartId}/items")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long cartId) {
        List<CartItem> items = cartService.getCartItems(cartId);
        return ResponseEntity.ok(items);
    }

    @PutMapping("/item/{cartItemId}")
    public ResponseEntity<CartItem> updateCartItemQuantity(@PathVariable Long cartItemId,
                                                           @RequestParam Integer quantity) {
        CartItem updatedItem = cartService.updateCartItemQuantity(cartItemId, quantity);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<String> removeCartItem(@PathVariable Long cartItemId) {
        cartService.removeCartItem(cartItemId);
        return ResponseEntity.ok("Ligne du panier supprimée avec succès");
    }

    @GetMapping("/{cartId}/total")
    public ResponseEntity<Double> calculateCartTotal(@PathVariable Long cartId) {
        Double total = cartService.calculateCartTotal(cartId);
        return ResponseEntity.ok(total);
    }

    @GetMapping("/{cartId}/details")
    public ResponseEntity<CartResponse> getCartDetails(@PathVariable Long cartId) {
        CartResponse cartResponse = cartService.getCartDetails(cartId);
        return ResponseEntity.ok(cartResponse);
    }
}
