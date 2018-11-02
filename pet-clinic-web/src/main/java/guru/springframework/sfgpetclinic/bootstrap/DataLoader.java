package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetService petService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetService petService,
                      PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        loadOwnerData();
        loadVetData();
    }

    private void loadOwnerData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("1234 Test St");
        owner1.setCity("Miami");
        owner1.setTelephone("7431874382");

        Pet mikePet = new Pet();
        mikePet.setPetType(savedDogPetType);
        mikePet.setOwner(owner1);
        mikePet.setBirthDate(LocalDate.now());
        mikePet.setName("Fido");
        owner1.getPets().add(mikePet);

        ownerService.save(owner1);

        Visit visit = new Visit();
        visit.setVisitDate(LocalDate.now());
        visit.setDescription("Sneezy Kitty");
        visit.setPet(mikePet);

        visitService.save(visit);

        Owner owner2 = new Owner();
        owner2.setFirstName("Jane");
        owner2.setLastName("Austin");
        owner2.setAddress("1234 Test Dr");
        owner2.setCity("Miami");
        owner2.setTelephone("194381939");

        Pet janePet = new Pet();
        janePet.setPetType(savedCatPetType);
        janePet.setOwner(owner2);
        janePet.setBirthDate(LocalDate.now());
        janePet.setName("Faith");
        owner2.getPets().add(janePet);

        ownerService.save(owner2);

        System.out.println("Loaded Owners...");
    }

    private void loadVetData() {
        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

        Vet vet = new Vet();
        vet.setFirstName("Bill");
        vet.setLastName("Murray");
        vet.getSpecialties().add(savedRadiology);

        vetService.save(vet);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jill");
        vet2.setLastName("Zoellner");
        vet2.getSpecialties().add(savedDentistry);
        vet2.getSpecialties().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
