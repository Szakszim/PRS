package server.repositories;

import dtos.CardDto;
import entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    CardDto findCardById(Integer id);

    void deleteCardById(Integer id);

    @Query("SELECT NEW CardDto(c.id,c.student) FROM Card c")
    List<CardDto> findAllCardsAsDto();
}
