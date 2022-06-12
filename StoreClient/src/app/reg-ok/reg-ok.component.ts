import { IdentityManagerService } from './../utility/identity-manager.service';
import { KeycloakProfile } from 'keycloak-js';
import { Component, OnInit } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-reg-ok',
  templateUrl: './reg-ok.component.html',
  styleUrls: ['./reg-ok.component.css']
})
export class RegOKComponent implements OnInit {
  identity: IdentityManagerService

  constructor() {
    this.identity = IdentityManagerService.getInstance();
  }

  ngOnInit() { }

}
