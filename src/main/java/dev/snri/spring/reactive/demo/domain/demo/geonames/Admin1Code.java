package dev.snri.spring.reactive.demo.domain.demo.geonames;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * admin1Codes
 */
@Entity
@Table(name = "ADMIN1_CODES")
public class Admin1Code {

    @Id
    @Column(name = "code", length = 20)
    private String code;

    @Column(name = "NAME", length = 200)
    private String name;

    @Column(name = "ASCIINAME", length = 200)
    private String asciiname;

    @Column(name = "geonameid")
    private Long geonameid;

}
