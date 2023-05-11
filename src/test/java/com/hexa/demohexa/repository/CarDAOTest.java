package com.hexa.demohexa.repository;

import com.hexa.demohexa.domain.models.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarDAOTest {
    @TestConfiguration
    static class CarDAOTestContextConfiguration {
        @Bean
        public ICarDAO iCarDAO() {
            return new CarDAO();
        }
    }
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    CarDAO carDAO;

    @Test
    public void shouldReturnACarWhenCreateACarInDatabase(){
        //Arrange
        String expectedModel = "Sportage";
        Car car = Car.builder()
                .carId(1L)
                .km(100)
                .year(2019)
                .model(expectedModel)
                .build();
        //Act
        Car carSaved = carDAO.save(car);
        //Assert
        assertThat(carSaved.getModel()).isEqualTo(expectedModel);
    }

    @Test
    public void shouldReturnAListOfCarsWhenFindAllIsInvoked(){
        Car car = Car.builder()
                .carId(1L)
                .km(100)
                .year(2019)
                .model("Sportage")
                .build();
        Car carSaved = entityManager.persist(car);
        Car car2 = Car.builder()
                .carId(2L)
                .km(1000)
                .year(2020)
                .model("Aveo")
                .build();
        Car carSaved2 = entityManager.persist(car2);
        //entityManager.flush();

        /*List<Car> expectedCarList = new ArrayList<>();
        expectedCarList.add(carSaved);
        expectedCarList.add(carSaved2);
        */
        int expectedListSize =2 ;
        List<Car> listCarsFound = carDAO.findAll();

        assertThat(listCarsFound.size()).isEqualTo(expectedListSize);

    }

}
