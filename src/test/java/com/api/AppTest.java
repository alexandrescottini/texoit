package com.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    MovieControllerIntegrationTest.class,
    ProducerControllerIntegrationTest.class,
    StudioControllerIntegrationTest.class
})
public class AppTest {

}
