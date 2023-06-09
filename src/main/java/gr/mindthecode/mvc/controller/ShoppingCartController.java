package gr.mindthecode.mvc.controller;

import gr.mindthecode.mvc.dto.NewOrderDto;
import gr.mindthecode.mvc.service.OrderService;
import gr.mindthecode.mvc.service.ProductService;
import gr.mindthecode.mvc.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class ShoppingCartController {

    private ShoppingCartService shoppingCartService;
    private ProductService productService;
    private OrderService orderService;

    public ShoppingCartController(ShoppingCartService shoppingCartService,ProductService productService,
                                  OrderService orderService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
        this.orderService=orderService;
    }

    @GetMapping("/new")
    public String getItems(
            @RequestParam(required = false) String productDescription,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "ASC", required = false) String sort,
            Model model
    ) {
        model.addAttribute("products", productService.getProducts(productDescription, page, size, sort));
        model.addAttribute("sort", sort);
        model.addAttribute("productDescription ", productDescription );

        model.addAttribute("address",new  NewOrderDto());

        return "order";
    }

    @GetMapping("/add/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("products",  productService.getProductById(id));
        model.addAttribute("address",new  NewOrderDto());

        shoppingCartService.addProductQuantity(productService.getProductById(id).getProductId());

        return "redirect:/order/new";
    }

    @PostMapping("/create")
    public String createOrder(@ModelAttribute NewOrderDto newOrderDto ,Model model) throws Exception {
        model.addAttribute("address",new NewOrderDto());

        shoppingCartService.buildNewOrderDto(newOrderDto.getAddress(),shoppingCartService.getProductQuantity());
        model.addAttribute("order",shoppingCartService.createOrder(shoppingCartService.getNewOrderDto()));

        model.addAttribute("orders", orderService.findAll());

        shoppingCartService.reset();

        return "redirect:/order/new";
    }

    @GetMapping("/all")
    public String getOrders(
            @RequestParam(required = false) String address,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "ASC", required = false) String sort,
            Model model
    ) {
        model.addAttribute("orders",orderService.getOrders(address,page,size,sort));
        model.addAttribute("sort", sort);
        model.addAttribute("address ", address );

        return "cart";
    }


}
