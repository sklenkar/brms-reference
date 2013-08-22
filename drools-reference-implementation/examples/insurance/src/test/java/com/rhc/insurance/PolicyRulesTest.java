package com.rhc.insurance;

import static org.junit.Assert.assertEquals;



import java.util.HashSet;
import java.util.Set;

import org.drools.KnowledgeBase;
import org.drools.event.DebugWorkingMemoryEventListener;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.rhc.drools.reference.ClasspathKnowledgeBaseBuilder;
import com.rhc.drools.reference.KnowledgeBaseBuilder;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;

/**
 * This is a sample class to launch a rule.
 */

public class PolicyRulesTest {

	private static KnowledgeBaseBuilder kbuilder;

	static KnowledgeBase kbase;

	static StatefulKnowledgeSession ksession;
	//static KnowledgeRuntimeLogger logger;
	
	static KnowledgeRuntimeLogger droolsAuditLogger = null;

	@BeforeClass
	public static void setupComponent() {

		Set<String> resources = new HashSet<String>();
		resources.add( "rules/lowPHlowBH.drl" );
		resources.add( "rules/lowPHhighBH.drl" );
		resources.add( "rules/highPHlowBH.drl" );
		resources.add( "rules/highPHhighBH.drl" );

		kbuilder = new ClasspathKnowledgeBaseBuilder( resources );
		kbase = kbuilder.getKnowledgeBase();

		ksession = kbase.newStatefulKnowledgeSession();
		
		//ksession.addEventListener( new DebugAgendaEventListener() );

		//ksession.addEventListener( new DebugWorkingMemoryEventListener() );

		
		String logFileName = "/home/sklenkar/apps/brms-reference/drools-reference-implementation/examples/insurance/src/test/resources/log/log.txt";
		droolsAuditLogger = KnowledgeRuntimeLoggerFactory.newFileLogger( ksession, logFileName);
		
		
		//addFiredRulesEventListener( ksession );
		
		//droolsAuditLogger.close();

	}

	@AfterClass
	public static void closeKsession() {
		try {
			// load up the knowledge base
			// logger.close();
			ksession.dispose();

		} catch ( Throwable t ) {
			t.printStackTrace();
		}
	}

	@Test
	public void lowPHlowBHTest() {

		try {
			// now create some test data
			Member member = new Member();
			member.setSmokesRegularly( true );
			member.setFilesMedicationRegularly( false );

			Policy policy = new Policy();

			// insert objects into working memory
			FactHandle driverFH = (FactHandle) ksession.insert( member );
			FactHandle policyFH = (FactHandle) ksession.insert( policy );
			ksession.fireAllRules();
			// logger.close();
			ksession.retract( driverFH );
			ksession.retract( policyFH );

			assertEquals( "Price is 1000", 1000, policy.getPrice() );

		} catch ( Throwable t ) {
			t.printStackTrace();
		}
	}

	@Test
	public void lowPHhighBHTest() {

		try {
			// now create some test data
			Member member = new Member();
			member.setSmokesRegularly( true );
			member.setFilesMedicationRegularly( true );

			Policy policy = new Policy();

			// insert objects into working memory
			FactHandle driverFH = (FactHandle) ksession.insert( member );
			FactHandle policyFH = (FactHandle) ksession.insert( policy );
			ksession.fireAllRules();
			// logger.close();
			ksession.retract( driverFH );
			ksession.retract( policyFH );

			assertEquals( "Price is 800", 800, policy.getPrice() );

		} catch ( Throwable t ) {
			t.printStackTrace();
		}
	}

	@Test
	public void highPHlowBHTest() {

		try {
			// now create some test data
			Member member = new Member();
			member.setSmokesRegularly( false );
			member.setFilesMedicationRegularly( false );

			Policy policy = new Policy();

			// insert objects into working memory
			FactHandle memberFH = (FactHandle) ksession.insert( member );
			FactHandle policyFH = (FactHandle) ksession.insert( policy );
			ksession.fireAllRules();
			// logger.close();
			ksession.retract( memberFH );
			ksession.retract( policyFH );

			assertEquals( "Price is 500", 500, policy.getPrice() );

		} catch ( Throwable t ) {
			t.printStackTrace();
		}
	}

	@Test
	public void highPHhighBHTest() {
		try {
			// now create some test data
			Member member = new Member();
			member.setSmokesRegularly( false );
			member.setFilesMedicationRegularly( true );

			Policy policy = new Policy();

			// insert objects into working memory
			FactHandle driverFH = (FactHandle) ksession.insert( member );
			FactHandle policyFH = (FactHandle) ksession.insert( policy );
			ksession.fireAllRules();
			// logger.close();
			ksession.retract( driverFH );
			ksession.retract( policyFH );

			assertEquals( "Price is 300", 300, policy.getPrice() );

		} catch ( Throwable t ) {
			t.printStackTrace();
		}
	}

}