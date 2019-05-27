package server.repositories;

import dtos.CardDto;
import entities.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, String> {
    CardDto findCardById(String id);

    void deleteCardById(String id);

    @Query("SELECT NEW dtos.CardDto(c) FROM Card c")
    List<CardDto> findAllCardsAsDto();

    CardDto findCardByStudent_Id(Integer id);
}
