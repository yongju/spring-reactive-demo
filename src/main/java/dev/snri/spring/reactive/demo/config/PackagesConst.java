package dev.snri.spring.reactive.demo.config;

final class PackagesConst {

    private static final String BASE_PACKAGE = "dev.snri.spring.reactive.demo";
    private static final String BASE_DOMAIN = BASE_PACKAGE + ".domain";
    private static final String BASE_REPOSITORIES = BASE_PACKAGE + ".repositories";
    private static final String JPA_PACKAGE = ".jpa";
    private static final String REACTIVE_PACKAGE = ".reactive";

    static final String APP_PACKAGE = ".app";
    static final String APP_DOMAIN = BASE_DOMAIN + APP_PACKAGE;
    static final String APP_JPA_REPOSITORIES = BASE_REPOSITORIES + APP_PACKAGE + JPA_PACKAGE;
    static final String APP_REACTIVE_REPOSITORIES = BASE_REPOSITORIES + APP_PACKAGE + REACTIVE_PACKAGE;

    static final String DEMO_PACKAGE = ".demo";
    static final String DEMO_DOMAIN = BASE_DOMAIN + DEMO_PACKAGE;
    static final String DEMO_JPA_REPOSITORIES = BASE_REPOSITORIES + DEMO_PACKAGE + JPA_PACKAGE;
    static final String DEMO_REACTIVE_REPOSITORIES = BASE_REPOSITORIES + DEMO_PACKAGE + REACTIVE_PACKAGE;

}
