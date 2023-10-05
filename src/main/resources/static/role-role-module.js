(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["role-role-module"],{

/***/ "./node_modules/raw-loader/index.js!./src/app/role/createrole/createrole.component.html":
/*!*************************************************************************************!*\
  !*** ./node_modules/raw-loader!./src/app/role/createrole/createrole.component.html ***!
  \*************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<!-- <ngx-spinner></ngx-spinner> -->\n<section id=\"image\">\n  <div class=row>\n    <div class=\" col-md-12 \">\n      <div class=\"row\">\n        <div class=\"container-fluid\">\n          <div class=\"row text-left\">\n            <div class=\"col-md-8\">\n              <div class=\"content-header\" style=\"font-size: 20px\">Create</div>\n              <br>\n              <p class=\"content-sub-header\"> <a routerLink=\"/dashboard/dashboard1\"><span\n                    style=\"font-size: 15px\">Home</span></a>\n                <span style=\"font-size: 15px\">/Role</span>\n\n              </p>\n            </div>\n          </div>\n        </div>\n      </div>\n      <div class=\"card\">\n        <div class=\"card-header\">\n          <div class=\"card-body\">\n            <div class=\"form-group\">\n              <div class=\"col-md-12\">\n                <form class=\"form\" #addClassForm=\"ngForm\" novalidate>\n                  <div class=\"form-body\">\n\n                    <div class=\"row\">\n                      <div class=\"col-md-6\">\n                        <div class=\"form-group\">\n                          <label for=\"name\"><span *ngIf=\"role.name == undefined || role.name =='' \"\n                              style=\"color: red\">*</span>Enter Role Name\n                          </label>\n                          <span class=\" text-muted danger\" *ngIf=\"!name.valid && (name.dirty || name.touched)\">\n                            is required!\n                          </span>\n                          <input type=\"text\" id=\"name\" class=\"form-control border-primary\" name=\"name\"\n                            [(ngModel)]=\"role.name\" ngModel #name=\"ngModel\" required>\n\n                        </div>\n                      </div>\n                      <div class=\"col-md-6\">\n                        <div class=\"form-group\">\n                          <label for=\"description\"><span *ngIf=\"role.description == undefined || role.description =='' \"\n                              style=\"color: red\">*</span>Description\n                          </label>\n                          <span class=\" text-muted danger\"\n                            *ngIf=\"!description.valid && (description.dirty || description.touched)\">\n                            is required!\n                          </span>\n                          <input type=\"text\" id=\"description\" class=\"form-control border-primary\" name=\"description\"\n                            [(ngModel)]=\"role.description\" ngModel #description=\"ngModel\" required>\n\n                        </div>\n                      </div>\n\n                    </div>\n                    <div class=\"col-md-6\">\n                      <fieldset class=\"form-group\">\n                          <label for=\"features\"> <span\n                                  *ngIf=\"role.features == undefined || role.features =='' \"\n                                  style=\"color: red\">*</span>Features\n                          </label>\n                          <span class=\" text-muted danger\"\n                              *ngIf=\"!features.valid && (features.dirty || features.touched)\">\n                              is required!\n                          </span>\n                          <ng-select [items]=\"roleFeatures\" name=\"features\"\n                              bindLabel=\"name\" placeholder=\"Select features\" appendTo=\"body\"\n                              multiple=\"true\" [(ngModel)]=\"role.features\"\n                              [ngModelOptions]=\"{standalone: true}\"  ngModel\n                              #features=\"ngModel\" required>\n                              \n                          </ng-select>\n\n                      </fieldset>\n                  </div>\n                    <div class=\"col-md-4\">\n                      <div class=\"form-group\">\n                        <label class=\"d-block\">Status</label>\n                        <div class=\"input-group\">\n                          <div class=\"custom-control custom-radio d-inline-block float-left\">\n                            <input type=\"radio\" checked id=\"active\" name=\"active\" class=\"custom-control-input\"\n                              [value]=true [(ngModel)]=\"role.active\">\n                            <label class=\"custom-control-label\" for=\"active\">Active</label>\n                          </div>\n                          <div class=\"custom-control custom-radio d-inline-block float-left ml-1\">\n                            <input type=\"radio\" id=\"inactive\" name=\"inactive\" class=\"custom-control-input\" [value]=false\n                              [(ngModel)]=\"role.active\">\n                            <label class=\"custom-control-label\" for=\"inactive\">In Active</label>\n                          </div>\n                        </div>\n                      </div>\n                    </div>\n                  </div>\n                  <div class=\"form-actions center\">\n\n                    <button type=\"button\" class=\"btn btn-raised btn-primary mr-1\" (click)=\"addRole()\"\n                      [disabled]=\"!addClassForm.valid\">\n                      <i class=\"fa fa-check-square-o\"></i> Create\n                    </button>\n                    <button type=\"button\" class=\"btn btn-raised btn-warning\">\n                      <i class=\"ft-x\"></i> Cancel\n                    </button>\n                  </div>\n                </form>\n\n              </div>\n            </div>\n          </div>\n        </div>\n      </div>\n    </div>\n  </div>\n</section>\n<section id=\"image\">\n  <div class=row>\n    <div class=\" col-md-12 \">\n      <div class=\"card\">\n\n        <div class=\"card-body\">\n          <div class=\"form-group\">\n            <section id=\"simple\">\n\n              <!-- <div class=\"col-sm-6\">\n                <form role=\"search\">\n                  <div class=\"position-relative has-icon-right\">\n                    <input type=\"text\" class=\"form-control round\" placeholder=\"Search\" name=\"search\"\n                      [(ngModel)]=\"searchText\" autocomplete=\"off\">\n                    <div class=\"form-control-position\">\n                      <i class=\"ft-search\"></i>\n                    </div>\n                  </div>\n                </form>\n              </div> -->\n              <br>\n              <div class=\"row text-left\">\n\n                <div class=\"col-sm-12\">\n                  <table class=\"table table-responsive-md text-center\">\n                    <thead>\n                      <tr>\n                        <th>S.no</th>\n                        <th>Role Name</th>\n                        <!-- <th>Edit</th> -->\n                        <!-- <th>Status</th> -->\n                        <th>Features</th>\n                      </tr>\n                    </thead>\n                    <!-- <tbody\n                        *ngFor=\"let role of roles| slice: (page-1) * pageSize : +((page-1) * pageSize) + +pageSize; index as i\"> -->\n                    <tbody *ngFor=\"let role of roles;index as i\">\n                      <tr>\n                        <!-- <td>{{i+1+(page-1) * pageSize}}</td> -->\n                        <td>{{i+1}}</td>\n                        <td>{{role.name}}</td>\n                        <!-- <td>\n                          <a class=\"success p-0\" data-original-title=\"\" title=\"\" (click)=\"editRole(role)\"\n                            placement=\"bottom\" ngbTooltip=\"Edit\">\n                            <i class=\"ft-edit-2 font-medium-3 mr-2\"></i>\n                          </a>\n                          <a class=\"danger p-0\" data-original-title=\"\" title=\"\"\n                                                      (click)=\"deleteClass(class.id)\">\n                                                    \n                                                      <i class=\"icon-trash font-medium-3 mr-2\"></i>\n                                                  </a>\n                        </td> -->\n                        <!-- <td><button type=\"button\" class=\"btn btn-raised\" disabled style=\"cursor:default\"\n                            [ngClass]=\"{' btn-primary' : role.active,'btn-warning' :!role.active }\">\n                            {{ role.active ? 'Active' : 'Inactive' }}\n\n                          </button></td> -->\n                          <td>{{role.features}}</td>\n\n                      </tr>\n                    </tbody>\n                  </table>\n                  <!-- <div *ngIf=\"searchText != ''\">\n                    <table class=\"table table-responsive-md text-center\">\n                      <thead>\n                        <tr>\n                          <th>S.no</th>\n                          <th>Class Name</th>\n                          <th>Edit</th>\n                          <th>Status</th>\n\n                        </tr>\n                      </thead>\n                      <tbody *ngFor=\"let class of classes| filter:searchText ; index as i\">\n                        <tr>\n                          <td>{{i+1+(page-1) * pageSize}}</td>\n                          <td>{{class.name}}</td>\n                          <td>\n                            <a class=\"success p-0\" data-original-title=\"\" title=\"\" (click)=\"editClass(class)\"\n                              placement=\"bottom\" ngbTooltip=\"Edit\">\n                              <i class=\"ft-edit-2 font-medium-3 mr-2\"></i>\n                            </a>\n                            <a class=\"danger p-0\" data-original-title=\"\" title=\"\"\n                                                        (click)=\"deleteClass(class.id)\">\n                                                      \n                                                        <i class=\"icon-trash font-medium-3 mr-2\"></i>\n                                                    </a>\n                          </td>\n                          <td><button type=\"button\" class=\"btn btn-raised\" disabled style=\"cursor:default\"\n                              [ngClass]=\"{' btn-primary' : class.active,'btn-warning' :!class.active }\">\n                              {{ class.active ? 'Active' : 'Inactive' }}\n\n                            </button></td>\n\n\n                        </tr>\n                      </tbody>\n                    </table>\n                  </div> -->\n                  <!-- <div class=\"row\">\n                    <div class=\"col-sm-6\">\n                      <div class=\"formgroup\">\n                        <ngb-pagination [(page)]=\"page\" [pageSize]=\"pageSize\" [collectionSize]=\"classes.length\"\n                          [ellipses]=\"false\" [boundaryLinks]=\"true\"></ngb-pagination>\n                      </div>\n                    </div>\n                    <div class=\"col-sm-6\">\n                      <div class=\"formgroup\">\n                        Items per Page:\n                        <select (change)=\"handlePageSizeChange($event)\">\n                          <option *ngFor=\"let pageSize of pageSizes\" [ngValue]=\"pageSize\">\n                            {{ pageSize }}\n                          </option>\n                        </select>\n                      </div>\n                    </div>\n                  </div> -->\n                </div>\n              </div>\n            </section>\n          </div>\n        </div>\n      </div>\n    </div>\n  </div>\n</section>"

/***/ }),

