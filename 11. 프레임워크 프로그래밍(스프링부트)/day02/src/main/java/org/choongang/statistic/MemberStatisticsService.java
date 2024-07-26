package org.choongang.statistic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class MemberStatisticsService {

    @Scheduled(fixedRate = 5000, timeUnit = TimeUnit.SECONDS)
    public void makeData() {
        log.info("실행!!!");
    }
}
