package com.codewithisa.aplikasitiketbioskop.controller;

import com.codewithisa.aplikasitiketbioskop.service.InvoiceService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;
    @PostMapping("generate-tiket")
    public void generateTiket(HttpServletResponse response){
        try{
            JasperReport sourceFileName = JasperCompileManager.compileReport(
                    ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX+"tiket.jrxml")
                            .getAbsolutePath()
            );
            List<Map<String,String>> dataPemesan = new ArrayList<>();
            Map<String, String> pemesan = new HashMap<>();
            pemesan.put("filmName", "Robot");
            pemesan.put("tanggalTayang", "18 Nov 2022");
            pemesan.put("jamMulai", "20.00 WIB");
            pemesan.put("studioName", "A");
            pemesan.put("nomorKursi", "A4");
            pemesan.put("hargaTiket", "50.000");
            pemesan.put("username", "keong");
            pemesan.put("emailAddress", "keong@yahoo.com");
            dataPemesan.add(pemesan);

            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataPemesan);
            Map<String,Object> parameters = new HashMap<>();

            JasperPrint jasperPrint = JasperFillManager.fillReport(sourceFileName, parameters, beanColDataSource);

            response.setContentType("application/pdf");
            response.addHeader("Content-Disposition", "inline; filename=tiket.pdf;");

            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
