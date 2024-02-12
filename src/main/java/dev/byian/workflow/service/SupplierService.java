package dev.byian.workflow.service;

import com.github.javafaker.Faker;
import dev.byian.workflow.dao.SupplierDao;
import dev.byian.workflow.domain.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class SupplierService {

    SupplierDao supplierDao;
    SupplierService(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }
    public ResponseEntity<Map<String, Object>> getAllSupplier(PageRequest pageRequest) {
        Map<String, Object> responseMap = new HashMap<>();
       try {
           Page<Supplier> supplier = supplierDao.findAll(pageRequest);
           if (supplier.isEmpty()) {
               responseMap.put("message", "No supplier found");
               responseMap.put("size", 0);
               return new ResponseEntity<>(responseMap,HttpStatus.NO_CONTENT);
           }
           responseMap.put("size", supplier.get().count());
           responseMap.put("data", supplier);
           return new ResponseEntity<>(responseMap, HttpStatus.OK);
       } catch (Exception e) {
           responseMap.put("message", e.getMessage());
           responseMap.put("status", HttpStatus.BAD_REQUEST.toString());
           return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
       }
    }

    public ResponseEntity<Map<String,Object>> addSupplier(Supplier supplier) {
        Map<String, Object> responseMap = new HashMap<>();
       try {
           supplierDao.save(supplier);
           responseMap.put("message", "Supplier " + supplier.getName() + " added successfully");
           responseMap.put("data", supplier);
           return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
       } catch (Exception e) {
           responseMap.put("message", e.getMessage());
           return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
       }
    }

    public ResponseEntity<Map<String,String>> deleteSupplier(UUID id) {
        Map<String, String> responseMap = new HashMap<>();
        try {
            supplierDao.deleteById(id);
            responseMap.put("message", "Supplier deleted successfully with ID of " +id);
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Map<String,Object>> updateSupplier(Supplier supplier) {
        Map<String,Object> responseMap = new HashMap<>();

        try {
            Optional<Supplier> supplierResponse =   supplierDao.findById(supplier.getId());
            if(supplierResponse.isEmpty()){
                responseMap.put("message", "No supplier found with ID " + supplier.getId());
                return new ResponseEntity<>(responseMap, HttpStatus.NO_CONTENT);
            }
            supplierResponse.get().update(supplier);
            supplierDao.save(supplierResponse.get());
            responseMap.put("message", "Supplier updated successfully with ID of " + supplier.getId());
            responseMap.put("data", supplierResponse.get());
            return new ResponseEntity<>(responseMap, HttpStatus.OK);

        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Map<String, Object>> getSupplierByName(String name) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            List<Supplier> suppliers = supplierDao.findByName(name);
            if (suppliers.isEmpty()) {
                responseMap.put("message", "No supplier found with name " + name);
                responseMap.put("size", 0);
                return new ResponseEntity<>(responseMap,HttpStatus.NO_CONTENT);
            }
            responseMap.put("size", suppliers.size());
            responseMap.put("data", suppliers);
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Map<String,Object>> getSupplierById(UUID id) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            Optional<Supplier> supplier = supplierDao.findById(id);
            if (supplier.isEmpty()) {
                responseMap.put("message", "No supplier found with ID " + id);
                responseMap.put("size", 0);
                return new ResponseEntity<>(responseMap,HttpStatus.NO_CONTENT);
            }
            responseMap.put("size", 1);
            responseMap.put("data", supplier.get());
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity <Map<String, Object>> generateFakeSupplier(int count){
        Map<String, Object> responseMap = new HashMap<>();
        List<Supplier> supplierList = new ArrayList<>();
        try {
            int savedCounter = 0;
            int duplicateCounter = 0;
            for(int i = 0; i < count; i++){
                Supplier supplier = new Supplier();
                Faker faker = new Faker();
                String supplierName = faker.company().name();
                List<Supplier> supplierResponse = supplierDao.findByName(supplierName);
                if(supplierResponse.isEmpty()){
                    supplier.setName(supplierName);
                    supplier.setAddress(faker.address().fullAddress());
                    supplier.setContactNumber(faker.phoneNumber().cellPhone());
                    supplier.setEmail(faker.internet().emailAddress());
                    supplierDao.save(supplier);
                    savedCounter++;
                    supplierList.add(supplier);
                }
                else{
                    duplicateCounter++;
                }

            }
            responseMap.put("message", savedCounter + " fake supplier added successfully with " + duplicateCounter + " duplicates");
            responseMap.put("data", supplierList);
            return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<Map<String, Object>> getSupplierByContactNumber(String contactNumber) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            List<Supplier> suppliers = supplierDao.findByContactNumber(contactNumber);
            if (suppliers.isEmpty()) {
                responseMap.put("message", "No supplier found with contact number " + contactNumber);
                responseMap.put("size", 0);
                return new ResponseEntity<>(responseMap,HttpStatus.NO_CONTENT);
            }
            responseMap.put("size", suppliers.size());
            responseMap.put("data", suppliers);
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Map<String, Object>> getSupplierByEmail(String email) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            List<Supplier> suppliers = supplierDao.findByEmail(email);
            if (suppliers.isEmpty()) {
                responseMap.put("message", "No supplier found with email " + email);
                responseMap.put("size", 0);
                return new ResponseEntity<>(responseMap,HttpStatus.NO_CONTENT);
            }
            responseMap.put("size", suppliers.size());
            responseMap.put("data", suppliers);
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Map<String, Object>> getSupplierByAddress(String address) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            List<Supplier> suppliers = supplierDao.findByAddress(address);
            if (suppliers.isEmpty()) {
                responseMap.put("message", "No supplier found with address " + address);
                responseMap.put("size", 0);
                return new ResponseEntity<>(responseMap,HttpStatus.NO_CONTENT);
            }
            responseMap.put("size", suppliers.size());
            responseMap.put("data", suppliers);
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }
}
