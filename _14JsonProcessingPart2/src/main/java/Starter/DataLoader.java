package Starter;

import Starter.services.DataImportService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final DataImportService dataImportService;

    public DataLoader(DataImportService dataImportService) {
        this.dataImportService = dataImportService;
    }

    @Override
    public void run(String... args) throws Exception {

        // 5 Car Dealer Import Data
        dataImportService.importSuppliers();
        dataImportService.importParts();
        dataImportService.importCars();
        dataImportService.importCustomers();
        dataImportService.importSales();

        // 6 Car Dealer Query and Export Data
        // Query 1 – Ordered Customers
        dataImportService.exportOrderedData();

        // Query 2 – Cars from Make Toyota
        dataImportService.exportCarsFromMakeToyota();

        // Query 3 – Local Suppliers
        dataImportService.exportLocalSuppliers();
    }
}
