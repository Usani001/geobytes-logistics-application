import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { SignUp } from 'src/app/models/sign-up';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css'],
})
export class SignUpComponent {
  constructor(private authService: AuthService) {}

  doSignUp(fName: string, lName: string, email: string, password: string) {
    const payload: SignUp = {
      email: email,
      firstName: fName,
      lastName: lName,
      password: password,
    };
    this.authService.doSignup(payload).subscribe({
      next: (value) => {
        console.log(value);
      },
      error: (err: HttpErrorResponse) => {
        console.log(err.error.message);
      },
    });
  }
}
