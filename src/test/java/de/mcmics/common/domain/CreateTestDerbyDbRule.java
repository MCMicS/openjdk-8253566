package de.mcmics.common.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.rules.ExternalResource;

import java.sql.Connection;
import java.sql.DriverManager;

@Slf4j
public class CreateTestDerbyDbRule extends ExternalResource {

    @Override
    protected void before() throws Throwable {
        super.before();
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver").getDeclaredConstructor().newInstance();
        try (Connection con = DriverManager.getConnection("jdbc:derby:memory:test-derbydb;create=true")) {
            log.debug("Valid derby Connection for JUnit: {}", con.isValid(0));
        }
    }
}
