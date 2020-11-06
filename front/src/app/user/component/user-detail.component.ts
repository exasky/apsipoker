import {Component, HostBinding, OnInit} from '@angular/core';
import {AuthService} from '../../login/auth.service';
import {UserService} from '../service/user.service';
import {UserEdit} from '../model/user-edit';
import {ToasterService} from '../../common/service/toaster.service';
import {AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators} from '@angular/forms';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html'
})
export class UserDetailComponent implements OnInit {
  @HostBinding('class') cssClasses = 'flex-grow d-flex flex-column justify-content-center align-items-center';
  currentUser: UserEdit;

  passFormGroup: FormGroup;

  constructor(private authService: AuthService,
              private userService: UserService,
              private toaster: ToasterService,
              private formBuilder: FormBuilder) {

    this.userService.getById(this.authService.currentUserValue.id).subscribe(userEdit => this.currentUser = userEdit);
  }

  ngOnInit(): void {
    this.passFormGroup = this.formBuilder.group({
      password: ['', [Validators.required, Validators.minLength(3)]],
      password2: ['', [Validators.required]]
    }, {validator: passwordMatchValidator});
  }

  /* Shorthands for form controls (used from within template) */
  get password(): AbstractControl { return this.passFormGroup.get('password'); }
  get password2(): AbstractControl { return this.passFormGroup.get('password2'); }

  updatePassword(): void {
    if (this.passFormGroup.valid) {
      this.currentUser.password = this.password.value;
      this.userService.updatePassword(this.currentUser).subscribe(updatedUser => {
        this.currentUser = updatedUser;
        this.toaster.success('Password updated !');
      });
    }
  }

  onPasswordInput(): void {
    if (this.passFormGroup.hasError('passwordMismatch')) {
      this.password2.setErrors([{passwordMismatch: true}]);
    }
    else {
      this.password2.setErrors(null);
    }
  }

  saveUser(): void {
    this.userService.update(this.currentUser).subscribe(updatedUser => {
      this.currentUser = updatedUser;
    });
  }
}

export const passwordMatchValidator: ValidatorFn = (formGroup: FormGroup): ValidationErrors | null => {
  if (formGroup.get('password').value === formGroup.get('password2').value) {
    return null;
  }
  else {
    return {passwordMismatch: true};
  }
};
