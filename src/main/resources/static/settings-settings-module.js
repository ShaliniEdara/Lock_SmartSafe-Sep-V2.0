(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["settings-settings-module"],{

/***/ "./node_modules/raw-loader/index.js!./src/app/settings/change-password/change-password.component.html":
/*!***************************************************************************************************!*\
  !*** ./node_modules/raw-loader!./src/app/settings/change-password/change-password.component.html ***!
  \***************************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<div class=\"row\">\r\n    <div class=\"col-sm-12\">\r\n\r\n        <div class=\"card\">\r\n            <div class=\"card-header\"><h4> Change Password</h4></div>\r\n\r\n            <div class=\"card-body\">\r\n                <div class=\"row\">\r\n                    <div class=\"col-sm-6\">\r\n                        <form name=\"forgotPasswordForm\" #forgotPasswordForm=\"ngForm\">\r\n                            <div class=\"form-group\">\r\n                                <label for=\"oldPassword\" classs=\"form-control-label\"><span\r\n                            *ngIf=\"changePasswordDto.oldPassword == undefined ||changePasswordDto.oldPassword=='' \"\r\n                            style=\"color: red\">*</span>oldPassword</label>\r\n                                \r\n\r\n                            <input class=\"form-control input-md\" ngModel #oldPassword=\"ngModel\" required id=\"password\" name=\"oldPassword\" type=\"password\" [(ngModel)]=\"changePasswordDto.oldPassword\">\r\n                                <small class=\" text-muted danger\" *ngIf=\"!oldPassword.valid && (oldPassword.dirty || oldPassword.touched)\">\r\n                                    please enter old password\r\n                                </small>\r\n                            </div>\r\n                            <div class=\"form-group\">\r\n                                <label for=\"newPassword\" classs=\"form-control-label\"> <span\r\n                                    *ngIf=\"changePasswordDto.newPassword == undefined ||changePasswordDto.newPassword=='' \"\r\n                                    style=\"color: red\">*</span>newPassword</label>\r\n                                <input class=\"form-control input-md\" pattern=\"(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}\"  #newPassword=\"ngModel\"\r\n                                    required id=\"password\" name=\"newPassword\" type=\"password\" [(ngModel)]=\"changePasswordDto.newPassword\">\r\n                                    \r\n                                    <div class=\"text-muted danger\" *ngIf=\"newPassword.touched&& newPassword.invalid\">\r\n                                         <span *ngIf=\"newPassword.errors.required\">\r\n                                             please provide new password\r\n                                         </span>\r\n                                         <span *ngIf=\"newPassword.errors.pattern\">\r\n                                            Must contain at least one number <br> One uppercase and lowercase letter <br> Atleast 8 or more characters.\r\n                                           </span>\r\n                                    </div>\r\n                            </div>\r\n                            <div class=\"form-group\">\r\n                                <label for=\"confirmPassword\" classs=\"form-control-label\"> confirmPassword</label>\r\n                                <input class=\"form-control input-md\" ngModel #confirmPassword=\"ngModel\" required  id=\"confirmPassword\" name=\"confirmPassword\" type=\"password\" [(ngModel)]=\"changePasswordDto.confirmPassword\"\r\n                                pattern=\"{{ newPassword.value }}\">\r\n                                <div class=\"text-muted danger\" *ngIf=\"confirmPassword.touched && confirmPassword.invalid\">\r\n                                    <span *ngIf=\"confirmPassword.errors.required\">\r\n                                           please confirm password\r\n                                    </span>\r\n                                    <span *ngIf=\"confirmPassword.errors.pattern\">\r\n                                           confirm password does not match\r\n                                    </span>\r\n                                </div>\r\n                            </div>\r\n                            <div class=\"form-group text-center\">\r\n                                <button type=\"button\" class=\"btn btn-raised btn-primary mr-1\" (click)=\"onSubmitConfirm()\" routerLink=\"/pages/login\" [disabled]=\"!forgotPasswordForm.valid\">\r\n                        save\r\n                      </button><button type=\"button\" class=\"btn btn-raised btn-warning mr-1\">\r\n                        cancel\r\n                      </button>\r\n                            </div>\r\n                        </form>\r\n                    </div>\r\n                </div>\r\n            </div>\r\n        </div>\r\n    </div>\r\n</div>"

/***/ }),

