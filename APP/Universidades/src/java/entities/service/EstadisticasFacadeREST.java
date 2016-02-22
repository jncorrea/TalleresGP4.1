/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.service;

import entities.Estadisticas;
import entities.Paises;
import entities.Universidades;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Edgar
 */
@Stateless
@Path("entities.estadisticas")
public class EstadisticasFacadeREST extends AbstractFacade<Estadisticas> {

    @PersistenceContext(unitName = "UniversidadesPU")
    private EntityManager em;

    public EstadisticasFacadeREST() {
        super(Estadisticas.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Estadisticas entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Estadisticas entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Estadisticas find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Estadisticas> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Estadisticas> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @GET
    @Path("/allData/{indicador},{anio},{limit}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Estadisticas> allData(@PathParam("indicador") String indicador, @PathParam("anio") String anio, @PathParam("limit") String limit) throws SQLException {
        Statement cmd = connect.createStatement();
        ResultSet rs = cmd.executeQuery("SELECT e.*, u.id_universidad, u.universidad FROM estadisticas e, universidades u WHERE e.id_universidad = u.id_universidad and e.anio = '" + anio + "' order by e." + indicador + " desc limit " + limit);
        ArrayList<Estadisticas> datos = new ArrayList<Estadisticas>();
        while (rs.next()) {
            int id_dato = Integer.parseInt(rs.getString("id_dato"));
            String id_universidad = rs.getString("id_universidad");
            String universidad = rs.getString("universidad");
            Universidades u = new Universidades();
            u.setIdUniversidad(id_universidad);
            u.setUniversidad(universidad);
            String anio_est = rs.getString("anio");
            int ibe = Integer.parseInt(rs.getString("ibe"));
            int lac = Integer.parseInt(rs.getString("lac"));
            int co = Integer.parseInt(rs.getString("co"));
            int o = Integer.parseInt(rs.getString("o"));
            float ic = Float.parseFloat(rs.getString("ic"));
            float ni = Float.parseFloat(rs.getString("ni"));
            float q1 = Float.parseFloat(rs.getString("q1"));
            float spec = Float.parseFloat(rs.getString("spec"));
            float exc = Float.parseFloat(rs.getString("exc"));
            float lead = Float.parseFloat(rs.getString("lead"));
            float ewl = Float.parseFloat(rs.getString("ewl"));
            Estadisticas datpais = new Estadisticas();
            datpais.setIdDato(id_dato);
            datpais.setIdUniversidad(u);
            datpais.setAnio(anio_est);
            datpais.setIbe(ibe);
            datpais.setLac(lac);
            datpais.setCo(co);
            datpais.setO(o);
            datpais.setIc(ic);
            datpais.setNi(ni);
            datpais.setQ1(q1);
            datpais.setSpec(spec);
            datpais.setExc(exc);
            datpais.setLead(lead);
            datpais.setEwl(ewl);
            datos.add(datpais);
            System.out.println("AQUI ESTA: " + id_dato);
        }
        rs.close();
        return datos;
    }

    @GET
    @Path("/countryData/{indicador},{anio},{limit},{pais}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Estadisticas> countryData(@PathParam("indicador") String indicador, @PathParam("anio") String anio, @PathParam("limit") String limit, @PathParam("pais") String pais) throws SQLException {
        Statement cmd = connect.createStatement();
        ResultSet rs = cmd.executeQuery("SELECT e.*, u.id_universidad, u.universidad "
                + "FROM estadisticas e, universidades u, paises p "
                + "WHERE e.id_universidad = u.id_universidad "
                + "and u.id_pais = p.id_pais "
                + "and e.anio='" + anio + "' "
                + "and p.pais='" + pais + "' "
                + "order by e." + indicador + " desc "
                + "limit " + limit);
        ArrayList<Estadisticas> datos = new ArrayList<Estadisticas>();
        while (rs.next()) {
            int id_dato = Integer.parseInt(rs.getString("id_dato"));
            String id_universidad = rs.getString("id_universidad");
            String universidad = rs.getString("universidad");
            Universidades u = new Universidades();
            u.setIdUniversidad(id_universidad);
            u.setUniversidad(universidad);
            String anio_est = rs.getString("anio");
            int ibe = Integer.parseInt(rs.getString("ibe"));
            int lac = Integer.parseInt(rs.getString("lac"));
            int co = Integer.parseInt(rs.getString("co"));
            int o = Integer.parseInt(rs.getString("o"));
            float ic = Float.parseFloat(rs.getString("ic"));
            float ni = Float.parseFloat(rs.getString("ni"));
            float q1 = Float.parseFloat(rs.getString("q1"));
            float spec = Float.parseFloat(rs.getString("spec"));
            float exc = Float.parseFloat(rs.getString("exc"));
            float lead = Float.parseFloat(rs.getString("lead"));
            float ewl = Float.parseFloat(rs.getString("ewl"));
            Estadisticas datpais = new Estadisticas();
            datpais.setIdDato(id_dato);
            datpais.setIdUniversidad(u);
            datpais.setAnio(anio_est);
            datpais.setIbe(ibe);
            datpais.setLac(lac);
            datpais.setCo(co);
            datpais.setO(o);
            datpais.setIc(ic);
            datpais.setNi(ni);
            datpais.setQ1(q1);
            datpais.setSpec(spec);
            datpais.setExc(exc);
            datpais.setLead(lead);
            datpais.setEwl(ewl);
            datos.add(datpais);
            System.out.println("AQUI ESTA: " + id_dato);
        }
        rs.close();
        return datos;
    }

    @GET
    @Path("/universityData/{indicador},{universidad}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Estadisticas> universityData(@PathParam("indicador") String indicador, @PathParam("universidad") String inst) throws SQLException {
        Statement cmd = connect.createStatement();
        ResultSet rs = cmd.executeQuery("SELECT e.*, u.id_universidad, u.universidad "
                + "FROM estadisticas e, universidades u "
                + "WHERE e.id_universidad = u.id_universidad "
                + "and u.universidad = '"+inst+"' "
                + "order by e." + indicador + " asc");
        ArrayList<Estadisticas> datos = new ArrayList<Estadisticas>();
        while (rs.next()) {
            int id_dato = Integer.parseInt(rs.getString("id_dato"));
            String id_universidad = rs.getString("id_universidad");
            String universidad = rs.getString("universidad");
            Universidades u = new Universidades();
            u.setIdUniversidad(id_universidad);
            u.setUniversidad(universidad);
            String anio_est = rs.getString("anio");
            int ibe = Integer.parseInt(rs.getString("ibe"));
            int lac = Integer.parseInt(rs.getString("lac"));
            int co = Integer.parseInt(rs.getString("co"));
            int o = Integer.parseInt(rs.getString("o"));
            float ic = Float.parseFloat(rs.getString("ic"));
            float ni = Float.parseFloat(rs.getString("ni"));
            float q1 = Float.parseFloat(rs.getString("q1"));
            float spec = Float.parseFloat(rs.getString("spec"));
            float exc = Float.parseFloat(rs.getString("exc"));
            float lead = Float.parseFloat(rs.getString("lead"));
            float ewl = Float.parseFloat(rs.getString("ewl"));
            Estadisticas datpais = new Estadisticas();
            datpais.setIdDato(id_dato);
            datpais.setIdUniversidad(u);
            datpais.setAnio(anio_est);
            datpais.setIbe(ibe);
            datpais.setLac(lac);
            datpais.setCo(co);
            datpais.setO(o);
            datpais.setIc(ic);
            datpais.setNi(ni);
            datpais.setQ1(q1);
            datpais.setSpec(spec);
            datpais.setExc(exc);
            datpais.setLead(lead);
            datpais.setEwl(ewl);
            datos.add(datpais);
            System.out.println("AQUI ESTA: " + id_dato);
        }
        rs.close();
        return datos;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
