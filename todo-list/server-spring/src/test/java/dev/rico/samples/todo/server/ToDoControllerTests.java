/*
 * Copyright 2015-2018 Canoo Engineering AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.rico.samples.todo.server;

import dev.rico.samples.todo.ToDoList;

import dev.rico.client.remoting.Param;
import dev.rico.server.remoting.test.ControllerUnderTest;
import dev.rico.server.remoting.test.SpringTestNGControllerTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static dev.rico.samples.todo.TodoAppConstants.*;

@SpringBootTest(classes = ToDoServerConfiguration.class)
public class ToDoControllerTests extends SpringTestNGControllerTest {

    @Test
    public void testAddElement() {

        //given:
        ControllerUnderTest<ToDoList> controllerUnderTest = createController(TODO_CONTROLLER_NAME);

        //when:
        controllerUnderTest.getModel().getNewItemText().set("Banana");
        controllerUnderTest.invoke(ADD_ACTION);

        //then:
        Assert.assertEquals(controllerUnderTest.getModel().getItems().size() ,1);
        Assert.assertEquals(controllerUnderTest.getModel().getItems().get(0).getText() ,"Banana");
        Assert.assertEquals(controllerUnderTest.getModel().getItems().get(0).isCompleted() ,false);
    }

    @Test
    public void testChangeElementState() {

        //given:
        ControllerUnderTest<ToDoList> controllerUnderTest = createController(TODO_CONTROLLER_NAME);

        //when:
        controllerUnderTest.getModel().getNewItemText().set("Banana");
        controllerUnderTest.invoke(ADD_ACTION);
        controllerUnderTest.invoke(CHANGE_ACTION, new Param(ITEM_PARAM, "Banana"));

        //then:
        Assert.assertEquals(controllerUnderTest.getModel().getItems().get(0).isCompleted() ,true);
    }

    @Test
    public void testDeleteElement() {

        //given:
        ControllerUnderTest<ToDoList> controllerUnderTest = createController(TODO_CONTROLLER_NAME);

        //when:
        controllerUnderTest.getModel().getNewItemText().set("Banana");
        controllerUnderTest.invoke(ADD_ACTION);
        controllerUnderTest.invoke(REMOVE_ACTION, new Param(ITEM_PARAM, "Banana"));

        //then:
        Assert.assertEquals(controllerUnderTest.getModel().getItems().size() ,0);
    }

    @Test
    public void testInitialElements() {

        //given:
        ControllerUnderTest<ToDoList> controllerUnderTest = createController(TODO_CONTROLLER_NAME);

        //when:
        controllerUnderTest.getModel().getNewItemText().set("Banana");
        controllerUnderTest.invoke(ADD_ACTION);
        ControllerUnderTest<ToDoList> controllerUnderTest2 = createController(TODO_CONTROLLER_NAME);

        //then:
        Assert.assertEquals(controllerUnderTest2.getModel().getItems().size() ,1);
        Assert.assertEquals(controllerUnderTest2.getModel().getItems().get(0).getText() ,"Banana");
    }

    @Test
    public void testElementSync() {

        //given:
        ControllerUnderTest<ToDoList> controllerUnderTest = createController(TODO_CONTROLLER_NAME);
        ControllerUnderTest<ToDoList> controllerUnderTest2 = createController(TODO_CONTROLLER_NAME);


        //when:
        controllerUnderTest.getModel().getNewItemText().set("Banana");
        controllerUnderTest.invoke(ADD_ACTION);

        //then:
        Assert.assertEquals(controllerUnderTest2.getModel().getItems().size() ,1);
        Assert.assertEquals(controllerUnderTest2.getModel().getItems().get(0).getText() ,"Banana");
    }

    @Test
    public void testElementStateSync() {

        //given:
        ControllerUnderTest<ToDoList> controllerUnderTest = createController(TODO_CONTROLLER_NAME);
        ControllerUnderTest<ToDoList> controllerUnderTest2 = createController(TODO_CONTROLLER_NAME);


        //when:
        controllerUnderTest.getModel().getNewItemText().set("Banana");
        controllerUnderTest.invoke(ADD_ACTION);
        controllerUnderTest.invoke(CHANGE_ACTION, new Param(ITEM_PARAM, "Banana"));

        //then:
        Assert.assertEquals(controllerUnderTest2.getModel().getItems().size() ,1);
        Assert.assertEquals(controllerUnderTest2.getModel().getItems().get(0).isCompleted() ,true);
    }

    @Test
    public void testElementDeleteSync() {

        //given:
        ControllerUnderTest<ToDoList> controllerUnderTest = createController(TODO_CONTROLLER_NAME);
        ControllerUnderTest<ToDoList> controllerUnderTest2 = createController(TODO_CONTROLLER_NAME);


        //when:
        controllerUnderTest.getModel().getNewItemText().set("Banana");
        controllerUnderTest.invoke(ADD_ACTION);
        controllerUnderTest.invoke(REMOVE_ACTION, new Param(ITEM_PARAM, "Banana"));

        //then:
        Assert.assertEquals(controllerUnderTest2.getModel().getItems().size() ,0);
    }
}
