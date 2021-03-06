package com.softim.learn.ejb.arquillian;

//import static org.junit.Assert.*;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.naming.NamingException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.softim.learn.ejb.app1.func.BrowseStudents;
import com.softim.learn.ejb.app1.func.StudentsManagement;
import com.softim.learn.ejb.app1.func.StudentsManagementInterfaceLocal;
import com.softim.learn.jpa.Student;
import com.softim.learn.jpa.StudentDAO;

@RunWith(Arquillian.class)
public class JNDILookupTest {

	//@Deployment
	public static JavaArchive createDeployment1() {
		JavaArchive jararch =  ShrinkWrap.create(JavaArchive.class)
				.addClass(StudentsManagement.class)
				.addClass(Student.class)
				.addClass(StudentsManagementInterfaceLocal.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
				;
		System.out.println(jararch);
		return jararch;
	}

    public static final String CURRENT_ARTEFACT_ID = "test17ejb:test17ejb:0.1.1-SNAPSHOT";
      //public static final String MOCKITO_ARTEFACT_ID = "org.mockito:mockito-all";
      public static final String TEST_CLASSES_PATH = "./target/test-classes";
      public static final String MODULE_NAME = "ejb-module.jar";
 
    @Deployment
    public static Archive<?> getKirEarArchive() {
        PomEquippedResolveStage pom = Maven.resolver().loadPomFromFile("pom.xml");
        //get project dependencies
        File[] libs = pom.importDependencies(ScopeType.COMPILE).resolve().withTransitivity().asFile();
        //add mockito dependency
        //File mockitoLib = pom.resolve(MOCKITO_ARTEFACT_ID).withTransitivity().asSingleFile();
        //get current project
        File module = pom.resolve(CURRENT_ARTEFACT_ID).withoutTransitivity().asSingleFile();
        EnterpriseArchive ear = ShrinkWrap.create(EnterpriseArchive.class).addAsLibraries(libs)
                //.addAsLibrary(mockitoLib)
                .addAsModule(module, MODULE_NAME);
        //add test classes to archive
        ear.getAsType(JavaArchive.class, MODULE_NAME).as(ExplodedImporter.class)
                .importDirectory((new File(TEST_CLASSES_PATH)));
        return ear;
    }

	
	//@Deployment
	public static EnterpriseArchive createDeployment2() {
		JavaArchive jararch = ShrinkWrap.create(JavaArchive.class, "test.jar")
				.addClass(StudentsManagement.class)
				.addClass(Student.class)
				.addClass(StudentsManagementInterfaceLocal.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		EnterpriseArchive arch = ShrinkWrap.create(EnterpriseArchive.class, "test.ear").addAsLibrary(jararch)
		// .addAsLibraries(new
		// File("/C:/java/eclipselink/eclipselink-2.5.1/commonj.sdo.jar"))
		// .addAsLibraries(new
		// File("/C:/java/eclipselink/eclipselink-2.5.1/eclipselink.jar"))
		// .addAsLibraries(new
		// File("/C:/java/eclipselink/eclipselink-2.5.1/javax.persistence.jar"))
		// .addAsLibraries(new
		// File("/C:/MySQL/Connector J 5.1.30/mysql-connector-java-5.1.30-bin.jar"))
		// .addAsLibraries(new File("lib/test9-0.0.1-SNAPSHOT.jar"))
		// .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
		// .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
		// .addAsManifestResource(new
		// File("ejbModule/META-INF/persistence.xml"), "persistence.xml")
		// .addAsResource(new File("ejbModule/META-INF/persistence.xml"),
		// "persistence.xml")
		;
		System.out.println(arch.toString(true));
		return arch;
	}

	@EJB
	StudentsManagementInterfaceLocal studentsManagement;

	@Test
	public void testStudentsManagementIniected() {
		System.out.println("Before test testStudentsManagementIniected");
		assertNotNull(studentsManagement);
		System.out.println("After test testStudentsManagementIniected");
	}

	@Test
	public void testStudentDAOIniected() {
		System.out.println("Before test testStudentDAOIniected");
		assertTrue(studentsManagement.hasStudenDAO());
		System.out.println("After test testStudentDAOIniected");
	}
	
//	@Test
//	public void geterateStudents() throws NamingException, SQLException{
//		Generate10kStudnets gens = new Generate10kStudnets();
//		gens.truncate();
//		gens.create10kStudents();
//	}
	
	@Test
	public void browseStudentsByPage() throws NamingException, SQLException, InterruptedException{
		System.out.println("sleep 20s");
		Thread.sleep(20000);
		long fs=Runtime.getRuntime().freeMemory();
		System.out.println("browseStudentsByPage start");
		BrowseStudents bs=new BrowseStudents();
		bs.initBrowsing();
		int pg=1;
		List<Student> sl = bs.getPage(pg);
		while (!bs.isCurrentPageEmpty()){
			//System.out.println(pg+" : "+sl.size());
			pg++;
			if (pg % 1000 == 0){
				System.out.println("pos : "+(pg*20));
				Thread.sleep(400);
			}
			sl = bs.getPage(pg);
		}
		System.out.println("browseStudentsByPage end, mem used="+(fs-Runtime.getRuntime().freeMemory())/1000000);
		
		fs=Runtime.getRuntime().freeMemory();
		System.out.println("readAllStudents start");
		bs=new BrowseStudents();
		bs.initBrowsing();
		int pos=1;
		ArrayList<Student> sal = new ArrayList<Student>();
		Student stud=bs.getStudentAtPos(pos);
		
		while (stud!=null){
			sal.add(stud);
			pos++;
			if (pos % 20000 == 0){
				System.out.println("pos : "+pos);
				Thread.sleep(400);
			}
			stud=bs.getNextStudent();
		}
		System.out.println("readAllStudents end, mem used="+(fs-Runtime.getRuntime().freeMemory())/1000000);
	}
	
}
