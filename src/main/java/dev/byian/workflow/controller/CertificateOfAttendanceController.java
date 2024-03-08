package dev.byian.workflow.controller;

import dev.byian.workflow.domain.CertificateOfAttendance;
import dev.byian.workflow.service.CertificateOfAttendanceService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/request/coa")
public class CertificateOfAttendanceController {
    CertificateOfAttendanceService certificateOfAttendanceService;
    CertificateOfAttendanceController(CertificateOfAttendanceService certificateOfAttendanceService){
        this.certificateOfAttendanceService = certificateOfAttendanceService;
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String,Object>> getAllCertificateOfAttendances(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return certificateOfAttendanceService.getAllCertificateOfAttendance(pageRequest);
    }

    @GetMapping(value = "/find", params = "id")
    public ResponseEntity<Map<String,Object>> getCertificateOfAttendanceById(@RequestParam("id") UUID id) {
        return certificateOfAttendanceService.getCertificateOfAttendanceById(id);
    }

    @DeleteMapping(value = "/delete", params = "id")
    public ResponseEntity<Map<String,String>> deleteCertificateOfAttendance(@RequestParam("id") UUID id) {
        return certificateOfAttendanceService.deleteCertificateOfAttendance(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String,Object>> addCertificateOfAttendance(@RequestBody CertificateOfAttendance certificateOfAttendance) {
        return certificateOfAttendanceService.addCertificateOfAttendance(certificateOfAttendance);
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateCertificateOfAttendance(@RequestBody CertificateOfAttendance certificateOfAttendance) {
        return certificateOfAttendanceService.updateCertificateOfAttendance(certificateOfAttendance);
    }

    @GetMapping(value = "/generate", params = "count")
    public ResponseEntity <Map<String, Object>> generateCertificateOfAttendances(@RequestParam("count") int count) {
        return certificateOfAttendanceService.generateFakeCertificateOfAttendance(count);
    }

}
