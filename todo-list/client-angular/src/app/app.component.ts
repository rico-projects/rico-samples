import { Component, ApplicationRef, OnInit } from '@angular/core';
import { RicoService } from 'rico-angular';
import { ControllerProxy } from 'rico-angular/lib/controller-proxy';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  static LOGGER;

  controllerProxy: ControllerProxy;
  ricoService: RicoService;
  appRef: ApplicationRef;

  model: any = {};

  constructor(ricoService: RicoService, appRef: ApplicationRef) {
    this.ricoService = ricoService;
    this.appRef = appRef;
    AppComponent.LOGGER = ricoService.getLogger('AppComponent');
  }

  ngOnInit() {
    AppComponent.LOGGER.info('Component initialized');
    this.ricoService.connect('http://localhost:8080/todo-app/remoting', this.appRef).then(() => {

      this.ricoService.createController('ToDoController').then((controllerProxy) => {
        AppComponent.LOGGER.info('received proxy after createController:', controllerProxy);
        this.model = controllerProxy.model;
        this.appRef.tick();
        this.controllerProxy = controllerProxy;
      }).catch((error) => {
        AppComponent.LOGGER.error(error);
      });
    });
  }

  addTask() {
    AppComponent.LOGGER.info('Add task', this.model);
    this.controllerProxy.invoke('add');
  }

  changeState(item: string) {
    AppComponent.LOGGER.info('complete task', item);
    this.controllerProxy.invoke('change', {'item': item});
  }

  remove(item: string) {
    AppComponent.LOGGER.info('remove task', item);
    this.controllerProxy.invoke('remove', {'item': item});
  }
}
