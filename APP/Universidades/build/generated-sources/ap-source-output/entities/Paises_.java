package entities;

import entities.Universidades;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-02-22T00:19:05")
@StaticMetamodel(Paises.class)
public class Paises_ { 

    public static volatile SingularAttribute<Paises, String> idPais;
    public static volatile SingularAttribute<Paises, String> pais;
    public static volatile CollectionAttribute<Paises, Universidades> universidadesCollection;

}