/***/ "./src/app/role/createrole/createrole.component.scss":
/*!***********************************************************!*\
  !*** ./src/app/role/createrole/createrole.component.scss ***!
  \***********************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL3JvbGUvY3JlYXRlcm9sZS9jcmVhdGVyb2xlLmNvbXBvbmVudC5zY3NzIn0= */"

/***/ }),

/***/ "./src/app/role/createrole/createrole.component.ts":
/*!*********************************************************!*\
  !*** ./src/app/role/createrole/createrole.component.ts ***!
  \*********************************************************/
/*! exports provided: CreateroleComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CreateroleComponent", function() { return CreateroleComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var app_model_role__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! app/model/role */ "./src/app/model/role.ts");
/* harmony import */ var app_service_toastr_service__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! app/service/toastr.service */ "./src/app/service/toastr.service.ts");
/* harmony import */ var environments_environment__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! environments/environment */ "./src/environments/environment.ts");







var CreateroleComponent = /** @class */ (function () {
    function CreateroleComponent(http, router, service, changeDetectorRefs) {
        this.http = http;
        this.router = router;
        this.service = service;
        this.changeDetectorRefs = changeDetectorRefs;
        this.role = new app_model_role__WEBPACK_IMPORTED_MODULE_4__["Role"]();
        this.roleFeatures = ['All', 'InsertBills', 'Doors', 'Admin', 'ChangeRequestDoors', 'StandBank', 'OTPScreen', 'Valut'
        ];
        this.searchText = '';
    }
    CreateroleComponent.prototype.getRoleList = function () {
        return this.http.get(environments_environment__WEBPACK_IMPORTED_MODULE_6__["environment"].smartSafeAPIUrl + '/role/all');
    };
    CreateroleComponent.prototype.getAllRolesList = function () {
        var _this = this;
        return this.getRoleList().
            subscribe(function (data) {
            console.log(data);
            _this.roles = data;
            _this.changeDetectorRefs.markForCheck();
        });
    };
    CreateroleComponent.prototype.addRole = function () {
        var _this = this;
        var role = new app_model_role__WEBPACK_IMPORTED_MODULE_4__["Role"]();
        role.features = this.SelectedFeatureNames;
        this.http.post(environments_environment__WEBPACK_IMPORTED_MODULE_6__["environment"].smartSafeAPIUrl + '/role/', this.role).subscribe(function (res) {
            console.log(res);
            //event.confirm.resolve(event.newData);
            _this.service.addSuccess();
        }, function (err) {
            if (err.error instanceof Error) {
                console.log("Client-side error occured.");
            }
            else {
                console.log("Server-side error occured.");
            }
            _this.service.typeWarning();
        });
        console.log(JSON.stringify(this.role));
        this.getAllRolesList();
    };
    CreateroleComponent.prototype.ngOnInit = function () {
        this.getAllRolesList();
    };
    CreateroleComponent.ctorParameters = function () { return [
        { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"] },
        { type: _angular_router__WEBPACK_IMPORTED_MODULE_3__["Router"] },
        { type: app_service_toastr_service__WEBPACK_IMPORTED_MODULE_5__["NGXToastrService"] },
        { type: _angular_core__WEBPACK_IMPORTED_MODULE_2__["ChangeDetectorRef"] }
    ]; };
    CreateroleComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_2__["Component"])({
            selector: 'app-createrole',
            template: __webpack_require__(/*! raw-loader!./createrole.component.html */ "./node_modules/raw-loader/index.js!./src/app/role/createrole/createrole.component.html"),
            providers: [app_service_toastr_service__WEBPACK_IMPORTED_MODULE_5__["NGXToastrService"]],
            styles: [__webpack_require__(/*! ./createrole.component.scss */ "./src/app/role/createrole/createrole.component.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"],
            _angular_router__WEBPACK_IMPORTED_MODULE_3__["Router"],
            app_service_toastr_service__WEBPACK_IMPORTED_MODULE_5__["NGXToastrService"],
            _angular_core__WEBPACK_IMPORTED_MODULE_2__["ChangeDetectorRef"]])
    ], CreateroleComponent);
    return CreateroleComponent;
}());



