package org.jboss.spring.quickstarts.kitchensink.kitchensink_spring.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.spring.integration.test.annotation.SpringConfiguration;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.jboss.spring.quickstarts.kitchensink.kitchensink_spring.domain.Member;
import org.jboss.spring.quickstarts.kitchensink.kitchensink_spring.mvc.MemberController;
import org.jboss.spring.quickstarts.kitchensink.kitchensink_spring.repo.MemberDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(Arquillian.class)
@SpringConfiguration({"applicationContext.xml"})
@TransactionConfiguration(transactionManager="transactionManager")
@Transactional
public class MemberArquillian {

	
    @Deployment
    public static WebArchive createTestArchive() {
        WebArchive archive =  ShrinkWrap.create(WebArchive.class, "spring-test.war")
                .addPackage(MemberDao.class.getPackage())
                .addPackage(Member.class.getPackage())
                .addPackage(MemberController.class.getPackage())
                .addAsWebInfResource("web.xml")
                .addAsWebInfResource("jbossas-ds.xml")
                .addAsResource("applicationContext.xml")
                .addAsResource("import.sql")
                .addAsResource("META-INF/test-persistence-arq.xml", "META-INF/persistence.xml")
                .addAsLibraries(springDependencies());
        return archive;
    }

    /**
     * The injected {@link EmployeeController}.
     */
    @Autowired
    private MemberDao memberDao;
    
    @Test
    public void testFindById()
    {
        Member member = memberDao.findById(0l);

        Assert.assertEquals("John Smith", member.getName());
        Assert.assertEquals("john.smith@mailinator.com", member.getEmail());
        Assert.assertEquals("2125551212", member.getPhoneNumber());
        return;
    }

    @Test
    public void testFindByEmail()
    {
        Member member = memberDao.findByEmail("john.smith@mailinator.com");

        Assert.assertEquals("John Smith", member.getName());
        Assert.assertEquals("john.smith@mailinator.com", member.getEmail());
        Assert.assertEquals("2125551212", member.getPhoneNumber());
        return;
    }

    @Test
    public void testRegister()
    {
        Member member = new Member();
        member.setEmail("jane.doe@mailinator.com");
        member.setName("Jane Doe");
        member.setPhoneNumber("2125552121");

        memberDao.register(member);
        Long id = member.getId();
        Assert.assertNotNull(id);
        
        Assert.assertEquals(2, memberDao.findAllOrderedByName().size());
        Member newMember = memberDao.findById(id);

        Assert.assertEquals("Jane Doe", newMember.getName());
        Assert.assertEquals("jane.doe@mailinator.com", newMember.getEmail());
        Assert.assertEquals("2125552121", newMember.getPhoneNumber());
        return;
    }
    //For some Reason the other tests are not rolling back
    @Test
    public void testFindAllOrderedByName()
    {
    	List<Member> members = memberDao.findAllOrderedByName();
        Member member = new Member();
        member.setEmail("jane.doe2@mailinator.com");
        member.setName("Jane Doe");
        member.setPhoneNumber("2125552122");
        memberDao.register(member);

        members = memberDao.findAllOrderedByName();
        Assert.assertEquals(2, members.size());
        Member newMember = members.get(0);

        Assert.assertEquals("Jane Doe", newMember.getName());
        Assert.assertEquals("jane.doe2@mailinator.com", newMember.getEmail());
        Assert.assertEquals("2125552122", newMember.getPhoneNumber());
        return;
    }
    

    /**
     * <p>Retrieves the dependencies.</p
     * 
     * @return the array of the dependencies
     */
    public static File[] springDependencies() {

        ArrayList<File> files = new ArrayList<File>();

        files.addAll(resolveDependencies("org.springframework:spring-context:3.1.1.RELEASE"));
        files.addAll(resolveDependencies("org.springframework:spring-orm:3.1.1.RELEASE"));
        files.addAll(resolveDependencies("org.springframework:spring-tx:3.1.1.RELEASE"));
        files.addAll(resolveDependencies("org.springframework:spring-test:3.1.1.RELEASE"));
        files.addAll(resolveDependencies("org.springframework:spring-webmvc:3.1.1.RELEASE"));
        files.addAll(resolveDependencies("javassist:javassist:3.6.0.GA"));
        files.addAll(resolveDependencies("com.h2database:h2:1.3.166"));
        files.addAll(resolveDependencies("org.jboss.arquillian.extension:arquillian-drone-api:1.1.0.CR1"));
        List<File> selenium = resolveDependencies("org.seleniumhq.selenium:selenium-java:2.24.1");
        files.addAll(selenium.subList(0, selenium.size()-1));
        files.addAll(resolveDependencies("org.jboss.arquillian.extension:arquillian-warp-spring:1.0.0.Alpha2"));

        return files.toArray(new File[files.size()]);
    }

    /**
* <p>Resolves the given artifact by it's name with help of maven build system.</p>
*
* @param artifactName the fully qualified artifact name
*
* @return the resolved files
*/
    public static List<File> resolveDependencies(String artifactName) {
        MavenDependencyResolver mvnResolver = DependencyResolvers.use(MavenDependencyResolver.class);

        mvnResolver.loadMetadataFromPom("pom.xml");

        return Arrays.asList(mvnResolver.artifacts(artifactName).resolveAsFiles());
    }
}