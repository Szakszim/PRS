package server.controllers;

import dtos.rowmodels.PurchaseRowModelDto;
import entities.Key;
import entities.KeyAssignment;
import entities.Purchase;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.repositories.KeyRepository;
import server.repositories.PurchaseRepository;
import utils.DateUtil;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
public class PurchaseController {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private PurchaseRepository purchaseRepository;

    @Inject
    private KeyRepository keyRepository;

    @Transactional
    @RequestMapping(value = "/savepurchase", method = RequestMethod.POST)
    public ResponseEntity saveAddress(@RequestBody @Valid Purchase purchase, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
//        em.getTransaction().begin();
        purchase.setPurchaseDate(new Date());
        purchase.setPaymentIndicator(Boolean.FALSE);
        purchase = em.merge(purchase);
        em.flush();
//        em.getTransaction().commit();

//        //for time when payments are not implement!
//        Key
//        //end of block

        return new ResponseEntity(purchase, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    public ResponseEntity findById(@RequestBody @Valid Long purchaseId, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Purchase purchaseResult = purchaseRepository.findById(purchaseId).get();

        return new ResponseEntity(purchaseResult, new HttpHeaders(), HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(value = "/confirmpurchasepayment", method = RequestMethod.POST)
    public ResponseEntity confirmPurchasePayment(@RequestBody @Valid Long purchaseId, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        Purchase purchaseResult = purchaseRepository.findById(purchaseId).get();
        purchaseResult.setPaymentIndicator(Boolean.TRUE);
        purchaseResult.setPaymentDate(new Date());
        purchaseResult = em.merge(purchaseResult);
//        em.flush();


        //create a key assignment start
        KeyAssignment keyAssignment = new KeyAssignment();
        Key key = keyRepository.getFirstFreeKey();
        keyAssignment.setLicenseKey(key);
        keyAssignment.setLicenseExpiredDate(DateUtil.addDays(new Date(), 365));
        keyAssignment.setFarm(purchaseResult.getFarm());
        keyAssignment.setModuleType(purchaseResult.getModuleType());
        keyAssignment.setConfirmationIndicator(Boolean.FALSE);
        keyAssignment.setPurchase(purchaseResult);
        em.merge(keyAssignment);

        key.setUsageIndicator(Boolean.TRUE);
        key.setUsageDate(new Date());
        em.merge(key);
        //create a key assignment end

        em.flush();

        return new ResponseEntity(purchaseResult, new HttpHeaders(), HttpStatus.OK);
    }

    @RequestMapping(value = "/purchaselist", method = RequestMethod.POST)
    public ResponseEntity findAllPurchasesWithFarmData() {
        List<PurchaseRowModelDto> purchaseListResult = purchaseRepository.findAllPurchasesWithPurchaserAndFarmData();
        return new ResponseEntity(purchaseListResult, new HttpHeaders(), HttpStatus.OK);
    }

}
