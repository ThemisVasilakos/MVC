package gr.mindthecode.mvc.controller;

import gr.mindthecode.mvc.model.Product;
import gr.mindthecode.mvc.service.ProductService;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/index")
    public String greeting(
            @RequestParam(required = false) String productDescription,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam(defaultValue = "ASC", required = false) String sort,
            Model model
    ) {
        model.addAttribute("products", service.getProducts(productDescription, page, size, sort));
        model.addAttribute("sort", sort);
        model.addAttribute("productDescription ", productDescription );
        return "product";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("products", new Product());
        return "create-or-update-product";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("products",  service.getProductById(id));
        return "create-or-update-product";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        service.deleteProduct(id);
        return "redirect:/products/index";
    }

    @PostMapping("/create-or-update")
    public String saveCreateForm(@RequestParam Optional<Integer> id, @ModelAttribute Product product, Model model) {
        //System.out.println("id:" + id);
        //System.out.println("product id:" + product.getProductId());
        try {
            service.createOrUpdateProduct(id.isPresent() ? id.get() : null , product);
        } catch (Exception e) {
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400), e.getMessage());
        }

        return "redirect:/products/index";
    }
}
