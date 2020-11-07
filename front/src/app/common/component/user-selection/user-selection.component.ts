import {Component, EventEmitter, HostBinding, Input, Output} from '@angular/core';
import {UserEdit} from '../../../user/model/user-edit';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';

@Component({
  selector: 'app-user-selection',
  templateUrl: './user-selection.component.html',
  styleUrls: ['./user-selection.component.scss']
})
export class UserSelectionComponent {
  @HostBinding('class') cssClasses = 'flex-grow d-flex';

  @Input()
  allUsersLabel;

  @Input()
  selectedUsersLabel;

  allUsersVariable: UserEdit[];

  @Input()
  set allUsers(allUsers: UserEdit[]) {
    this.allUsersVariable = allUsers;
    if (this.selectedUsers) {
      this.processRemainingUsers();
    }
  }

  get allUsers(): UserEdit[] {
    return this.allUsersVariable;
  }

  selectedUsersVariable: UserEdit[];

  @Input()
  set selectedUsers(selectedUsers: UserEdit[]) {
    this.selectedUsersVariable = selectedUsers;
    if (this.allUsers) {
      this.processRemainingUsers();
    }
  }

  get selectedUsers(): UserEdit[] {
    return this.selectedUsersVariable;
  }

  @Output()
  selectedUsersChange = new EventEmitter<UserEdit[]>();

  @Input()
  disabled: boolean;

  remainingUsers: UserEdit[];
  userFilter = '';

  private processRemainingUsers(): void {
    this.remainingUsers = JSON.parse(JSON.stringify(this.allUsers));
    this.selectedUsers.forEach(user => this.remainingUsers.splice(
      this.remainingUsers.findIndex(ru => ru.id === user.id),
      1));
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
    this.remainingUsers = [...this.remainingUsers];
    this.selectedUsersChange.emit(this.selectedUsers);
  }

  addUser(user: UserEdit): void {
    this.selectedUsers.push(user);

    this.remainingUsers.splice(this.remainingUsers.indexOf(user), 1);
    this.remainingUsers = [...this.remainingUsers];

    this.selectedUsersChange.emit(this.selectedUsers);
  }

  removeUser(user: UserEdit): void {
    this.selectedUsers.splice(this.selectedUsers.indexOf(user), 1);

    this.remainingUsers.push(user);
    this.remainingUsers = [...this.remainingUsers];

    this.selectedUsersChange.emit(this.selectedUsers);
  }
}
