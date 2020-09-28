# How to Reproduce [JDK-8253566]

Clone this project. Use JDK15+. With JDK <= 14.0.2 this test will fail.

* Run `mvn test`
* Test will **PASS** if the `ConstraintDeclarationException` occurs
* Test **FAIL** if all works correctly and no exception occurs on validation
* It will retry 250 times (default) on FAIL. It can be configured with `-DrerunFailingTestsCount=10`.

## Debug
1. Configure you Debug environment to reset cached metadatas in 'org.hibernate.validator.internal.metadata.BeanMetaDataManagerImpl.getBeanMetaData'
   see `DebugSettings.png`
1. Create Bug configuration to run tests multiple times (see `DebugSettings.png`).
   It will normally fail test (which mean the error not occurred).
   The test will pass if the error occurs.

[JDK-8253566]]: https://bugs.openjdk.java.net/browse/JDK-8253566
