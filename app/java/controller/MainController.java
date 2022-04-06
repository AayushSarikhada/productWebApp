package app.java.controller;

import app.java.dao.ProductDAO;
import app.java.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.*;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ProductDAO productDAO;


    @RequestMapping("/")
    public String home(Model model){

        List<Product> products = productDAO.getProducts();
        model.addAttribute("product",products);


        return "index";
    }

    //show added product form
    @RequestMapping("/add-product")
    public String addProduct(Model m){
        m.addAttribute("title","Add Product");
        return "add_product_form";
    }


    //handle add product form
    @RequestMapping(value = "/handle-product", method = RequestMethod.POST)
    public RedirectView handleProduct(@ModelAttribute Product product,HttpServletRequest request){
        productDAO.createProduct(product);
       return new RedirectView(request.getContextPath()+"/"); //redirect to home page
    }

    //delete handler
    @RequestMapping("/delete/{productId}")
    public RedirectView deleteProduct(@PathVariable("productId") int productId,HttpServletRequest request){
        System.out.println(productId);
        productDAO.deleteProduct(productId);
        return new RedirectView(request.getContextPath()+"/");  //redirect to home page
    }

    //update handler
    @RequestMapping("/update/{productId}")
    public String updateForm(@PathVariable("productId") int pid,Model model){
        Product product = productDAO.getProduct(pid);
        model.addAttribute("product",product);


        return "update_form";
    }


}
