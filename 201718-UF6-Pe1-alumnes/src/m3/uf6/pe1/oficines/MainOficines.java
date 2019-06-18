package m3.uf6.pe1.oficines;

import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

public class MainOficines {
	public static final String DATABASE = "$objectdb/db/oficines.odb";
	public static final int MIDA_PAGINA = 10;
	public static final int COL_RESULTAT = 15;
	private static EntityManagerFactory emf;

	public static void main(String[] args) {

		// Exercici 2
		EntityManager em = initEM();


		// Exercici 3. upd
		crearDades1(em);
		crearDades2(em);
		crearDades3(em);


		// Exercici 5. query
		consultaDades1(em, 2);
		consultaDades2(em, 2, 2, 999);
		consultaDades3(em, 1);
		consultaDades4(em, 3, 999);

		// Exercici 2
		closeDatabase(em);

		System.out.println("FINAL");
	}


	public static EntityManager initEM() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(DATABASE);
		EntityManager em = emf.createEntityManager();
		return em;
	}

	public static void closeDatabase(EntityManager em) {
		emf.close();
		em.close();
	}

	public static void crearDades1(EntityManager em) {

		try {
			Banc b8000 = new Banc(8000, "Banco test");
			em.persist(b8000);
			Municipi mProva = new Municipi("Municipi prova", "Barcelonès", "Sant Boi", 8930);
			em.persist(mProva);
			Oficina oProva = new Oficina(b8000, mProva, false);
			em.persist(oProva);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw new NullPointerException("Les dades no s'han creat correctament");
		} finally {
			if (em.getTransaction().isActive()) em.getTransaction().rollback();
		}
	}

	public static void crearDades2(EntityManager em) {

		try {
			Banc b8000 = new Banc(8000, "Banco test duplicat");
			em.persist(b8000);
			Municipi mProva = new Municipi("Municipi prova 2", "Barcelonès", "Sant Boi", 8930);
			em.persist(mProva);
			Oficina oProva = new Oficina(b8000, mProva, false);
			em.persist(oProva);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw new NullPointerException("Les dades no s'han creat correctament");
		} finally {
			if (em.getTransaction().isActive()) em.getTransaction().rollback();
		}

	}

	public static void crearDades3(EntityManager em) {

		try {
			Banc b8001 = new Banc(8001, "Banco test duplicat");
			em.persist(b8001);
			Municipi mProvaNull = new Municipi(null, null, null, 8930);
			em.persist(mProvaNull);
			Oficina oProva = new Oficina(b8001, mProvaNull, false);
			em.persist(oProva);
			em.getTransaction().commit();
		} catch (Exception e) {
			throw new NullPointerException("Les dades no s'han creat correctament");
		} finally {
			if (em.getTransaction().isActive()) em.getTransaction().rollback();
		}
	}


	public static void consultaDades1(EntityManager em, int page) {

		String strQuery = "SELECT " + "m.nom" + "m.cp";
		strQuery += " FROM Municipi m ";

		TypedQuery<Object[]> query = em.createQuery(strQuery, Object[].class);

		List<Object[]> resultat = query.getResultList();
		Vector<String[]> dades = new Vector<String[]>();
		for (Object[] item : resultat) {
			dades.add(new String[]{ item[0]+"", item[2]+"" });
		}
		System.out.println(mostrarResultatConsulta("Municipi.findAll", dades));

	}

	public static void consultaDades2(EntityManager em, int page, int min, int max) {

		String strQuery = "SELECT " + "count(b.sucursals)" + "b.codi" + "b.nom";
		strQuery += " FROM Banc b ";
		strQuery += " GROUP BY b.codi";
		strQuery += " HAVING count(b.sucursals) <= max and count(b.sucursals <= min)";
		strQuery += " ORDER BY DESC count(b.sucursals), b.codi";

		TypedQuery<Object[]> query = em.createQuery(strQuery, Object[].class);

		List<Object[]> resultat = query.getResultList();
		Vector<String[]> dades = new Vector<String[]>();
		for (Object[] item : resultat) {
			dades.add(new String[]{ item[0]+"", item[2]+"" });
		}
		System.out.println(mostrarResultatConsulta("Sucursals.inRange", dades));
	}

	public static void consultaDades3(EntityManager em, int page) {

	}

	public static void consultaDades4(EntityManager em, int page, int max) {

	}

	private static String mostrarResultatConsulta(String titol, Vector<String[]> dades) {
		if (dades == null || titol == null) return System.lineSeparator()+"No es pot mostrar les dades de la consulta. Dades d'entrada incorrectes";

		String resultat = System.lineSeparator()+"RESULTATS "+titol+System.lineSeparator();

		if (dades.size() == 0) {
			resultat = titol+System.lineSeparator()+ " SENSE RESULTATS "+titol+System.lineSeparator();
			return resultat;
		}

		resultat += System.lineSeparator();
		for (int i = 0; i < dades.get(0).length; i++) resultat += StringUtils.rightPad("Camp "+(i+1), COL_RESULTAT)+"   ";
		resultat += System.lineSeparator();
		for (int i = 0; i < dades.get(0).length; i++) resultat += StringUtils.repeat("-", COL_RESULTAT)+"   ";
		resultat += System.lineSeparator();

		for (String[] fila : dades) {
			for (String dada : fila) {
				resultat += StringUtils.rightPad(StringUtils.abbreviate(dada, COL_RESULTAT), COL_RESULTAT);
				resultat += "   ";
			}
			resultat += System.lineSeparator();
		}
		return resultat+System.lineSeparator();
	}
}