/***/ }),

/***/ "./src/app/role/role-routing.module.ts":
/*!*********************************************!*\
  !*** ./src/app/role/role-routing.module.ts ***!
  \*********************************************/
/*! exports provided: RoleRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "RoleRoutingModule", function() { return RoleRoutingModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _createrole_createrole_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./createrole/createrole.component */ "./src/app/role/createrole/createrole.component.ts");




var routes = [
    {
        path: 'createrole',
        component: _createrole_createrole_component__WEBPACK_IMPORTED_MODULE_3__["CreateroleComponent"],
        data: {
            title: 'Add Role'
        },
    }
];
var RoleRoutingModule = /** @class */ (function () {
    function RoleRoutingModule() {
    }
    RoleRoutingModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"].forChild(routes)],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"]]
        })
    ], RoleRoutingModule);
    return RoleRoutingModule;
}());



/***/ }),

/***/ "./src/app/role/role.module.ts":
/*!*************************************!*\
  !*** ./src/app/role/role.module.ts ***!
  \*************************************/
/*! exports provided: RoleModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "RoleModule", function() { return RoleModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _role_routing_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./role-routing.module */ "./src/app/role/role-routing.module.ts");
/* harmony import */ var _createrole_createrole_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./createrole/createrole.component */ "./src/app/role/createrole/createrole.component.ts");
/* harmony import */ var _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ng-bootstrap/ng-bootstrap */ "./node_modules/@ng-bootstrap/ng-bootstrap/fesm5/ng-bootstrap.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _ng_select_ng_select__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @ng-select/ng-select */ "./node_modules/@ng-select/ng-select/fesm5/ng-select.js");








var RoleModule = /** @class */ (function () {
    function RoleModule() {
    }
    RoleModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            declarations: [_createrole_createrole_component__WEBPACK_IMPORTED_MODULE_4__["CreateroleComponent"]],
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _role_routing_module__WEBPACK_IMPORTED_MODULE_3__["RoleRoutingModule"],
                _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_5__["NgbModule"], _angular_forms__WEBPACK_IMPORTED_MODULE_6__["FormsModule"],
                _ng_select_ng_select__WEBPACK_IMPORTED_MODULE_7__["NgSelectModule"]
            ]
        })
    ], RoleModule);
    return RoleModule;
}());



/***/ })

}]);
//# sourceMappingURL=role-role-module.js.map