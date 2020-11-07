import {Component, HostBinding, OnInit} from '@angular/core';
import {ToasterService} from '../../common/service/toaster.service';
import {MatDialog} from '@angular/material/dialog';
import {ChampionshipService} from '../service/championship.service';
import {Championship, Player} from '../model/championship';
import {UserEdit} from '../../user/model/user-edit';
import {UserService} from '../../user/service/user.service';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';

@Component({
  selector: 'app-championship-list',
  templateUrl: './championship-list.component.html',
  styleUrls: ['./championship-list.component.scss']
})
export class ChampionshipListComponent implements OnInit {
  @HostBinding('class') cssClass = 'flex-grow d-flex';

  championships: Championship[] = [];
  selectedChampionship: Championship;

  isEdit = false;
  championshipEdit: Championship = new Championship();

  baseUserList: UserEdit[] = [];
  users: UserEdit[] = [];
  selectedUsers: UserEdit[] = [];
  userFilter = '';

  constructor(private championshipService: ChampionshipService,
              private userService: UserService,
              private toaster: ToasterService,
              private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.championshipService.getAll().subscribe(championships => {
      this.championships = championships;
    });
    this.userService.getAll().subscribe(users => {
      this.baseUserList = users;
    });
  }

  create(): void {
    this.select(new Championship());
    this.isEdit = true;
  }

  select(championship: Championship): void {
    this.selectedChampionship = championship;
    this.championshipEdit = JSON.parse(JSON.stringify(this.selectedChampionship));

    this.users = JSON.parse(JSON.stringify(this.baseUserList));
    this.selectedUsers = this.users
      .filter(user => this.championshipEdit.participants.some(par => par.id === user.id));
    this.selectedUsers.forEach(user => this.users.splice(this.users.indexOf(user), 1));

    this.isEdit = false;
  }

  edit(): void {
    this.isEdit = true;
  }

  cancel(): void {
    this.select(this.selectedChampionship);
  }

  save(): void {
    this.selectedChampionship = this.championshipEdit;
    this.selectedChampionship.participants = this.selectedUsers.map(user => user as Player);
    if (this.selectedChampionship.id !== undefined) {
      // this.championshipService.update(this.selectedChampionship).subscribe(savedUser => {
      //   this.selectUser(savedUser);
      //   this.ngOnInit();
      //   this.toaster.success('User ' + savedUser.username + ' updated !');
      // });
    } else {
      this.championshipService.create(this.selectedChampionship).subscribe(createdChampionship => {
        this.select(createdChampionship);
        this.toaster.success('Championnat ' + createdChampionship.name + ' created !');
      });
    }
  }

  delete(): void {
    //   const dialogRef = this.dialog.open(ConfirmDialogComponent, {
    //     width: '250px',
    //     data: {
    //       title: 'Character deletion',
    //       confirmMessage: 'Êtes-vous sur de vouloir supprimer le championnat ' + this.selectedChampionship.name + '?'
    //     }
    //   });
    //
    //   dialogRef.afterClosed().subscribe(result => {
    //     if (result) {
    //       this.userService.delete(this.selectedUser.id).subscribe(() => {
    //         this.ngOnInit();
    //         this.toaster.success('User ' + this.selectedUser.username + ' deleted !');
    //         this.selectedUser = null;
    //       });
    //     }
    //   });
  }

  addUserToChampionship(user: UserEdit): void {
    this.users.splice(this.users.indexOf(user), 1);
    this.selectedUsers.push(user);
    this.users = [...this.users];
  }

  removeUserFromChampionship(user: UserEdit): void {
    this.selectedUsers.splice(this.selectedUsers.indexOf(user), 1);
    this.users.push(user);
    this.users = [...this.users];
  }

  drop(event: CdkDragDrop<UserEdit[], any>): void {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex);
    }
    this.users = [...this.users];
  }
}
