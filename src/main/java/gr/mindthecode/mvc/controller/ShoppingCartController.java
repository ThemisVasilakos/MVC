package gr.mindthecode.mvc.controller;

import gr.mindthecode.mvc.dto.NewOrderDto;
import gr.mindthecode.mvc.service.ShoppingCartService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class ShoppingCartController {

    private ShoppingCartService service;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.service = shoppingCartService;
    }

    @GetMapping("/all")
    public String orders(Model model){

        model.addAttribute("order", service.findAll());
        return "order";
    }

    @PostMapping("/create")
    public String createOrder(@RequestBody NewOrderDto newOrderDto, Model model) throws Exception {
        model.addAttribute("order",service.createOrder(newOrderDto));
        return "redirect:/order/all";
    }


}
