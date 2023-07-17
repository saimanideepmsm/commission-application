package com.example.springassignmento.Controller;

import com.fasterxml.jackson.core.type.TypeReference;

import com.example.springassignmento.Commission;
import com.example.springassignmento.Product;
import com.example.springassignmento.Repository.CommissionRepository;
import com.example.springassignmento.Repository.ProductRepository;
import com.example.springassignmento.Repository.SalesmanRepository;
import com.example.springassignmento.Sales;
import com.example.springassignmento.Salesman;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class SalesController
{
    @Autowired
    private CommissionRepository commissionRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SalesmanRepository salesmanRepository;

    @PostMapping("/sales")
    public ResponseEntity<List<Commission>> computeSalesAndSaveToDB(
            @RequestParam("sales") MultipartFile salesFile,
            @RequestParam("products") MultipartFile productsFile) throws IOException {

        List<Salesman> salesmans = readSalesmanFromFile(salesFile);
        List<Product> products = readProductsFromFile(productsFile);
        TreeMap<Long,Salesman> salesmanHashMap = new TreeMap<>();
        for(Salesman sm:salesmans){
            salesmanHashMap.put(sm.getSalesmanId(),sm);
        }
        List<Commission> commissions = new ArrayList<>();
        for (Product product : products) {
            Salesman salesman = salesmanHashMap.get(product.getSalesmanId());
            salesmanRepository.save(salesman);
            Double salesAmount = product.getQuantity() * product.getMrpperUnit();
            Integer commissionAmount = (int)(salesAmount * (salesman.getCommissionRate() / 100.0));
            Commission commission = new Commission();
            commission.setProductName(product.getProductName());
            commission.setSalesmanName(salesman.getSalesmanName());
            commission.setQuantity(product.getQuantity());
            commission.setSalesAmount(salesAmount);
            commission.setSalesmanArea(salesman.getSalesmanArea());
            commission.setSalesmanCommission((double)commissionAmount);
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String strDate= formatter.format(date);
            commission.setCreatedDate(strDate);
            commissions.add(commission);}

        commissionRepository.saveAll(commissions);
        return new ResponseEntity<>(commissions, HttpStatus.OK);
    }


    private List<Salesman> readSalesmanFromFile(MultipartFile file) throws  IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        try(InputStream inputStream = file.getInputStream()){
            return objectMapper.readValue(inputStream, new TypeReference<List<Salesman>>() {
            });
        }
    }
    private List<Product> readProductsFromFile(MultipartFile file) throws  IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        try(InputStream inputStream = file.getInputStream()){
            return objectMapper.readValue(inputStream, new TypeReference<List<Product>>() {
            });
        }
    }

//    @PostMapping("/sales")
//    public ResponseEntity<List<Commission>> computeSalesAndSaveToDB(@RequestBody Sales s) {
//        List<Product> products = s.getProducts();
//        List<Salesman> salesmans = s.getSalesman();
//        TreeMap<Long,Salesman> salesmanHashMap = new TreeMap<>();
//        for(Salesman sm:salesmans){
//            salesmanHashMap.put(sm.getSalesmanId(),sm);
//        }
//        List<Commission> commissions = new ArrayList<>();
//        for (Product product : products) {
//
//            Salesman salesman = salesmanHashMap.get(product.getSalesmanId());
//            salesmanRepository.save(salesman);
//            productRepository.save(product);
//
//            Double salesAmount = product.getQuantity() * product.getMrpperUnit();
//            Double commissionAmount = salesAmount * (salesman.getCommissionRate() / 100.0);
//
//            Commission commission = new Commission();
//            commission.setProductName(product.getProductName());
//            commission.setSalesmanName(salesman.getSalesmanName());
//            commission.setQuantity(product.getQuantity());
//            commission.setSalesAmount(salesAmount);
//            commission.setSalesmanArea(salesman.getSalesmanArea());
//            commission.setSalesmanCommission(commissionAmount);
//            Date date = new Date();
//            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//            String strDate= formatter.format(date);
//            commission.setCreatedDate(strDate);
//            commissions.add(commission);}
//
//        commissionRepository.saveAll(commissions);
//        return new ResponseEntity<>(commissions, HttpStatus.OK);
//    }

    @GetMapping("/sales")
    public ResponseEntity<List<Commission>> getAllCommissions() {
        List<Commission> sales = commissionRepository.findAll();
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }
    @GetMapping("/sales/date")
    public ResponseEntity<List<Commission>> getAllCommissionsByDate(@RequestParam("date")String date) {
        List<Commission> sales = commissionRepository.findByCreatedDateIs(date);
        return new ResponseEntity<>(sales, HttpStatus.OK);
    }
}