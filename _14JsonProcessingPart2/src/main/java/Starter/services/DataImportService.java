package Starter.services;

import Starter.DTOs.*;
import Starter.JsonUtil;
import Starter.entities.Car;
import Starter.entities.Customer;
import Starter.entities.Part;
import Starter.entities.Sale;
import Starter.repositories.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import Starter.entities.Supplier;


@Service
public class DataImportService {

    private final SupplierRepository supplierRepo;
    private final PartRepository partRepo;
    private final CarRepository carRepo;
    private final CustomerRepository customerRepo;
    private final SaleRepository saleRepo;
    private final JsonUtil jsonUtil;

    private final Random random = new Random();

    public DataImportService(
        SupplierRepository supplierRepo,
        PartRepository partRepo,
        CarRepository carRepo,
        CustomerRepository customerRepo,
        SaleRepository saleRepo,
        JsonUtil jsonUtil) {

            this.supplierRepo = supplierRepo;
            this.partRepo = partRepo;
            this.carRepo = carRepo;
            this.customerRepo = customerRepo;
            this.saleRepo = saleRepo;
            this.jsonUtil = jsonUtil;
    }


    // 1 Import Suppliers
    public void importSuppliers() throws Exception {

        InputStream buff = getClass().getResourceAsStream("/files/suppliers.json");
        SupplierDTO[] suppDTO = jsonUtil.fromJson(buff, SupplierDTO[].class);

        for (SupplierDTO data : suppDTO) {
            Supplier supplier = new Supplier();

            supplier.setName(data.getName());
            supplier.setUsesImportedParts(data.getIsImporter());

            supplierRepo.save(supplier);
        }
    }

    // 2 Import Parts (random supplier)
    public void importParts() throws Exception {

        InputStream is = getClass().getResourceAsStream("/files/parts.json");
        PartDTO[] parts = jsonUtil.fromJson(is, PartDTO[].class);

        List<Supplier> suppliers = supplierRepo.findAll();

        for (PartDTO d : parts) {
            Part part = new Part();

            part.setName(d.getName());
            part.setPrice(d.getPrice());
            part.setQuantity(d.getQuantity());

            Supplier randomSupplier = suppliers.get(random.nextInt(suppliers.size()));
            part.setSupplier(randomSupplier);

            partRepo.save(part);
        }
    }

    // 3 Import Cars (3-5 random parts)
    public void importCars() throws Exception {

        InputStream buff = getClass().getResourceAsStream("/files/cars.json");
        CarDTO[] cars = jsonUtil.fromJson(buff, CarDTO[].class);

        List<Part> allParts = partRepo.findAll();

        for (CarDTO data : cars) {
            Car car = new Car();

            car.setMake(data.getMake());
            car.setModel(data.getModel());
            car.setTraveledDistance(data.getTravelledDistance());

            int partsCount = 3 + random.nextInt(3); // 3-5

            Set<Part> randomParts = new HashSet<>();

            for (int pos = 0; pos < partsCount; pos++) {

                Part part = allParts.get(random.nextInt(allParts.size()));
                randomParts.add(part);
            }

            car.setParts(randomParts);
            carRepo.save(car);
        }
    }

    // 4 Import Customers
    public void importCustomers() throws Exception {

        InputStream buff = getClass().getResourceAsStream("/files/customers.json");
        CustomerDTO[] data = jsonUtil.fromJson(buff, CustomerDTO[].class);

        for (CustomerDTO d : data) {
            Customer customer = new Customer();

            customer.setName(d.getName());
            customer.setBirthDate(d.getBirthDate().atStartOfDay());
            customer.setIsYoungDriver(d.getIsYoungDriver());

            customerRepo.save(customer);
        }
    }

    // 5 Import Sales (random car, customer, discount)
    public void importSales() {

        List<Car> cars = carRepo.findAll();
        List<Customer> customers = customerRepo.findAll();

        int[] discounts = {0, 5, 10, 15, 20, 30, 40, 50};

        for (int pos = 0; pos < 50; pos++) { // пример: 50 sales
            Sale sale = new Sale();

            sale.setCar(cars.get(random.nextInt(cars.size())));
            sale.setCustomer(customers.get(random.nextInt(customers.size())));
            sale.setDiscountPercentage(discounts[random.nextInt(discounts.length)]);

            saleRepo.save(sale);
        }
    }

    // 6 Car Dealer Query and Export Data
    // Query 1 – Ordered Customers
    public void exportOrderedData() throws Exception{
        List<Customer> customers = customerRepo.findAllOrdered();

        List<OrderedCustomerDTO> dataList = customers.stream().map(c -> {
            OrderedCustomerDTO data = new OrderedCustomerDTO();

            data.setId(c.getId());
            data.setName(c.getName());
            data.setBirthDate(c.getBirthDate());
            data.setYoungDriver(c.getIsYoungDriver());

            data.setSales(List.<Object>of()); // EMPTY LIST

            return data;

        }).collect(Collectors.toList());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        String json = mapper
            .writerWithDefaultPrettyPrinter()
            .writeValueAsString(dataList);

        Files.writeString(Path.of("src/main/resources/files/ordered-customers.json"), json);
//        System.out.println(json);
    }

    // Query 2 – Cars from Make Toyota

    public void exportCarsFromMakeToyota() throws Exception {

        List<ToyotaCarDTO> carsList = carRepo.findAllToyotaCars().stream()
            .map(car -> {
                ToyotaCarDTO data = new ToyotaCarDTO();

                data.setId(car.getId());
                data.setMake(car.getMake());
                data.setModel(car.getModel());
                data.setTraveledDistance(car.getTraveledDistance());

                return data;
            }).collect(Collectors.toList());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        String json = mapper
            .writerWithDefaultPrettyPrinter()
            .writeValueAsString(carsList);

        Files.writeString(Path.of("src/main/resources/files/toyota-cars.json"), json);
//        System.out.println(json);
    }

    // Query 3 – Local Suppliers
    public void exportLocalSuppliers() throws Exception {

        List<LocalSupplierDTO> suppliers = supplierRepo.findAllLocalSuppliers()
            .stream()
            .map(s -> {
                LocalSupplierDTO data = new LocalSupplierDTO();

                data.setId(s.getId());
                data.setName(s.getName());
                data.setPartsCount(s.getParts().size());

                return data;
            }).collect(Collectors.toList());

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        String json = mapper
            .writerWithDefaultPrettyPrinter()
            .writeValueAsString(suppliers);

        Files.writeString(Path.of("src/main/resources/files/local-suppliers.json"), json);
        System.out.println(json);
    }
}
