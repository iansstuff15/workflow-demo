package dev.byian.workflow.service;

import com.github.javafaker.Faker;
import dev.byian.workflow.dao.CertificateOfAttendanceDao;
import dev.byian.workflow.domain.CertificateOfAttendance;
import dev.byian.workflow.domain.CertificateOfAttendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class CertificateOfAttendanceService {

    CertificateOfAttendanceDao certificateOfAttendanceDao;
    CertificateOfAttendanceService(CertificateOfAttendanceDao certificateOfAttendanceDao) {
        this.certificateOfAttendanceDao = certificateOfAttendanceDao;
    }
    public ResponseEntity<Map<String, Object>> getAllCertificateOfAttendance(PageRequest pageRequest) {
        Map<String, Object> responseMap = new HashMap<>();
       try {
           Page<CertificateOfAttendance> certificateOfAttendances = certificateOfAttendanceDao.findAll(pageRequest);
           if (certificateOfAttendances.isEmpty()) {
               responseMap.put("message", "No certificate of attendance found");
               responseMap.put("size", 0);
               return new ResponseEntity<>(responseMap,HttpStatus.NO_CONTENT);
           }
           responseMap.put("size", certificateOfAttendances.get().count());
           responseMap.put("data", certificateOfAttendances);
           return new ResponseEntity<>(responseMap, HttpStatus.OK);
       } catch (Exception e) {
           responseMap.put("message", e.getMessage());
           responseMap.put("status", HttpStatus.BAD_REQUEST.toString());
           return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
       }
    }

    public ResponseEntity<Map<String,Object>> addCertificateOfAttendance(CertificateOfAttendance certificateOfAttendance) {
        Map<String, Object> responseMap = new HashMap<>();
       try {
           certificateOfAttendanceDao.save(certificateOfAttendance);
           responseMap.put("message", "Certificate of attendance request " + certificateOfAttendance.getId() + " added successfully");
           responseMap.put("data", certificateOfAttendance);
           return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
       } catch (Exception e) {
           responseMap.put("message", e.getMessage());
           return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
       }
    }

    public ResponseEntity<Map<String,String>> deleteCertificateOfAttendance(UUID id) {
        Map<String, String> responseMap = new HashMap<>();
        try {
            certificateOfAttendanceDao.deleteById(id);
            responseMap.put("message", "Certificate of attendance  deleted successfully with ID of " +id);
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Map<String,Object>> updateCertificateOfAttendance(CertificateOfAttendance certificateOfAttendance) {
        Map<String,Object> responseMap = new HashMap<>();

        try {
            Optional<CertificateOfAttendance> supplierResponse =   certificateOfAttendanceDao.findById(certificateOfAttendance.getId());
            if(supplierResponse.isEmpty()){
                responseMap.put("message", "No certificateOfAttendance found with ID " + certificateOfAttendance.getId());
                return new ResponseEntity<>(responseMap, HttpStatus.NO_CONTENT);
            }
            supplierResponse.get().update(certificateOfAttendance);
            certificateOfAttendanceDao.save(supplierResponse.get());
            responseMap.put("message", "CertificateOfAttendance updated successfully with ID of " + certificateOfAttendance.getId());
            responseMap.put("data", supplierResponse.get());
            return new ResponseEntity<>(responseMap, HttpStatus.OK);

        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Map<String,Object>> getCertificateOfAttendanceById(UUID id) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            Optional<CertificateOfAttendance> certificateOfAttendance = certificateOfAttendanceDao.findById(id);
            if (certificateOfAttendance.isEmpty()) {
                responseMap.put("message", "No certificate of attendance found with ID " + id);
                responseMap.put("size", 0);
                return new ResponseEntity<>(responseMap,HttpStatus.NO_CONTENT);
            }
            responseMap.put("size", 1);
            responseMap.put("data", certificateOfAttendance.get());
            return new ResponseEntity<>(responseMap, HttpStatus.OK);
        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity <Map<String, Object>> generateFakeCertificateOfAttendance(int count){
        Map<String, Object> responseMap = new HashMap<>();
        List<CertificateOfAttendance> certificateOfAttendances = new ArrayList<>();
        try {
            int savedCounter = 0;
            int duplicateCounter = 0;
            for(int i = 0; i < count; i++){
                CertificateOfAttendance certificateOfAttendance = new CertificateOfAttendance();
                Faker faker = new Faker();
                Date startDate = faker.date().future(1, TimeUnit.DAYS);
                Date endDate = faker.date().future(1, TimeUnit.DAYS, startDate);

                certificateOfAttendance.setStartDate(startDate);
                certificateOfAttendance.setEndDate(endDate);
                certificateOfAttendance.setReason(faker.lorem().sentence());

            }
            responseMap.put("message", savedCounter + " fake supplier added successfully with " + duplicateCounter + " duplicates");
            responseMap.put("data", certificateOfAttendances);
            return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
        } catch (Exception e) {
            responseMap.put("message", e.getMessage());
            return new ResponseEntity<>(responseMap, HttpStatus.BAD_REQUEST);
        }
    }





}
