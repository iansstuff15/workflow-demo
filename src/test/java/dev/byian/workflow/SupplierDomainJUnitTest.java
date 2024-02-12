package dev.byian.workflow;

import dev.byian.workflow.domain.Department;
import dev.byian.workflow.domain.Employee;
import dev.byian.workflow.domain.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class SupplierDomainJUnitTest {
    @Test
    void shouldBeAbleToSetSupplierName() {
        Supplier supplier = new Supplier();
        supplier.setName("Supplier 1");
        Assertions.assertEquals("Supplier 1", supplier.getName());
    }
    @Test
    void shouldBeAbleToSetSupplierAddress() {
        Supplier supplier = new Supplier();
        supplier.setAddress("Supplier 1 Address");
        Assertions.assertEquals("Supplier 1 Address", supplier.getAddress());
    }
    @Test
    void shouldBeAbleToSetSupplierContactNumber() {
        Supplier supplier = new Supplier();
        supplier.setContactNumber("1234567890");
        Assertions.assertEquals("1234567890", supplier.getContactNumber());
    }
    @Test
    void shouldBeAbleToSetSupplierEmail() {
        Supplier supplier = new Supplier();
        supplier.setEmail("test@email.com");
        Assertions.assertEquals("test@email.com", supplier.getEmail());
    }
    @Test
    void shouldBeAbleToUpdateSupplierWithNewSupplier() {
        Supplier supplier = new Supplier();
        supplier.setName("Supplier 1");
        supplier.setAddress("Supplier 1 Address");
        supplier.setContactNumber("1234567890");
        supplier.setEmail("test@email.com:");
        Supplier newSupplier = new Supplier();
        newSupplier.setName("Supplier 2");
        newSupplier.setAddress("Supplier 2 Address");
        newSupplier.setContactNumber("0987654321");
        newSupplier.setEmail("test2@email.com");
        supplier.update(newSupplier);
        Assertions.assertEquals("Supplier 2", supplier.getName());
        Assertions.assertEquals("Supplier 2 Address", supplier.getAddress());
        Assertions.assertEquals("0987654321", supplier.getContactNumber());
        Assertions.assertEquals("test2@email.com", supplier.getEmail());
    }
    @Test
    void shouldUpdateSupplierNameFromSupplier1ToSupplier2() {
        Supplier supplier = new Supplier();
        supplier.setName("Supplier 1");
        supplier.update(Supplier.builder().name("Supplier 2").build());
        String name = supplier.getName();
        Assertions.assertEquals("Supplier 2", name);
    }
    @Test
    void shouldUpdateSupplierAddressFromSupplier1AddressToSupplier2Address() {
        Supplier supplier = new Supplier();
        supplier.setAddress("Supplier 1 Address");
        supplier.update(Supplier.builder().address("Supplier 2 Address").build());
        String address = supplier.getAddress();
        Assertions.assertEquals("Supplier 2 Address", address);
    }
    @Test
    void shouldUpdateSupplierEmailFromSupplier1EmailToSupplier2Email() {
        Supplier supplier = new Supplier();
        supplier.setEmail("test@email.com");
        supplier.update(Supplier.builder().email("test2@email.com").build());
    }
    @Test
    void shouldUpdateSupplierContactNumberFromSupplier1ContactNumberToSupplier2ContactNumber() {
        Supplier supplier = new Supplier();
        supplier.setContactNumber("1234567890");
        supplier.update(Supplier.builder().contactNumber("0987654321").build());
        String contactNumber = supplier.getContactNumber();
        Assertions.assertEquals("0987654321", contactNumber);
    }
    @Test
    void shouldBeAbleToProvideSupplierId() {
        Supplier supplier = new Supplier();
        supplier.setId(UUID.fromString("e0defa9c-1389-46e6-a9c1-73aaa5fe53d1"));
        Assertions.assertEquals(UUID.fromString("e0defa9c-1389-46e6-a9c1-73aaa5fe53d1"), supplier.getId());
    }

}
