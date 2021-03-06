package com.conx.logistics.kernel.bpm.impl.jbpm.taskserver.task.service;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.eventmessaging.EventKey;
import org.jbpm.eventmessaging.EventTriggerTransport;
import org.jbpm.eventmessaging.Payload;
import org.jbpm.task.Task;
import org.jbpm.task.event.TaskClaimedEvent;
import org.jbpm.task.event.TaskEventKey;
import org.jbpm.task.service.Operation;

import com.conx.logistics.kernel.bpm.impl.jbpm.BaseTest;

public abstract class MockEventMessagingTest extends BaseTest {    
    public void testMockTransport() throws Exception {      
        Map  vars = new HashMap();     
        vars.put( "users", users );
        vars.put( "groups", groups );        
        vars.put( "now", new Date() );                

        // One potential owner, should go straight to state Reserved
        String str = "(with (new Task()) { priority = 55, taskData = (with( new TaskData()) { } ), ";
        str += "peopleAssignments = (with ( new PeopleAssignments() ) { potentialOwners = [users['bobba' ], users['darth'] ], }),";                        
        str += "names = [ new I18NText( 'en-UK', 'This is my task name')] })";
            
        Task task = ( Task )  eval( new StringReader( str ), vars );
        taskSession.addTask( task, null );
        
        long taskId = task.getId();      
        
        EventKey key = new TaskEventKey(TaskClaimedEvent.class, taskId );        
        MockEventTriggerTransport transport = new MockEventTriggerTransport();   
        taskService.getEventKeys().register( key, transport );      
        
        
        taskSession.taskOperation( Operation.Claim, taskId, users.get( "darth" ).getId(), null, null, null );        
        
        assertEquals( 1, transport.list.size() );
        assertEquals( taskId, ((TaskClaimedEvent) ((Payload) transport.list.get(0)).get()).getTaskId() );
        assertEquals( users.get( "darth" ).getId(), ((TaskClaimedEvent) ((Payload) transport.list.get(0)).get()).getUserId() );
        
    }
    
    public static class MockEventTriggerTransport implements EventTriggerTransport {
        List<Payload> list = new ArrayList<Payload>();
        
        public void trigger(Payload payload) {
            list.add( payload );
        }

        public boolean isRemove() {
            return true;
        }                
    }
  
}