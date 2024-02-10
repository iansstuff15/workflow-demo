package dev.byian.workflow.controller;

import dev.byian.workflow.domain.Supplier;
import dev.byian.workflow.domain.Supplier;
import dev.byian.workflow.service.SupplierService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    SupplierService supplierService;
    SupplierController(SupplierService supplierService){
        this.supplierService = supplierService;
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> getAllSuppliers(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return supplierService.getAllSupplier(pageRequest);
    }

    @GetMapping(value = "/find", params = "id")
    public ResponseEntity<Map<String,Object>> getSupplierById(@RequestParam("id") UUID id) {
        return supplierService.getSupplierById(id);
    }

    @GetMapping(value = "/find",params = "name")
    public ResponseEntity<Map<String, Object>> getSupplierByName(@RequestParam("name") String name) {
        return supplierService.getSupplierByName(name);
    }

    @GetMapping(value = "/find",params = "contactNumber")
    public ResponseEntity<Map<String, Object>> getSupplierByContactNumber(@RequestParam("contactNumber") String contactNumber) {
        return supplierService.getSupplierByContactNumber(contactNumber);
    }

    @GetMapping(value = "/find",params = "email")
    public ResponseEntity<Map<String, Object>> getSupplierByEmail(@RequestParam("email") String email) {
        return supplierService.getSupplierByEmail(email);
    }

    @GetMapping(value = "/find",params = "address")
    public ResponseEntity<Map<String, Object>> getSupplierByAddress(@RequestParam("address") String address) {
        return supplierService.getSupplierByAddress(address);
    }

    @DeleteMapping(value = "/delete", params = "id")
    public ResponseEntity<Map<String,String>> deleteSupplier(@RequestParam("id") UUID id) {
        return supplierService.deleteSupplier(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String,Object>> addSupplier(@RequestBody Supplier supplier) {
        return supplierService.addSupplier(supplier);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateSupplier(@RequestBody Supplier supplier) {
        return supplierService.updateSupplier(supplier);
    }

    @GetMapping(value = "/generate", params = "count")
    public ResponseEntity <Map<String, String>> generateSuppliers(@RequestParam("count") int count) {
        return supplierService.generateFakeSupplier(count);
    }

}
