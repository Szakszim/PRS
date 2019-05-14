package server.controllers;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.repositories.AddressRepository;
import server.repositories.FarmRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@RestController
public class WorkController {
    @PersistenceContext
    private EntityManager em;

//    @Inject
//    WorkRepository workRepository;
//
//    @RequestMapping("/worklist")
//    public List<Work> getWorkList() {
////        JPAQueryFactory query = new JPAQueryFactory(em);
//
//        List<Work> result = workRepository.findAll();
//
//        return result;
//    }
}
