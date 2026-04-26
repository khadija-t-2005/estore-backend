package com.estore.estoreProject.shopping.service;

import com.estore.estoreProject.catalog.entity.Product;
import com.estore.estoreProject.catalog.repository.ProductRepository;
import com.estore.estoreProject.customer.entity.User;
import com.estore.estoreProject.customer.repository.UserRepository;
import com.estore.estoreProject.exception.ResourceNotFoundException;
import com.estore.estoreProject.exception.StockInsufficientException;
import com.estore.estoreProject.inventory.entity.Inventory;
import com.estore.estoreProject.inventory.repository.InventoryRepository;
import com.estore.estoreProject.shopping.dto.CartItemResponse;
import com.estore.estoreProject.shopping.dto.CartResponse;
import com.estore.estoreProject.shopping.entity.Cart;
import com.estore.estoreProject.shopping.entity.CartItem;
import com.estore.estoreProject.shopping.repository.CartItemRepository;
import com.estore.estoreProject.shopping.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    // ✅ AJOUT : repository Inventory pour vérifier le stock
    private final InventoryRepository inventoryRepository;

    public CartService(CartRepository cartRepository,
                       CartItemRepository cartItemRepository,
                       UserRepository userRepository,
                       ProductRepository productRepository,
                       InventoryRepository inventoryRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public Cart createCart(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new ResourceNotFoundException("Utilisateur introuvable");
        }

        Cart cart = new Cart();
        cart.setCreatedAt(LocalDateTime.now());
        cart.setUser(userOptional.get());

        return cartRepository.save(cart);
    }

    public Optional<Cart> getLatestCartByUserId(Long userId) {
        return cartRepository.findTopByUserIdOrderByCreatedAtDesc(userId);
    }

    public CartItem addProductToCart(Long cartId, Long productId, Integer quantity) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (!cartOptional.isPresent()) {
            throw new ResourceNotFoundException("Panier introuvable");
        }

        Optional<Product> productOptional = productRepository.findById(productId);
        if (!productOptional.isPresent()) {
            throw new ResourceNotFoundException("Produit introuvable");
        }

        // ✅ AJOUT : vérification du stock avant d'ajouter au panier
        Optional<Inventory> inventoryOptional = inventoryRepository.findByProductId(productId);
        if (!inventoryOptional.isPresent()) {
            throw new ResourceNotFoundException("Stock introuvable pour ce produit");
        }

        Inventory inventory = inventoryOptional.get();
        if (inventory.getQuantity() < quantity) {
            throw new StockInsufficientException(
                    "Stock insuffisant. Disponible : " + inventory.getQuantity() + ", demandé : " + quantity
            );
        }

        Cart cart = cartOptional.get();
        Product product = productOptional.get();

        Optional<CartItem> existingItem = cartItemRepository.findByCartIdAndProductId(cartId, productId);

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            int newQty = item.getQuantity() + quantity;
            // ✅ AJOUT : vérification aussi lors d'une mise à jour de quantité existante
            if (inventory.getQuantity() < newQty) {
                throw new StockInsufficientException(
                        "Stock insuffisant. Disponible : " + inventory.getQuantity() + ", demandé : " + newQty
                );
            }
            item.setQuantity(newQty);
            return cartItemRepository.save(item);
        }

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        cartItem.setUnitPrice(product.getPrice());

        return cartItemRepository.save(cartItem);
    }

    public List<CartItem> getCartItems(Long cartId) {
        return cartItemRepository.findByCartId(cartId);
    }

    public CartItem updateCartItemQuantity(Long cartItemId, Integer quantity) {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if (!cartItemOptional.isPresent()) {
            throw new ResourceNotFoundException("Ligne du panier introuvable");
        }

        CartItem cartItem = cartItemOptional.get();
        cartItem.setQuantity(quantity);
        return cartItemRepository.save(cartItem);
    }

    public void removeCartItem(Long cartItemId) {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if (!cartItemOptional.isPresent()) {
            throw new ResourceNotFoundException("Ligne du panier introuvable");
        }
        cartItemRepository.deleteById(cartItemId);
    }

    public Double calculateCartTotal(Long cartId) {
        List<CartItem> items = cartItemRepository.findByCartId(cartId);
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getUnitPrice() * item.getQuantity();
        }
        return total;
    }

    public CartResponse getCartDetails(Long cartId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if (!cartOptional.isPresent()) {
            throw new ResourceNotFoundException("Panier introuvable");
        }

        Cart cart = cartOptional.get();
        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);

        List<CartItemResponse> itemResponses = new ArrayList<>();
        double total = 0.0;

        for (CartItem item : cartItems) {
            CartItemResponse itemResponse = new CartItemResponse(
                    item.getProduct().getId(),
                    item.getProduct().getName(),
                    item.getQuantity(),
                    item.getUnitPrice()
            );
            itemResponses.add(itemResponse);
            total += item.getUnitPrice() * item.getQuantity();
        }

        CartResponse cartResponse = new CartResponse();
        cartResponse.setId(cart.getId());
        cartResponse.setUserId(cart.getUser().getId());
        cartResponse.setItems(itemResponses);
        cartResponse.setTotal(total);

        return cartResponse;
    }
}
