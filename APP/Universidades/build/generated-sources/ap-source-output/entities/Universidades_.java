package entities;

import entities.Estadisticas;
import entities.Paises;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-02-22T00:19:05")
@StaticMetamodel(Universidades.class)
public class Universidades_ { 

    public static volatile SingularAttribute<Universidades, String> universidad;
    public static volatile SingularAttribute<Universidades, Paises> idPais;
    public static volatile SingularAttribute<Universidades, String> latitud;
    public static volatile SingularAttribute<Universidades, String> longitud;
    public static volatile SingularAttribute<Universidades, String> idUniversidad;
    public static volatile CollectionAttribute<Universidades, Estadisticas> estadisticasCollection;

}