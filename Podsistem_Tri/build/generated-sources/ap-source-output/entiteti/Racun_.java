package entiteti;

import entiteti.Komitent;
import entiteti.Mesto;
import entiteti.Transakcija;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-02-08T18:12:38")
@StaticMetamodel(Racun.class)
public class Racun_ { 

    public static volatile SingularAttribute<Racun, Integer> brojTransakcija;
    public static volatile SingularAttribute<Racun, Komitent> idK;
    public static volatile SingularAttribute<Racun, Double> dozvoljenMinus;
    public static volatile SingularAttribute<Racun, Date> datumVreme;
    public static volatile SingularAttribute<Racun, Mesto> idM;
    public static volatile SingularAttribute<Racun, Double> stanje;
    public static volatile SingularAttribute<Racun, Integer> idR;
    public static volatile ListAttribute<Racun, Transakcija> transakcijaList;
    public static volatile ListAttribute<Racun, Transakcija> transakcijaList1;
    public static volatile SingularAttribute<Racun, String> status;

}