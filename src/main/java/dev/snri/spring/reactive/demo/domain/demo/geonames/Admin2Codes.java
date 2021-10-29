package dev.snri.spring.reactive.demo.domain.demo.geonames;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * admin2Codes
 */
@Entity
@Table(name = "ADMIN2_CODES")
@org.springframework.data.relational.core.mapping.Table
public class Admin2Codes {

    /**
     * concatenated codes
     */
    @Id
    @org.springframework.data.annotation.Id
    @Column(name = "code", length = 80)
    private String code;

    /**
     * name
     */
    @Column(name = "name", length = 200)
    private String name;

    /**
     * asciiname
     */
    @Column(name = "asciiname", length = 200)
    private String asciiname;

    /**
     * geonameid
     */
    @Column(name="geonameid")
    private Long geonameid;

}
