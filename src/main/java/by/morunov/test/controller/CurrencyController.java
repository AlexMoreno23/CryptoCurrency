package by.morunov.test.controller;

import by.morunov.test.domain.entity.Cryptocurrency;
import by.morunov.test.domain.entity.UserNotify;
import by.morunov.test.service.CryptoCurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Alex Morunov
 */
@AllArgsConstructor
@RestController
@RequestMapping
public class CurrencyController {

    private final CryptoCurrencyService cryptoCurrencyService;

    @GetMapping
    public List<Cryptocurrency> getCrypto() {
        cryptoCurrencyService.saveToDb();
        return cryptoCurrencyService.getAll();
    }

    @PostMapping(name = "/notify")
    public ResponseEntity<UserNotify> addUser(@RequestParam(value = "username") String username,
                                              @RequestParam(value = "cryptoname") String cryptoName,
                                              UserNotify userNotify) {
        userNotify.setUsername(username);
        userNotify.setCryptocurrency(cryptoCurrencyService.getCurrencyByName(cryptoName));
        cryptoCurrencyService.saveUser(userNotify);
        return new ResponseEntity<>(userNotify, HttpStatus.CREATED);
    }


}