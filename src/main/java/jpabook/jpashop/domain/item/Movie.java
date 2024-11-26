package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter @SuperBuilder
@RequiredArgsConstructor
@DiscriminatorValue("M")
public class Movie extends  Item{

    private String director;
    private String actor;
}
