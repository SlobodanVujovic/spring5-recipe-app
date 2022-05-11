package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {
    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Test
    public void findByUomTeaspoon() {
        Optional<UnitOfMeasure> result = unitOfMeasureRepository.findByUom("Teaspoon");

        assertEquals("Teaspoon", result.get().getUom());
    }

    @Test
    public void findByUomCup() {
        Optional<UnitOfMeasure> result = unitOfMeasureRepository.findByUom("Cup");

        assertEquals("Cup", result.get().getUom());
    }
}