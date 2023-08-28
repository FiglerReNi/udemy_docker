package com.example.applicationdockerfrommaven.controllers;

import com.example.applicationdockerfrommaven.converters.ProductToProductForm;
import com.example.applicationdockerfrommaven.domain.Product;
import com.example.applicationdockerfrommaven.domain.ProductForm;
import com.example.applicationdockerfrommaven.services.ProductService;
import hu.docker.pageviewmodel.model.PageViewEvent;
import hu.docker.pageviewservice.pageview.PageViewService;
import jakarta.validation.Valid;
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {


    @Autowired
    ProductToProductForm productToProductForm;

    @Autowired
    ProductService productService;

    @Autowired
    PageViewService pageViewService;

    @RequestMapping("/")
    public String redirToList() {
        return "redirect:/product/list";
    }

    @RequestMapping({"/product/list", "/product"})
    public String listProducts(Model model) {
        model.addAttribute("products", productService.listAll());

        PageViewEvent pageViewEvent = new PageViewEvent();
        pageViewEvent.setPageUrl("/product/list");
        pageViewEvent.setPageViewDate(new Date());
        pageViewEvent.setCorrelationId(UUID.randomUUID().toString());

        pageViewService.sendPageViewEvent(pageViewEvent);

        return "product/list";
    }

    @RequestMapping("/product/show/{id}")
    public String getProduct(@PathVariable String id, Model model) {
        model.addAttribute("product", productService.getById(id));
        return "product/show";
    }

    @RequestMapping("product/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Product product = productService.getById(id);
        ProductForm productForm = productToProductForm.convert(product);

        model.addAttribute("productForm", productForm);
        return "product/productform";
    }

    @RequestMapping("/product/new")
    public String newProduct(Model model) {
        model.addAttribute("productForm", new ProductForm());
        return "product/productform";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String saveOrUpdateProduct(@Valid ProductForm productForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "product/productform";
        }

        Product savedProduct = productService.saveOrUpdateProductForm(productForm);

        return "redirect:/product/show/" + savedProduct.getId();
    }

    @RequestMapping("/product/delete/{id}")
    public String delete(@PathVariable String id) {
        productService.delete(id);
        return "redirect:/product/list";
    }
}


