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
package com.canoo.dolphin.todo.server;

import com.canoo.platform.core.functional.Subscription;
import com.canoo.platform.remoting.BeanManager;
import com.canoo.platform.remoting.server.Param;
import com.canoo.platform.remoting.server.RemotingAction;
import com.canoo.platform.remoting.server.RemotingController;
import com.canoo.platform.remoting.server.RemotingModel;
import com.canoo.platform.remoting.server.event.RemotingEventBus;
import com.canoo.platform.samples.distribution.common.model.ToDoItem;
import com.canoo.platform.samples.distribution.common.model.ToDoList;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.canoo.dolphin.todo.TodoAppConstants.ADD_ACTION;
import static com.canoo.dolphin.todo.TodoAppConstants.CHANGE_ACTION;
import static com.canoo.dolphin.todo.TodoAppConstants.ITEM_PARAM;
import static com.canoo.dolphin.todo.TodoAppConstants.REMOVE_ACTION;
import static com.canoo.dolphin.todo.TodoAppConstants.TODO_CONTROLLER_NAME;
import static com.canoo.dolphin.todo.server.ToDoEventTopics.ITEM_ADDED;
import static com.canoo.dolphin.todo.server.ToDoEventTopics.ITEM_MARK_CHANGED;
import static com.canoo.dolphin.todo.server.ToDoEventTopics.ITEM_REMOVED;

@RemotingController(TODO_CONTROLLER_NAME)
public class ToDoController {

    private final BeanManager beanManager;

    private final RemotingEventBus eventBus;

    private final TodoItemStore todoItemStore;

    private final List<Subscription> subscriptions = new ArrayList<>();

    @RemotingModel
    private ToDoList toDoList;

    @Inject
    public ToDoController(BeanManager beanManager, RemotingEventBus eventBus, TodoItemStore todoItemStore) {
        this.beanManager = beanManager;
        this.eventBus = eventBus;
        this.todoItemStore = todoItemStore;
    }

    @PostConstruct
    public void onInit() {
        final Subscription changedSubscription = eventBus.subscribe(ITEM_MARK_CHANGED, message -> updateItemState(message.getData()));
        final Subscription removedSubscription = eventBus.subscribe(ITEM_REMOVED, message -> removeItem(message.getData()));
        final Subscription addedSubscritpion = eventBus.subscribe(ITEM_ADDED, message -> addItem(message.getData()));

        subscriptions.add(changedSubscription);
        subscriptions.add(removedSubscription);
        subscriptions.add(addedSubscritpion);

        todoItemStore.itemNameStream().forEach(name -> addItem(name));
    }

    @PreDestroy
    public void onDestroy() {
        subscriptions.forEach(subscription -> subscription.unsubscribe());
    }

    @RemotingAction(ADD_ACTION)
    public void onItemAddAction() {
        todoItemStore.addItem(toDoList.getNewItemText().get());
        toDoList.getNewItemText().set("");
    }

    @RemotingAction(CHANGE_ACTION)
    public void onItemStateChangedAction(@Param(ITEM_PARAM) String name) {
        todoItemStore.changeItemState(name);
    }

    @RemotingAction(REMOVE_ACTION)
    public void onItemRemovedAction(@Param(ITEM_PARAM) String name) {
        todoItemStore.removeItem(name);
    }

    private void addItem(String name) {
        toDoList.getItems().add(beanManager.create(ToDoItem.class).withText(name).withState(todoItemStore.getState(name)));
    }

    private void removeItem(String name) {
        getItemByName(name).ifPresent(i -> toDoList.getItems().remove(i));
    }

    private void updateItemState(String name) {
        getItemByName(name).ifPresent(i -> i.setCompleted(todoItemStore.getState(name)));
    }

    private Optional<ToDoItem> getItemByName(String name) {
        return toDoList.getItems().stream().filter(i -> i.getText().equals(name)).findAny();
    }
}
