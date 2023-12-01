package com.example.banlinhkien.controller;


import com.example.banlinhkien.models.TableOrderDTO;
import com.example.banlinhkien.service.TableOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/admin/download")
public class DownloadFileController {

    @Autowired
    private TableOrderService tableOrderService;

    @GetMapping("file")
    public String exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
        List<TableOrderDTO> tableOrderDTOs = tableOrderService.getListByActive(true);
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=reportRevenue_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);


        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Code orders", "Amount", "Date"};
        String[] nameMapping = {"name", "totalMoney", "date"};
        csvWriter.writeHeader(csvHeader);
        for (TableOrderDTO tableOrderDTO : tableOrderDTOs) {
            if(tableOrderDTO.isChecked()){
                csvWriter.write(tableOrderDTO, nameMapping);
            }
        }

        csvWriter.close();
        return "redirect:/admin/QuanLyDonHang/revenue/all";
    }
}
