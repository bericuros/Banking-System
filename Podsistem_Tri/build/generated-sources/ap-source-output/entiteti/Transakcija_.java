package entiteti;

import entiteti.Filijala;
import entiteti.Racun;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-08T18:12:38")
@StaticMetamodel(Transakcija.class)
public class Transakcija_ { 

    public static volatile SingularAttribute<Transakcija, Integer> redniBrojRacunSa;
    public static volatile SingularAttribute<Transakcija, Double> iznos;
    public static volatile SingularAttribute<Transakcija, String> svrha;
    public static volatile SingularAttribute<Transakcija, Date> datumVreme;
    public static volatile SingularAttribute<Transakcija, Racun> idRSa;
    public static volatile SingularAttribute<Transakcija, Integer> idT;
    public static volatile SingularAttribute<Transakcija, String> tip;
    public static volatile SingularAttribute<Transakcija, Filijala> idF;
    public static volatile SingularAttribute<Transakcija, Racun> idRNa;
    public static volatile SingularAttribute<Transakcija, Integer> redniBrojRacunNa;

}