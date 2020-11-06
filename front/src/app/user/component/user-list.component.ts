import {Component, HostBinding, OnInit} from '@angular/core';
import {UserService} from '../service/user.service';
import {UserEdit} from '../model/user-edit';
import {MatDialog} from '@angular/material/dialog';
import {ConfirmDialogComponent} from '../../common/dialog/confirm-dialog.component';
import {ToasterService} from '../../common/service/toaster.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html'
})
export class UserListComponent implements OnInit {
  @HostBinding('class') cssClass = 'flex-grow d-flex';

  users: UserEdit[];

  selectedUser: UserEdit;

  isEdit = false;
  userEdit: UserEdit = new UserEdit();

  constructor(private userService: UserService,
              private toaster: ToasterService,
              private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.userService.getAll().subscribe(users => this.users = users
      .sort((a, b) => (a.username.toLowerCase() < b.username.toLowerCase()) ? -1 : 1));
  }

  selectUser(user: UserEdit): void {
    this.selectedUser = user;
    this.userEdit = JSON.parse(JSON.stringify(this.selectedUser));
    this.isEdit = false;
  }

  createUser(): void {
    this.selectUser(new UserEdit());
    this.isEdit = true;
  }

  editUser(): void {
    this.isEdit = true;
  }

  cancel(): void {
    this.selectUser(this.selectedUser);
  }

  deleteUser(): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '250px',
      data: {title: 'Character deletion', confirmMessage: 'Are ou sure you want to delete ' + this.selectedUser.username + '?'}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.userService.delete(this.selectedUser.id).subscribe(() => {
          this.ngOnInit();
          this.toaster.success('User ' + this.selectedUser.username + ' deleted !');
          this.selectedUser = null;
        });
      }
    });
  }

  saveUser(): void {
    this.selectedUser = this.userEdit;
    if (this.selectedUser.id !== undefined) {
      this.userService.update(this.selectedUser).subscribe(savedUser => {
        this.selectUser(savedUser);
        this.ngOnInit();
        this.toaster.success('User ' + savedUser.username + ' updated !');
      });
    } else {
      this.userService.create(this.selectedUser).subscribe(createdUser => {
        this.selectUser(createdUser);
        this.ngOnInit();
        this.toaster.success('User ' + createdUser.username + ' created !');
      });
    }
  }

  resetPassword(): void {
    this.userService.updatePassword(this.userEdit).subscribe(updatedUser => {
      this.selectUser(updatedUser);
      this.ngOnInit();
      this.toaster.success('Password updated !');
    });
  }
}
