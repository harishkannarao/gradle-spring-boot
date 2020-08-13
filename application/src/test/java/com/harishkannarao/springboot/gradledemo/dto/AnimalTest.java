package com.harishkannarao.springboot.gradledemo.dto;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AnimalTest {
    @Test
    public void test_Animal_builder() {
        Animal lion = Animal.builder()
                .setName("Lion")
                .setNoOfLegs(4)
                .build();

        assertThat(lion.getName(), equalTo("Lion"));
        assertThat(lion.getNoOfLegs(), equalTo(4));
    }

    @Test
    public void test_Animal_builder_from_another_instance() {
        Animal lion = Animal.builder()
                .setName("Lion")
                .setNoOfLegs(4)
                .build();

        Animal tiger = lion.toBuilder()
                .setName("Tiger")
                .build();

        assertThat(tiger.getName(), equalTo("Tiger"));
        assertThat(tiger.getNoOfLegs(), equalTo(4));
    }
}
