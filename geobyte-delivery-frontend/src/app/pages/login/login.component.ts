import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Login } from 'src/app/models/login';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  constructor(private authService: AuthService) {}

  doLogin(password: string, email: string) {
    const payload: Login = {
      email: email,
      password: password,
    };

    this.authService.doLogin(payload).subscribe({
      next: (value) => {
        console.log(value);
      },
      error: (err: HttpErrorResponse) => {},
    });
  }
}
