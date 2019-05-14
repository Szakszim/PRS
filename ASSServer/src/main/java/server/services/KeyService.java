package server.services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class KeyService {

    public String generateKey() {
        Random random = new Random();

        String alphabet = "0123456789ABCDEFGHIJKLOMNOPQRSTUVWXYZ";
        String newKeyValue = "";
        for (int i =0;i<4;i++) {
            for (int j = 0; j < 5; j++) {
                newKeyValue = newKeyValue + alphabet.charAt(random.nextInt(alphabet.length()));
            }
            newKeyValue = newKeyValue+'-';
        }
        for (int k = 0; k < 5; k++) {
            newKeyValue = newKeyValue + alphabet.charAt(random.nextInt(alphabet.length()));
        }

        return newKeyValue;
    }

}
