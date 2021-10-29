package dev.snri.spring.reactive.demo.domain.demo.geonames;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "GEONAME")
@org.springframework.data.relational.core.mapping.Table
@Data
public class Geoname {

    /**
     * geonameid  : integer id of record in geonames database
     */
    @Id
    @Column(name = "GEONAMEID")
    @org.springframework.data.annotation.Id
    private Long geonameid;

    /**
     *     name              : name of geographical point (utf8) varchar(200)
     */
    @Column(name = "NAME", length = 200)
    private String name;

    /**
     *     asciiname         : name of geographical point in plain ascii characters, varchar(200)
     */
    @Column(name = "ASCIINAME", length = 200)
    private String asciiname;

    /**
     *     alternatenames    : alternatenames, comma separated, ascii names automatically transliterated, convenience attribute from alternatename table, varchar(10000)
     */
    @Column(name = "ALTERNATENAMES")
    @Lob
    private String alternatenames;

    /**
     *     latitude          : latitude in decimal degrees (wgs84)
     */
    @Column(name = "LATITUDE", precision = 9, scale = 6)
    private Double latitude;

    /**
     *     longitude         : longitude in decimal degrees (wgs84)
     */
    @Column(name = "LONGITUDE", precision = 9, scale = 6)
    private Double longitude;

    /**
     *     feature class     : see http://www.geonames.org/export/codes.html, char(1)
     */
    @Column(name = "FEATURE_CLASS", columnDefinition = "CHAR(1)")
    private String featureClass;

    /**
     *     feature code      : see http://www.geonames.org/export/codes.html, varchar(10)
     */
    @Column(name = "FEATURE_CODE", length = 10)
    private String featureCode;

    /**
     *     country code      : ISO-3166 2-letter country code, 2 characters
     */
    @Column(name = "COUNTRY_CODE", length = 2)
    private String countryCode;

    /**
     *     cc2               : alternate country codes, comma separated, ISO-3166 2-letter country code, 200 characters
     */
    @Column(name = "CC2", length = 200)
    private String cc2;

    /**
     *     admin1 code       : fipscode (subject to change to iso code), see exceptions below, see file admin1Codes.txt for display names of this code; varchar(20)
     */
    @Column(name = "ADMIN1_CODE", length = 20)
    private String admin1Code;

    /**
     *     admin2 code       : code for the second administrative division, a county in the US, see file admin2Codes.txt; varchar(80)
     */
    @Column(name = "ADMIN2_CODE", length = 80)
    private String admin2Code;

    /**
     *     admin3 code       : code for third level administrative division, varchar(20)
     */
    @Column(name = "ADMIN3_CODE", length = 20)
    private String admin3Code;

    /**
     *     admin4 code       : code for fourth level administrative division, varchar(20)
     */
    @Column(name = "ADMIN4_CODE", length = 20)
    private String admin4Code;

    /**
     *     population        : bigint (8 byte int)
     */
    @Column(name = "POPULATION")
    private Long population;

    /**
     *     elevation         : in meters, integer
     */
    @Column(name = "ELEVATION")
    private Integer elevation;

    /**
     *     dem               : digital elevation model, srtm3 or gtopo30, average elevation of 3''x3'' (ca 90mx90m) or 30''x30'' (ca 900mx900m) area in meters, integer. srtm processed by cgiar/ciat.
     */
    @Column(name = "DEM")
    private Integer dem;

    /**
     * timezone          : the iana timezone id (see file timeZone.txt) varchar(40)
     */
    @Column(name = "TIMEZONE")
    private Instant timezone;

    /**
     *     modification date : date of last modification in yyyy-MM-dd format
     */
    @Column(name = "MODIFICATION_DATE")
    private Timestamp modificationDate;

}
