package com.example.banlinhkien.controller;


import com.example.banlinhkien.models.ProductDTO;
import com.example.banlinhkien.other.ProcessUrlImage;
import com.example.banlinhkien.other.UpLoadFile;
import com.example.banlinhkien.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/QuanLySanPham/")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/DanhSach")
    public String listProduct(HttpServletRequest request) {
        List<ProductDTO> listProductDTOs = productService.getList();
        request.setAttribute("list", listProductDTOs);
        return "admin/product/list";
    }

    @GetMapping(value = "/ChiTiet/{id}")
    public String detailProduct(HttpServletRequest request, @PathVariable("id") int id) {
        ProductDTO productDTO = productService.getById(id);
        request.setAttribute("product", productDTO);
        return "admin/product/detail";
    }

    @GetMapping(value = "/Them")
    public String addProduct(Model model) {
        ProductDTO productDTO = new ProductDTO();
        model.addAttribute("product", productDTO);
        return "admin/product/formAdd";
    }

    @PostMapping(value = "/Them")
    public String addProduct(@ModelAttribute("product") ProductDTO productDTO, @RequestParam("file") MultipartFile file) {
        String urlImage = ProcessUrlImage.processUrlImage(file.getOriginalFilename(), String.valueOf(System.currentTimeMillis()));
        UpLoadFile.saveFile(file, urlImage);
        productDTO.setUrl_image(urlImage);
        productService.addOrUpDate(productDTO);
        System.out.print(productDTO);
        return "redirect:/admin/QuanLySanPham/DanhSach";
    }

    @GetMapping(value = "/Sua/{id}")
    public String updateProduct(Model model, @PathVariable("id") int id) {
        ProductDTO productDTO = productService.getById(id);
        model.addAttribute("product", productDTO);
        return "admin/product/formUpdate";
    }

    @PostMapping(value = "/Sua")
    public String updateProduct(@ModelAttribute("product") ProductDTO productDTO, @RequestParam("file") MultipartFile file) {
        String urlImage = ProcessUrlImage.processUrlImage(file.getOriginalFilename(), String.valueOf(System.currentTimeMillis()));
        ProductDTO productDTOCheck = productService.getById(productDTO.getId());
        if(StringUtils.hasText(urlImage)){
            UpLoadFile.deleteFile(productDTOCheck.getUrl_image());
            UpLoadFile.saveFile(file, urlImage);
            productDTO.setUrl_image(urlImage);
        }else{
            productDTO.setUrl_image(productDTOCheck.getUrl_image());
        }
        productService.addOrUpDate(productDTO);
        return "redirect:/admin/QuanLySanPham/DanhSach";
    }

    @GetMapping(value = "/Xoa/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        ProductDTO productDTO = productService.getById(id);
        UpLoadFile.deleteFile(productDTO.getUrl_image());
        productService.delete(id);
        return "redirect:/admin/QuanLySanPham/DanhSach";
    }

}