/***/ "./node_modules/raw-loader/index.js!./src/app/settings/my-profile/my-profile.component.html":
/*!*****************************************************************************************!*\
  !*** ./node_modules/raw-loader!./src/app/settings/my-profile/my-profile.component.html ***!
  \*****************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<section id=\"image\">\n  <div class=\"col-md-12\">\n      <div class=\"card\">\n          <div class=\"card-header pb-2\">\n              <h4 class=\"card-title\" id=\"basic-layout-colored-form-control\">My\n                  Profile</h4>\n  \n          </div>\n          <div class=\"card-content\">\n              <div class=\"px-3\">\n  \n                  <form class=\"form\">\n                      <div class=\"form-body\">\n                          <h4 class=\"form-section\"><i class=\"ft-info\"></i> About User\n                          </h4>\n                          <div class=\"row\">\n                              <div class=\"col-md-6\">\n                                  <div class=\"form-group\">\n                                      <label for=\"firstName\">First Name</label>\n                                      <input type=\"text\" id=\"firstName\"\n                                          class=\"form-control border-primary\"\n                                          name=\"firstName\"\n                                          [(ngModel)]=\"user.firstName\" disabled>\n                                  </div>\n                              </div>\n                              <div class=\"col-md-6\">\n                                  <div class=\"form-group\">\n                                      <label for=\"lastName\">Last Name</label>\n                                      <input type=\"text\" id=\"lastName\"\n                                          class=\"form-control border-primary\"\n                                          name=\"lastName\" [(ngModel)]=\"user.lastName\" disabled>\n                                  </div>\n                              </div>\n                          </div>\n  \n                          <h4 class=\"form-section\"><i class=\"ft-mail\"></i> Contact\n                              Info & Bio</h4>\n                          <div class=\"row\">\n                              <div class=\"col-md-6\">\n                                  <div class=\"form-group\">\n                                      <label for=\"userinput5\">Email</label>\n                                      <input class=\"form-control border-primary\"\n                                          type=\"email\" id=\"email\"\n                                          [(ngModel)]=user.email name=\"email\" disabled>\n                                  </div>\n                              </div>\n  \n                              <div class=\"col-md-6\">\n                                  <div class=\"form-group\">\n                                      <label>Phone Number</label>\n                                      <input matInput\n                                          class=\"form-control border-primary\"\n                                          id=\"phoneNumber\" type=\"text\"\n                                          name=\"phoneNumber\"\n                                          [(ngModel)]=\"user.phoneNumber\"\n                                          maxlength=\"10\" numberOnly disabled>\n  \n  \n                                  </div>\n                              </div>\n                          </div>\n                          <div class=\"row\">\n  \n                                  <div class=\"col-md-6\">\n                                    <div class=\"form-group\">\n                                      <fieldset class=\"form-group\" for=\"referredBy\">\n                                        <label>Select User</label>\n                                        <select class=\"form-control\" required id=\"referredBy\" name=\"referredBy\"\n                                          [(ngModel)]=\"user.role\" disabled>\n                                          <option>Select User</option>\n                                          <option>Admin</option>\n                                          <option>User</option>\n              \n                                        </select>\n                                      </fieldset>\n                                    </div>\n                                  </div>\n                                  <div class=\"col-md-6\">\n                                    \n                                    <!-- <div *ngIf=\"user.role=='Admin'\" class=\" mb-1\">\n              \n                                      <label>PassWord</label>\n                                      <input class=\"form-control border-primary\" id=\"password\" type=\"text\" name=\"password\"\n                                        [(ngModel)]=\"user.password\">\n              \n                                    </div> -->\n                                    <div *ngIf=\"user.role=='User'\" class=\" mb-1\">\n              \n                                      <label>Department Name</label>\n                                      <input class=\"form-control border-primary\" id=\"departmentName\" type=\"text\" name=\"departmentName\"\n                                        [(ngModel)]=\"user.departmentName\" disabled>\n              \n                                    </div>\n                                  </div>\n                                </div>\n                      </div>\n                      <div class=\"form-group col-12 mb-2\">\n                          <div class=\"form-check\">\n                              <label>Status</label><br> <label class=\"radio-inline\">\n                                  <input type=\"radio\" name=\"isActive\"\n                                      [(ngModel)]=\"user.active\" value=true\n                                      ng-checked=\"!user.active\">Active\n                              </label> <label class=\"radio-inline\"> <input\n                                      type=\"radio\" name=\"isActive\"\n                                      [(ngModel)]=\"user.active\" value=false>Inactive\n                              </label>\n                          </div>\n                      </div>\n                      <!-- <div class=\"form-actions center\">\n                          <button type=\"button\"\n                              class=\"btn btn-raised btn-warning mr-1\"  routerLink=\"/user-management\">\n                              <i class=\"ft-x\"></i> Cancel\n                          </button>\n                          <button type=\"button\" class=\"btn btn-raised btn-primary\"\n                          (click)=\"updateUserManagement()\" routerLink=\"/user-management\"\n                             >\n                              <i class=\"fa fa-check-square-o\"></i> Update\n                          </button>\n                      </div>  -->\n                  </form>\n  \n              </div>\n          </div>\n      </div>\n  </div>\n  </section>"

/***/ }),

