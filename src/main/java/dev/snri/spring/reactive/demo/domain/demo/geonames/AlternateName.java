package dev.snri.spring.reactive.demo.domain.demo.geonames;

import dev.snri.spring.reactive.demo.domain.demo.converter.BooleanToIntConverter;

import javax.persistence.*;
import java.util.Date;

/**
 * alternate names
 */
@Entity
@Table(name = "ALTERNATE_NAMES")
@org.springframework.data.relational.core.mapping.Table
public class AlternateName {

    /**
     *     alternateNameId   : the id of this alternate name, int
     */
    @Id
    @org.springframework.data.annotation.Id
    @Column(name = "ALTERNATENAME_ID")
    private Long alternateNameId;

    /**
     *     geonameid         : geonameId referring to id in table 'geoname', int
     */
    @Column(name = "GEONAMEID")
    private String geonameid;

    /**
     * isolanguage       : iso 639 language code 2- or 3-characters; 4-characters 'post' for postal codes and 'iata','icao' and faac for airport codes, fr_1793 for French Revolution names,  abbr for abbreviation, link to a website (mostly to wikipedia), wkdt for the wikidataid, varchar(7)
     */
    @Column(name = "ISOLANGUAGE", length = 7)
    private String isolanguage;

    /**
     * alternate name    : alternate name or name variant, varchar(400)
     */
    @Column(name = "ALTERNATE_NAME", length = 400)
    private String alternateName;

    /**
     * isPreferredName   : '1', if this alternate name is an official/preferred name
     */
    @Column(name = "IS_PREFERRED_NAME", columnDefinition = "CHAR(1)")
    @Convert(converter = BooleanToIntConverter.class)
    private Boolean isPreferredName;

    /**
     * isShortName       : '1', if this is a short name like 'California' for 'State of California'
     */
    @Column(name = "IS_SHORT_NAME", columnDefinition = "CHAR(1)")
    @Convert(converter = BooleanToIntConverter.class)
    private Boolean isShortName;

    /**
     * isColloquial      : '1', if this alternate name is a colloquial or slang term. Example: 'Big Apple' for 'New York'.
     */
    @Column(name = "IS_COLLOQUIAL", columnDefinition = "CHAR(1)")
    @Convert(converter = BooleanToIntConverter.class)
    private Boolean isColloquial;

    /**
     * isHistoric        : '1', if this alternate name is historic and was used in the past. Example 'Bombay' for 'Mumbai'.
     */
    @Column(name = "IS_HISTORIC", columnDefinition = "CHAR(1)")
    @Convert(converter = BooleanToIntConverter.class)
    private Boolean isHistoric;

    /**
     *     from		  : from period when the name was used
     */
    @Column(name = "FROM")
    private Date from;

    /**
     * to		  : to period when the name was used
     */
    @Column(name = "TO")
    private Date to;

}
