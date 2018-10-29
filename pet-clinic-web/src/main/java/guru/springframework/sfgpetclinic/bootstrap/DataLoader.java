package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetService petService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petService = petService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Jane");
        owner2.setLastName("Austin");

        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet = new Vet();
        vet.setFirstName("Bill");
        vet.setLastName("Murray");

        vetService.save(vet);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jill");
        vet2.setLastName("Zoellner");

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