/***/ "./src/app/model/ChangePasswordDto.ts":
/*!********************************************!*\
  !*** ./src/app/model/ChangePasswordDto.ts ***!
  \********************************************/
/*! exports provided: ChangePasswordDto */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ChangePasswordDto", function() { return ChangePasswordDto; });
var ChangePasswordDto = /** @class */ (function () {
    function ChangePasswordDto() {
    }
    return ChangePasswordDto;
}());



/***/ }),

/***/ "./src/app/settings/change-password/change-password.component.scss":
/*!*************************************************************************!*\
  !*** ./src/app/settings/change-password/change-password.component.scss ***!
  \*************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL3NldHRpbmdzL2NoYW5nZS1wYXNzd29yZC9jaGFuZ2UtcGFzc3dvcmQuY29tcG9uZW50LnNjc3MifQ== */"

/***/ }),

/***/ "./src/app/settings/change-password/change-password.component.ts":
/*!***********************************************************************!*\
  !*** ./src/app/settings/change-password/change-password.component.ts ***!
  \***********************************************************************/
/*! exports provided: ChangePasswordComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "ChangePasswordComponent", function() { return ChangePasswordComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var environments_environment__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! environments/environment */ "./src/environments/environment.ts");
/* harmony import */ var app_model_ChangePasswordDto__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! app/model/ChangePasswordDto */ "./src/app/model/ChangePasswordDto.ts");
/* harmony import */ var app_service_toastr_service__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! app/service/toastr.service */ "./src/app/service/toastr.service.ts");






var ChangePasswordComponent = /** @class */ (function () {
    function ChangePasswordComponent(http, service) {
        this.http = http;
        this.service = service;
        this.httpOptions = {
            headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpHeaders"]({
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
                'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token',
                'Authorization': 'Basic ' + btoa('dashboard:$dashboardPWD$')
            })
        };
        this.changePasswordDto = new app_model_ChangePasswordDto__WEBPACK_IMPORTED_MODULE_4__["ChangePasswordDto"]();
    }
    ChangePasswordComponent.prototype.onSubmitConfirm = function () {
        var _this = this;
        this.changePasswordDto.email = localStorage.getItem('email');
        this.http.post(environments_environment__WEBPACK_IMPORTED_MODULE_3__["environment"].smartSafeAPIUrl + '/changePassword', this.changePasswordDto, this.httpOptions).subscribe(function (res) {
            console.log(res);
            //event.confirm.resolve(event.newData);
            _this.service.passwordChangeSuccess();
        }, function (err) {
            if (err.error instanceof Error) {
                console.log("Client-side error occured.");
            }
            else {
                console.log("Server-side error occured.");
            }
            _this.service.typeWarning();
        });
        console.log(JSON.stringify(this.changePasswordDto));
    };
    ChangePasswordComponent.prototype.ngOnInit = function () {
    };
    ChangePasswordComponent.ctorParameters = function () { return [
        { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"] },
        { type: app_service_toastr_service__WEBPACK_IMPORTED_MODULE_5__["NGXToastrService"] }
    ]; };
    ChangePasswordComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-change-password',
            template: __webpack_require__(/*! raw-loader!./change-password.component.html */ "./node_modules/raw-loader/index.js!./src/app/settings/change-password/change-password.component.html"),
            providers: [app_service_toastr_service__WEBPACK_IMPORTED_MODULE_5__["NGXToastrService"]],
            styles: [__webpack_require__(/*! ./change-password.component.scss */ "./src/app/settings/change-password/change-password.component.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_2__["HttpClient"], app_service_toastr_service__WEBPACK_IMPORTED_MODULE_5__["NGXToastrService"]])
    ], ChangePasswordComponent);
    return ChangePasswordComponent;
}());



/***/ }),

/***/ "./src/app/settings/my-profile/my-profile.component.scss":
/*!***************************************************************!*\
  !*** ./src/app/settings/my-profile/my-profile.component.scss ***!
  \***************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL3NldHRpbmdzL215LXByb2ZpbGUvbXktcHJvZmlsZS5jb21wb25lbnQuc2NzcyJ9 */"

/***/ }),

/***/ "./src/app/settings/my-profile/my-profile.component.ts":
/*!*************************************************************!*\
  !*** ./src/app/settings/my-profile/my-profile.component.ts ***!
  \*************************************************************/
/*! exports provided: MyProfileComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "MyProfileComponent", function() { return MyProfileComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var app_model_user__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! app/model/user */ "./src/app/model/user.ts");
/* harmony import */ var environments_environment__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! environments/environment */ "./src/environments/environment.ts");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");





