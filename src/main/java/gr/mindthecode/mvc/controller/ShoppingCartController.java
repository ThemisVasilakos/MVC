package gr.mindthecode.mvc.controller;

import gr.mindthecode.mvc.dto.NewOrderDto;
import gr.mindthecode.mvc.service.ProductService;
import gr.mindthecode.mvc.service.ShoppingCartService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;
    private ProductService productService;

    public ShoppingCartController(ShoppingCartService shoppingCartService,ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    @GetMapping("/new")
    public String getItems(
            @RequestParam(required = false) Double productPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "ASC", required = false) String sort,
            Model model
    ) {
        model.addAttribute("products", productService.getProducts(productPrice, page, size, sort));
        model.addAttribute("sort", sort);
        model.addAttribute("productDescription ", productPrice );
        return "order";
    }

    @PostMapping("/create")
    public String createOrder(@RequestBody NewOrderDto newOrderDto, Model model) throws Exception {
        model.addAttribute("order",shoppingCartService.createOrder(newOrderDto));
        return "redirect:/order/all";
    }


}
