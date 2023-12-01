package com.example.banlinhkien.controller;


import com.example.banlinhkien.models.AccountDTO;
import com.example.banlinhkien.models.TableOrderDTO;
import com.example.banlinhkien.service.AccountService;
import com.example.banlinhkien.service.ProductService;
import com.example.banlinhkien.service.TableOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/QuanLyDonHang")
public class TableOrderController {

    @Autowired
    private TableOrderService tableOrderService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/DanhSach")
    public String list(HttpServletRequest request) {
        List<TableOrderDTO> tableOrderDTOs = tableOrderService.getListByActive(true);
        request.setAttribute("tableOrderDTOs", tableOrderDTOs);
        return "admin/tableorder/list";
    }

    @GetMapping(value = "/Xoa/{id}")
    public String delete(@PathVariable("id") int id) {
        tableOrderService.delete(id);
        return "redirect:/admin/QuanLyDonHang/DanhSach";
    }

    @GetMapping(value = "/GiaoHang/{id}")
    public String checked(@PathVariable("id") int id) {
        TableOrderDTO tableOrderDTO = tableOrderService.getById(id);
        tableOrderDTO.setChecked(true);
        tableOrderService.addOrUpdate(tableOrderDTO);
        return "redirect:/admin/QuanLyDonHang/DanhSach";
    }

    @GetMapping(value = "/revenue/all")
    public String revenue(HttpServletRequest request) {
        List<TableOrderDTO> tableOrderDTOs = tableOrderService.getListByActive(true);
        // account
        request.setAttribute("totalAccount", accountService.getList().size());
        // san pham
        request.setAttribute("totalProduct", productService.getList().size());
        // don hang
        request.setAttribute("totalOrder", tableOrderService.getListByActive(true).size() + tableOrderService.getListByActive(false).size());
        // all doanh thu
        Long totalRevenue = 0L;
        for(TableOrderDTO tableOrderDTO : tableOrderDTOs){
            if(tableOrderDTO.isChecked()){
                totalRevenue+=Long.valueOf(tableOrderDTO.getTotalMoney());
            }
        }
        request.setAttribute("totalRevenue", totalRevenue);
        request.setAttribute("tableOrderDTOs", tableOrderDTOs);
        return "admin/revenue/list";
    }
}
