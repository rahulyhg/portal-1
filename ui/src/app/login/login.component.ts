import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { AuthenticationService, AuthService, ResetPasswordService,
AlertService } from '../_services/index';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
    model: any = {};
    registerModel: any = {};
    loading = false;
    error = '';
    showModel = false;
    resetFailed = false;
    constructor(
        private router: Router,
        private authenticationService: AuthService,
        private resetPasswordService: ResetPasswordService,
        private alertService: AlertService) { }
    ngOnInit() {
        // reset login status
        this.authenticationService.logout();
    }
    login_old() {
        this.loading = true;
        this.authenticationService.login(this.model.username, this.model.password)
            .subscribe(result => {
                if (result === true) {
                    // login successful
                    this.router.navigate(['/']);
                } else {
                    // login failed
                    // this.error = 'Username or password is incorrect';
                    this.alertService.error('Username or password is incorrect');
                    this.loading = false;
                }
            });
    }

    login() {
        this.loading = true;
        this.authenticationService.login(this.model.username, this.model.password)
            .subscribe(
            (result: boolean) => {
                 if (result === true) {
                    // login successful
                    this.router.navigate(['/']);
                } else {
                    // login failed
                    // this.error = 'Username or password is incorrect';
                    this.alertService.error('Username or password is incorrect');
                    this.loading = false;
                }
            },
            (err) => {
                console.error(err);
                this.alertService.error('Username or password is incorrect (or) Your account is not yet activate.');
                // this.error = 'Username or password is incorrect';
                this.loading = false;
            },
            () => console.log('login successful')
            );
    }
    gotoRegister() {
        this.router.navigate(['register']);
    }
    resetPass(username: string) {
    if (username === undefined || username === null) {
        // write validation
        this.resetFailed = true;
    } else {
        this.resetPasswordService.resetPasswordEmail(username)
            .subscribe(result => {
            if (result === true) {
                this.alertService.success('We have sent password reset email, Please check your email');
                this.resetFailed = false;
            } else {
                this.alertService.error('RegistarationId doesn`t exist (or) Some thing went wrong, try again');
            }
        },
        (err) => {
        console.error(err);
        this.alertService.error('Some thing went wrong, try again');
        },
        );
    }
    }
}
