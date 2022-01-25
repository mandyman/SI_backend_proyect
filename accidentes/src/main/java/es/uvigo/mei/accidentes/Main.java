package es.uvigo.mei.accidentes;

import es.uvigo.mei.accidentes.entidades.*;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Main {

    private static EntityManagerFactory emf;

    public static final void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("accidentes_PU");

        //pruebaCrearPersona();
        //pruebaCrearVehiculo();
        pruebaCrearAccidente();



        emf.close();
    }

    private static final void pruebaCrearPersona() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            Localizacion loc = new Localizacion("domicilio","localidad","codigopostal","provincia");
            Persona p1 = new Persona("name","apellidos","59396847A",loc);
            em.persist(p1);
       

            tx.commit();

        } catch (Exception e) {
            System.err.println("Error en pruebaCrearPersona");
            e.printStackTrace(System.err);

            if ((tx != null) && tx.isActive()) {
                tx.rollback();
            }
        }

        em.close();
    }
    
    private static final void pruebaCrearVehiculo() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            TypedQuery<Persona> query = em.createQuery("SELECT a FROM Persona AS a", Persona.class);
            List<Persona> personas = query.getResultList();
            Persona p1 = personas.get(0);
            
            Vehiculo v1 = new Vehiculo("model","matricula", p1);
            em.persist(v1);
       

            tx.commit();

        } catch (Exception e) {
            System.err.println("Error en pruebaCrearVehiculo");
            e.printStackTrace(System.err);

            if ((tx != null) && tx.isActive()) {
                tx.rollback();
            }
        }

        em.close();
    }
    
    private static final void pruebaCrearAccidente() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Localizacion loc = new Localizacion("domicilio","localidad","codigopostal","provincia");
            /*TypedQuery<Persona> query = em.createQuery("SELECT a FROM Persona AS a", Persona.class);
            List<Persona> personas = query.getResultList();
            Persona p1 = personas.get(0);*/
            
            //Vehiculo v1 = new Vehiculo("model","matricula", p1);
            

            
                   
            //em.persist(mu1);
            
            Accidente ac1 = new Accidente(loc,"description");                          
            Multa mu1 = new Multa("tipo", "sancion", ac1);         
            ac1.anadirMulta(mu1);
            
            em.persist(ac1);

            tx.commit();

        } catch (Exception e) {
            System.err.println("Error en pruebaCrearVehiculo");
            e.printStackTrace(System.err);

            if ((tx != null) && tx.isActive()) {
                tx.rollback();
            }
        }

        em.close();
    }
    
    /*private static final void pruebaCrearEntidades() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            
            Familia f1 = new Familia("tubos", "tubos de todas clases");
            em.persist(f1);
            Articulo a1 = new Articulo("tubo acero", "tubo de acero", f1, 10.0);
            em.persist(a1);

            

            Direccion d = new Direccion("calle", "localidad", "12345", "provincia", "123456789");
            Cliente c = new Cliente("12345678A", "juan rey rey", d);
            em.persist(c);

            Almacen a = new Almacen("principal", "almacen principal", d);
            em.persist(a);

            ArticuloAlmacen aa1 = new ArticuloAlmacen(a1, a, 10);
            ArticuloAlmacen aa2 = new ArticuloAlmacen(a2, a, 15);
            ArticuloAlmacen aa3 = new ArticuloAlmacen(a3, a, 20);
            ArticuloAlmacen aa4 = new ArticuloAlmacen(a4, a, 25);
            em.persist(aa1);
            em.persist(aa2);
            em.persist(aa3);
            em.persist(aa4);

            tx.commit();

        } catch (Exception e) {
            System.err.println("Error en pruebaCrearEntidades");
            e.printStackTrace(System.err);

            if ((tx != null) && tx.isActive()) {
                tx.rollback();
            }
        }

        em.close();
    }

    private static final void pruebaCrearPedido() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Cliente c = em.find(Cliente.class, "12345678A");
            Articulo a1 = em.find(Articulo.class, (long) 1);
            Articulo a2 = em.find(Articulo.class, (long) 3);                       
            Articulo a3 = em.find(Articulo.class, (long) 4);                       

            Pedido p = new Pedido(Calendar.getInstance().getTime(), c);
            p.anadirLineaPedido(new LineaPedido(p, 2, a1, a1.getPrecioUnitario()));
            p.anadirLineaPedido(new LineaPedido(p, 5, a2, a2.getPrecioUnitario()));
            p.anadirLineaPedido(new LineaPedido(p, 1, a3, a3.getPrecioUnitario()));
            em.persist(p);

            tx.commit();

        } catch (Exception e) {
            System.err.println("Error en pruebaCrearPedido");
            e.printStackTrace(System.err);

            if ((tx != null) && tx.isActive()) {
                tx.rollback();
            }
        }

        em.close();
    }

    private static void pruebaConsultaArticulos() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            TypedQuery<Articulo> query = em.createQuery("SELECT a FROM Articulo AS a", Articulo.class);
            List<Articulo> articulos = query.getResultList();

            System.out.println("--------------------");
            System.out.println("Listado de artículos");
            System.out.println("--------------------");
            for (Articulo a : articulos) {
                System.out.println(a.toString());
            }
            System.out.println("------------------\n");

            tx.commit();

        } catch (Exception e) {
            System.err.println("Error en pruebaConsultaArticulos");
            e.printStackTrace(System.err);

            if ((tx != null) && tx.isActive()) {
                tx.rollback();
            }
        }

        em.close();
    }

    private static void pruebaConsultaPedidos() {
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            TypedQuery<Pedido> query = em.createQuery("SELECT p FROM Pedido as p", Pedido.class);
            List<Pedido> pedidos = query.getResultList();

            System.out.println("------------------");
            System.out.println("Listado de pedidos");
            System.out.println("------------------");
            for (Pedido p : pedidos) {
                System.out.println(p.toString());
                for (LineaPedido lp : p.getLineas()) {
                    System.out.println("   " + lp.toString() + "   [total : " + lp.getImporteTotal() + "]");
                }
            }
            System.out.println("------------------\n");

            tx.commit();

        } catch (Exception e) {
            System.err.println("Error en pruebaConsultaPedidos");
            e.printStackTrace(System.err);

            if ((tx != null) && tx.isActive()) {
                tx.rollback();
            }
        }

        em.close();
    }*/

}