var MyProfileComponent = /** @class */ (function () {
    function MyProfileComponent(http) {
        this.http = http;
        this.httpOptions = {
            headers: new _angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpHeaders"]({
                'Access-Control-Allow-Origin': '*',
                'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
                'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token',
                'Authorization': 'Basic ' + btoa('dashboard:$dashboardPWD$')
            })
        };
        this.user = new app_model_user__WEBPACK_IMPORTED_MODULE_2__["UserAccount"]();
    }
    MyProfileComponent.prototype.ngOnInit = function () {
        // this.user=JSON.parse(localStorage.getItem('user'));
        this.loadUser();
    };
    MyProfileComponent.prototype.loadUser = function () {
        var _this = this;
        //this.user=JSON.parse(localStorage.getItem('user'));  
        var email = localStorage.getItem('email');
        this.http.get(environments_environment__WEBPACK_IMPORTED_MODULE_3__["environment"].smartSafeAPIUrl + "/userByEmail?email=" + email, this.httpOptions).subscribe(function (res) {
            console.log(res);
            //event.confirm.resolve(event.newData);
            _this.user = res;
        }, function (err) {
            if (err.error instanceof Error) {
                console.log("Client-side error occured.");
            }
            else {
                console.log("Server-side error occured.");
            }
        });
        console.log(JSON.stringify(this.user));
    };
    MyProfileComponent.ctorParameters = function () { return [
        { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpClient"] }
    ]; };
    MyProfileComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-my-profile',
            template: __webpack_require__(/*! raw-loader!./my-profile.component.html */ "./node_modules/raw-loader/index.js!./src/app/settings/my-profile/my-profile.component.html"),
            styles: [__webpack_require__(/*! ./my-profile.component.scss */ "./src/app/settings/my-profile/my-profile.component.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_4__["HttpClient"]])
    ], MyProfileComponent);
    return MyProfileComponent;
}());



/***/ }),

/***/ "./src/app/settings/settings-routing.module.ts":
/*!*****************************************************!*\
  !*** ./src/app/settings/settings-routing.module.ts ***!
  \*****************************************************/
/*! exports provided: SettingsRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SettingsRoutingModule", function() { return SettingsRoutingModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _change_password_change_password_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./change-password/change-password.component */ "./src/app/settings/change-password/change-password.component.ts");
/* harmony import */ var _my_profile_my_profile_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./my-profile/my-profile.component */ "./src/app/settings/my-profile/my-profile.component.ts");





var routes = [
    {
        path: '',
        children: [
            {
                path: 'change-password',
                component: _change_password_change_password_component__WEBPACK_IMPORTED_MODULE_3__["ChangePasswordComponent"],
                data: {
                    title: 'ChangePassword'
                }
            },
        ]
    },
    {
        path: '',
        children: [
            {
                path: 'my-profile',
                component: _my_profile_my_profile_component__WEBPACK_IMPORTED_MODULE_4__["MyProfileComponent"],
                data: {
                    title: 'My Profile'
                }
            },
        ]
    },
];
var SettingsRoutingModule = /** @class */ (function () {
    function SettingsRoutingModule() {
    }
    SettingsRoutingModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"].forChild(routes)],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"]]
        })
    ], SettingsRoutingModule);
    return SettingsRoutingModule;
}());



/***/ }),

/***/ "./src/app/settings/settings.module.ts":
/*!*********************************************!*\
  !*** ./src/app/settings/settings.module.ts ***!
  \*********************************************/
/*! exports provided: SettingsModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "SettingsModule", function() { return SettingsModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _settings_routing_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./settings-routing.module */ "./src/app/settings/settings-routing.module.ts");
/* harmony import */ var _change_password_change_password_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./change-password/change-password.component */ "./src/app/settings/change-password/change-password.component.ts");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _my_profile_my_profile_component__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! ./my-profile/my-profile.component */ "./src/app/settings/my-profile/my-profile.component.ts");







var SettingsModule = /** @class */ (function () {
    function SettingsModule() {
    }
    SettingsModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            declarations: [_change_password_change_password_component__WEBPACK_IMPORTED_MODULE_4__["ChangePasswordComponent"], _my_profile_my_profile_component__WEBPACK_IMPORTED_MODULE_6__["MyProfileComponent"]],
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"], _angular_forms__WEBPACK_IMPORTED_MODULE_5__["FormsModule"],
                _settings_routing_module__WEBPACK_IMPORTED_MODULE_3__["SettingsRoutingModule"]
            ]
        })
    ], SettingsModule);
    return SettingsModule;
}());



/***/ })

}]);
//# sourceMappingURL=settings-settings-module.js.map