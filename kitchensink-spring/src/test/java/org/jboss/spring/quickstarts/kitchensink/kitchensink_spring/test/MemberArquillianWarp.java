package org.jboss.spring.quickstarts.kitchensink.kitchensink_spring.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.warp.ClientAction;
import org.jboss.arquillian.warp.ServerAssertion;
import org.jboss.arquillian.warp.Warp;
import org.jboss.arquillian.warp.WarpTest;
import org.jboss.arquillian.warp.extension.servlet.AfterServlet;
import org.jboss.arquillian.warp.extension.spring.SpringMvcResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.jboss.spring.quickstarts.kitchensink.kitchensink_spring.domain.Member;
import org.jboss.spring.quickstarts.kitchensink.kitchensink_spring.mvc.MemberController;
import org.jboss.spring.quickstarts.kitchensink.kitchensink_spring.repo.MemberDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;


@RunWith(Arquillian.class)
@WarpTest
public class MemberArquillianWarp {

    @Drone
    WebDriver browser;

    @ArquillianResource
    URL contextPath;

    @Deployment
    public static WebArchive createDeployment() {

        File[] libs = DependencyResolvers.use(MavenDependencyResolver.class)
                .loadMetadataFromPom("pom.xml")
                .artifacts("org.springframework:spring-webmvc:3.1.1.RELEASE")
                .artifacts("javax.validation:validation-api:1.0.0.GA")
                .artifacts("org.hibernate:hibernate-validator:4.1.0.Final")
                .artifacts("org.jboss.arquillian.extension:arquillian-warp-spring:1.0.0.Alpha2")
                .artifacts("org.springframework:spring-tx:3.1.1.RELEASE")
                .resolveAsFiles();

        return ShrinkWrap.create(WebArchive.class, "spring-test.war")
                .addPackage(MemberDao.class.getPackage())
                .addPackage(Member.class.getPackage())
                .addPackage(MemberController.class.getPackage())
                .addAsWebInfResource("warp/web.xml", "web.xml")
                .addAsWebInfResource("jbossas-ds.xml")
                .addAsWebInfResource("warp/welcome-servlet.xml", "welcome-servlet.xml")
                .addAsWebInfResource("warp/WEB-INF/views/index.jsp", "jsp/index.jsp")
                .addAsResource("import.sql")
                .addAsResource("META-INF/test-persistence-arq.xml", "META-INF/persistence.xml")
                .addAsLibraries(libs);
    }
    

    @Test
    @RunAsClient
    public void test() {
    	Warp.execute(new ClientAction() {

            @Override
            public void action() {
            	browser.navigate().to(contextPath);
          }
        }).verify(new WelcomeControllerVerification());
    }
    
    public static class WelcomeControllerVerification extends ServerAssertion {

        private static final long serialVersionUID = 1L;

        @SpringMvcResource
        private ModelAndView modelAndView;
        
        @SpringMvcResource
        private Errors errors;

        @AfterServlet
        public void testWelcome() {
        	assertNotNull(modelAndView);
        	assertNotNull(errors);
        	assertNotNull("No Model in the view", modelAndView.getModel());
        	assertNotNull(modelAndView.getModel().get("members"));
        	@SuppressWarnings("unchecked")
			List<Member> members = (List<Member>) modelAndView.getModel().get("members");
        	assertEquals(1, members.size());
        	assertEquals("John Smith", members.get(0).getName());
        }
    }
    
    
}