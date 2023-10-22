package com.dokkie;

import com.dokkie.event.EventFixture;
import com.dokkie.payment.PaymentFixture;
import com.dokkie.user.UserFixture;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements ApplicationRunner {

    private final UserFixture userFixture;
    private final EventFixture eventFixture;
    private final PaymentFixture paymentFixture;

    public DatabaseInitializer(
            UserFixture userFixture,
            EventFixture eventFixture,
            PaymentFixture paymentFixture
    ) {
        this.userFixture = userFixture;
        this.eventFixture = eventFixture;
        this.paymentFixture = paymentFixture;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.userFixture.loadUsers();
        this.eventFixture.loadEvents();
        this.paymentFixture.loadPayments();
    }
}
