package com.dokkie;

import com.dokkie.event.EventFixture;
import com.dokkie.user.UserFixture;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements ApplicationRunner {

    private final UserFixture userFixture;
    private final EventFixture eventFixture;

    public DatabaseInitializer(UserFixture userFixture, EventFixture eventFixture) {
        this.userFixture = userFixture;
        this.eventFixture = eventFixture;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.userFixture.loadUsers();
        this.eventFixture.loadEvents();
    }
}
