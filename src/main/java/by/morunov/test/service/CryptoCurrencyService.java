package by.morunov.test.service;

import by.morunov.test.domain.dto.CryptoсurrencyDto;
import by.morunov.test.domain.entity.Cryptocurrency;
import by.morunov.test.domain.entity.UserNotify;
import by.morunov.test.repo.CryptoCurrencyRepo;
import by.morunov.test.repo.UserNotifyRepo;
import com.google.common.collect.ImmutableList;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

/**
 * @author Alex Morunov
 */
@Service
@Transactional
@AllArgsConstructor
public class CryptoCurrencyService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CryptoCurrencyService.class);

    private  final UserNotifyRepo userNotifyRepo;
    private final CryptoCurrencyRepo currencyRepo;
    private final CexIoClient cexIoClient;

    public List<Cryptocurrency> getAll() {
        return cexIoClient.getCurrencies().stream()
                .map(this::toCryptocurrency)
                .collect(collectingAndThen(toList(), ImmutableList::copyOf));
    }

    private Cryptocurrency toCryptocurrency(@NonNull CryptoсurrencyDto cryptoсurrencyDto) {
        Double price = new Double(cryptoсurrencyDto.getLprice());
        return new Cryptocurrency(cryptoсurrencyDto.getCurr1(),
                cryptoсurrencyDto.getCurr2(), price);
    }

    @Scheduled(fixedDelay = 60000)
    public void saveToDb() {
        currencyRepo.saveAll(getAll());
    }

    public Cryptocurrency getCurrencyByName(String currencyName){
       return currencyRepo.getByCryptoName(currencyName);
    }

    public void saveUser(UserNotify userNotify){
       userNotifyRepo.save(userNotify);
    }

    @Scheduled(initialDelay = 300000, fixedRate = 6000)
    public void notifyPrice(){
        if(userNotifyRepo.getById(1L).getCryptocurrency().getPrice() > getCurrencyByName(userNotifyRepo.getById(1L).getCryptocurrency().getCrypto()).getPrice()) {
            LOGGER.warn(userNotifyRepo.getById(1L).getUsername() + "currency appreciation!");
        }
    }






}
