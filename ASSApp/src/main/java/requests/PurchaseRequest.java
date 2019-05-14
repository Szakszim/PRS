package requests;

import dtos.base.PurchaseDto;
import dtos.rowmodels.PurchaseRowModelDto;
import entities.Purchase;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import parameters.Settings;

import java.util.List;

public class PurchaseRequest {

    public Purchase save(Purchase purchase) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Purchase> requestUpdate = new HttpEntity<>(purchase, null);
        ResponseEntity<Purchase> result = restTemplate.exchange(Settings.URL + "savepurchase", HttpMethod.POST, requestUpdate, Purchase.class);
        purchase = result.getBody();
        return purchase;
    }

    public Purchase findById(Long accountId) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Long> requestUpdate = new HttpEntity<>(accountId, null);

        ResponseEntity<Purchase> response = restTemplate.exchange(Settings.URL + "purchase", HttpMethod.POST, requestUpdate,Purchase.class);
       Purchase purchase = response.getBody();

        return purchase;
    }

    public Purchase confirmPurchasePayment(Long accountId) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Long> requestUpdate = new HttpEntity<>(accountId, null);

        ResponseEntity<Purchase> response = restTemplate.exchange(Settings.URL + "confirmpurchasepayment", HttpMethod.POST, requestUpdate,Purchase.class);
        Purchase purchase = response.getBody();

        return purchase;
    }

//    public Account findById(Long accountId) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpEntity<Long> requestUpdate = new HttpEntity<>(accountId, null);
//
//        ResponseEntity<Account> response = restTemplate.exchange(Settings.URL + "account", HttpMethod.POST, requestUpdate, Account.class);
//        Account account = response.getBody();
//
//        return account;
//    }
//
//    public List<AccountRowModelDto> findAllAccountsWithPersonData() {
//        RestTemplate restTemplate = new RestTemplate();
//
//        ResponseEntity<List<AccountRowModelDto>> response = restTemplate.exchange(Settings.URL + "accountlist", HttpMethod.POST, null, new ParameterizedTypeReference<List<AccountRowModelDto>>() {
//        });
//        List<AccountRowModelDto> accountListResult = response.getBody();
//
//        return accountListResult;
//    }
//
//    public void delete(Account account) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpEntity<Account> requestUpdate = new HttpEntity<>(account, null);
//        restTemplate.exchange(Settings.URL+"deleteaccount", HttpMethod.DELETE, requestUpdate, Account.class);
//    }

    public List<PurchaseRowModelDto> findAllPurchasesWithPurchaserAndFarmData(){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<PurchaseRowModelDto> > response = restTemplate.exchange(Settings.URL+"purchaselist", HttpMethod.POST, null,new ParameterizedTypeReference<List<PurchaseRowModelDto>>(){});
        List<PurchaseRowModelDto> purchaseListResult = response.getBody();

        return purchaseListResult;
    }
}
