(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["store-store-module"],{

/***/ "./node_modules/raw-loader/index.js!./src/app/store/assignstore/assignstore.component.html":
/*!****************************************************************************************!*\
  !*** ./node_modules/raw-loader!./src/app/store/assignstore/assignstore.component.html ***!
  \****************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<!-- <ngx-spinner></ngx-spinner> -->\n<section id=\"image\">\n  <div class=row>\n    <div class=\" col-md-12 \">\n      <div class=\"row\">\n        <div class=\"container-fluid\">\n          <div class=\"row text-left\">\n            <div class=\"col-md-8\">\n              <div class=\"content-header\" style=\"font-size: 20px\">Store Management</div>\n              <br>\n              <p class=\"content-sub-header\"> <a routerLink=\"/dashboard/dashboard1\"><span\n                    style=\"font-size: 15px\">Home</span></a>\n                <span style=\"font-size: 15px\">/Assign Store</span>\n\n              </p>\n            </div>\n\n          </div>\n        </div>\n      </div>\n      <div class=\"card\">\n        <div class=\"card-header\">\n            <div class=\"card-body\">\n            <div class=\"form-group\">\n              <div class=\"col-md-12\">\n                <form class=\"form\" #addClassForm=\"ngForm\" novalidate>\n                  <div class=\"form-body\">\n                    <button type=\"button\" class=\"btn btn-raised btn-info \" routerLink=\"/store/createstore\">\n                      Create Store\n                     </button>\n                     <div class=\"row\">\n                      <div class=\"col-md-6\">\n                        <div class=\"form-group\">\n                          <fieldset class=\"form-group\">\n                            <label for=\"storeSelect\">Select Store</label>\n                            <select class=\"form-control border-primary\" [(ngModel)]=\"store\" required id=\"store\"\n                            (change)='onStoreSelected(store.storeName)' name=\"store\">\n                              <option [ngValue]=\"store\" *ngFor=\"let store of stores\">\n                                {{store.storeName}} </option>\n                            </select>\n                          </fieldset>\n                        </div>\n                      </div>\n                   \n                    <div class=\"col-md-6\">\n                      <div class=\"form-group\">\n                        <label for=\"address\">Store Location\n                        </label>\n                       \n                        <input type=\"text\" id=\"address\" class=\"form-control border-primary\" name=\"address\"\n                          [(ngModel)]=\"selectedStore.address\" disabled>\n\n                      </div>\n                    </div>\n                   \n                     </div>\n                    <div class=\"row\">\n                      \n                      <div class=\"col-md-6\">\n                        <div class=\"form-group\">\n                          <fieldset class=\"form-group\">\n                            <label for=\"roleSelect\">Select Role</label>\n                            <select class=\"form-control border-primary\" [(ngModel)]=\"role\"\n                            (change)='onRoleSelected(role.name)' required id=\"role\" name=\"role\">\n                              <option [ngValue]=\"role\" *ngFor=\"let role of roles\">\n                                {{role.name}} </option>\n                            </select>\n                          </fieldset>\n                        </div>\n                      </div>\n                      <div class=\"col-md-6\">\n                        <fieldset class=\"form-group\">\n                            <label for=\"storeSelect\">Select User </label>\n                            <select required [(ngModel)]=\"employee\" id=\"employee\" name=\"employee\"\n                                class=\"form-control border-primary\">\n                                <option>Select User Name</option>\n                                <option [ngValue]=\"employee\" *ngFor=\"let employee of employees\">\n                                    {{employee.username}} </option>\n                            </select>\n                        </fieldset>\n  \n                    </div>\n                     \n\n                    </div>\n                  \n                  </div>\n                  <div class=\"form-actions center\">\n\n                    <button type=\"button\" class=\"btn btn-raised btn-primary mr-1\" (click)=\"addRole()\"\n                      [disabled]=\"!addClassForm.valid\">\n                      <i class=\"fa fa-check-square-o\"></i> Assign Store\n                    </button>\n                    <button type=\"button\" class=\"btn btn-raised btn-warning\">\n                      <i class=\"ft-x\"></i> Cancel\n                    </button>\n                  </div>\n                </form>\n\n              </div>\n            </div>\n          </div>\n        </div>\n      </div>\n    </div>\n  </div>\n</section>\n"

/***/ }),

/***/ "./node_modules/raw-loader/index.js!./src/app/store/createstore/createstore.component.html":
/*!****************************************************************************************!*\
  !*** ./node_modules/raw-loader!./src/app/store/createstore/createstore.component.html ***!
  \****************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<!-- <ngx-spinner></ngx-spinner> -->\n<section id=\"image\">\n  <div class=row>\n    <div class=\" col-md-12 \">\n      <div class=\"row\">\n        <div class=\"container-fluid\">\n          <div class=\"row text-left\">\n            <div class=\"col-md-8\">\n              <div class=\"content-header\" style=\"font-size: 20px\">Store Management</div>\n              <br>\n              <p class=\"content-sub-header\"> <a routerLink=\"/dashboard/dashboard1\"><span\n                    style=\"font-size: 15px\">Home</span></a>\n                <span style=\"font-size: 15px\">/Add Store</span>\n\n              </p>\n            </div>\n\n          </div>\n        </div>\n      </div>\n      <div class=\"card\">\n        <div class=\"card-header\">\n         \n\n          <div class=\"card-body\">\n            <div class=\"form-group\">\n              <div class=\"col-md-12\">\n                <form class=\"form\" #addClassForm=\"ngForm\" novalidate>\n                  <div class=\"form-body\">\n                    <button type=\"button\" class=\"btn btn-raised btn-info \" routerLink=\"/store/assignstore\">\n                      Assign Store\n                     </button>\n                    <div class=\"row\">\n                      <div class=\"col-md-4\">\n                        <div class=\"form-group\">\n                          <label for=\"storeName\"><span *ngIf=\"storeInfoRequest.storeName == undefined || storeInfoRequest.storeName =='' \"\n                              style=\"color: red\">*</span>Enter Store Name\n                          </label>\n                          <span class=\" text-muted danger\" *ngIf=\"!storeName.valid && (storeName.dirty || storeName.touched)\">\n                            is required!\n                          </span>\n                          <input type=\"text\" id=\"storeName\" class=\"form-control border-primary\" name=\"storeName\"\n                            [(ngModel)]=\"storeInfoRequest.storeName\" ngModel #storeName=\"ngModel\" required>\n\n                        </div>\n                      </div>\n                      <div class=\"col-md-4\">\n                        <div class=\"form-group\">\n                          <label for=\"corpStoreNo\"><span *ngIf=\"storeInfoRequest.corpStoreNo == undefined || storeInfoRequest.corpStoreNo =='' \"\n                              style=\"color: red\">*</span>Enter Corp StoreNo\n                          </label>\n                          <span class=\" text-muted danger\" *ngIf=\"!corpStoreNo.valid && (corpStoreNo.dirty || corpStoreNo.touched)\">\n                            is required!\n                          </span>\n                          <input type=\"text\" id=\"corpStoreNo\" class=\"form-control border-primary\" name=\"corpStoreNo\"\n                            [(ngModel)]=\"storeInfoRequest.corpStoreNo\" ngModel #corpStoreNo=\"ngModel\" required>\n\n                        </div>\n                      </div>\n                      <div class=\"col-md-4\">\n                        <div class=\"form-group\">\n                          <label for=\"serialNumber\"><span *ngIf=\"storeInfoRequest.serialNumber == undefined || storeInfoRequest.serialNumber =='' \"\n                              style=\"color: red\">*</span>Store Serial Number\n                          </label>\n                          <span class=\" text-muted danger\"\n                            *ngIf=\"!serialNumber.valid && (serialNumber.dirty || serialNumber.touched)\">\n                            is required!\n                          </span>\n                          <input type=\"text\" id=\"serialNumber\" class=\"form-control border-primary\" name=\"serialNumber\"\n                            [(ngModel)]=\"storeInfoRequest.serialNumber\" ngModel #serialNumber=\"ngModel\" required>\n\n                        </div>\n                      </div>\n\n                    </div>\n                    <div class=\"row\">\n                      <div class=\"col-md-4\">\n                        <div class=\"form-group\">\n                          <label for=\"address\"><span *ngIf=\"storeInfoRequest.address == undefined || storeInfoRequest.address =='' \"\n                              style=\"color: red\">*</span>Store Location\n                          </label>\n                          <span class=\" text-muted danger\"\n                            *ngIf=\"!address.valid && (address.dirty || address.touched)\">\n                            is required!\n                          </span>\n                          <input type=\"text\" id=\"address\" class=\"form-control border-primary\" name=\"address\"\n                            [(ngModel)]=\"storeInfoRequest.address\" ngModel #address=\"ngModel\" required>\n\n                        </div>\n                      </div>\n                      <div class=\"col-md-4\">\n                        <div class=\"form-group\">\n                          <label for=\"bankName\"><span *ngIf=\"storeInfoRequest.bankName == undefined || storeInfoRequest.bankName =='' \"\n                              style=\"color: red\">*</span>Bank Name\n                          </label>\n                          <span class=\" text-muted danger\"\n                            *ngIf=\"!bankName.valid && (bankName.dirty || bankName.touched)\">\n                            is required!\n                          </span>\n                          <input type=\"text\" id=\"bankName\" class=\"form-control border-primary\" name=\"bankName\"\n                            [(ngModel)]=\"storeInfoRequest.bankName\" ngModel #bankName=\"ngModel\" required>\n\n                        </div>\n                      </div>\n                      <div class=\"col-md-4\">\n                        <div class=\"form-group\">\n                          <label for=\"accountNumber\"><span *ngIf=\"storeInfoRequest.accountNumber == undefined || storeInfoRequest.accountNumber =='' \"\n                              style=\"color: red\">*</span>Account Number\n                          </label>\n                          <span class=\" text-muted danger\"\n                            *ngIf=\"!accountNumber.valid && (accountNumber.dirty || accountNumber.touched)\">\n                            is required!\n                          </span>\n                          <input type=\"text\" id=\"accountNumber\" class=\"form-control border-primary\" name=\"accountNumber\"\n                            [(ngModel)]=\"storeInfoRequest.accountNumber\" ngModel #accountNumber=\"ngModel\" required>\n\n                        </div>\n                      </div>\n                      </div>\n                      <div class=\"row\">\n                        <div class=\"col-md-6\">\n                          <div class=\"form-group\">\n                            <label for=\"minimumBalance\"><span *ngIf=\"storeInfoRequest.minimumBalance == undefined || storeInfoRequest.minimumBalance =='' \"\n                                style=\"color: red\">*</span>Minimum Balance\n                            </label>\n                            <span class=\" text-muted danger\"\n                              *ngIf=\"!minimumBalance.valid && (minimumBalance.dirty || minimumBalance.touched)\">\n                              is required!\n                            </span>\n                            <input type=\"text\" id=\"minimumBalance\" class=\"form-control border-primary\" name=\"minimumBalance\"\n                              [(ngModel)]=\"storeInfoRequest.minimumBalance\" ngModel #minimumBalance=\"ngModel\" required>\n  \n                          </div>\n                        </div>\n                      <!-- <div class=\"col-md-6\">\n                        <div class=\"form-group\">\n                          <label class=\"d-block\">Configured</label>\n                          <div class=\"input-group\">\n                            <div class=\"custom-control custom-radio d-inline-block float-left\">\n                              <input type=\"radio\" checked id=\"active\" name=\"active\" class=\"custom-control-input\"\n                                [value]=true [(ngModel)]=\"storeInfoRequest.configured\">\n                              <label class=\"custom-control-label\" for=\"active\">Yes</label>\n                            </div>\n                            <div class=\"custom-control custom-radio d-inline-block float-left ml-1\">\n                              <input type=\"radio\" id=\"inactive\" name=\"inactive\" class=\"custom-control-input\"\n                                [value]=false [(ngModel)]=\"storeInfoRequest.configured\">\n                              <label class=\"custom-control-label\" for=\"inactive\">No</label>\n                            </div>\n                          </div>\n                        </div>\n                      </div> -->\n                    </div>\n                  </div>\n                  <div class=\"form-actions center\">\n\n                    <button type=\"button\" class=\"btn btn-raised btn-primary mr-1\" (click)=\"addStore()\"\n                      [disabled]=\"!addClassForm.valid\">\n                      <i class=\"fa fa-check-square-o\"></i> Create\n                    </button>\n                    <button type=\"button\" class=\"btn btn-raised btn-warning\">\n                      <i class=\"ft-x\"></i> Cancel\n                    </button>\n                  </div>\n                </form>\n\n              </div>\n            </div>\n          </div>\n        </div>\n      </div>\n    </div>\n  </div>\n</section>\n<section id=\"image\">\n  <div class=row>\n    <div class=\" col-md-12 \">\n      <div class=\"card\">\n\n        <div class=\"card-body\">\n          <div class=\"form-group\">\n            <section id=\"simple\">\n\n              <!-- <div class=\"col-sm-6\">\n                <form storeInfoRequest=\"search\">\n                  <div class=\"position-relative has-icon-right\">\n                    <input type=\"text\" class=\"form-control round\" placeholder=\"Search\" name=\"search\"\n                      [(ngModel)]=\"searchText\" autocomplete=\"off\">\n                    <div class=\"form-control-position\">\n                      <i class=\"ft-search\"></i>\n                    </div>\n                  </div>\n                </form>\n              </div> -->\n              <br>\n              <div class=\"row text-left\">\n\n                <div class=\"col-sm-12\">\n                  <table class=\"table table-responsive-md text-center\">\n                    <thead>\n                      <tr>\n                        <th>S.no</th>\n                        <th>Corp StoreNo</th>\n                        <th>Serial Number</th>\n                        <th>Store Name</th>\n                        <th>Store Location</th>\n                        <th>Status</th>\n                        <th>Action</th>\n                      </tr>\n                    </thead>\n                    <!-- <tbody\n                        *ngFor=\"let storeInfoRequest of storeInfoRequests| slice: (page-1) * pageSize : +((page-1) * pageSize) + +pageSize; index as i\"> -->\n                    <tbody *ngFor=\"let storeInfoRequest of storeInfoRequests;index as i\">\n                      <tr>\n                        <!-- <td>{{i+1+(page-1) * pageSize}}</td> -->\n                        <td>{{i+1}}</td>\n                        <td>{{storeInfoRequest.corpStoreNo}}</td>\n                        <td>{{storeInfoRequest.serialNumber}}</td>\n                        <td>{{storeInfoRequest.storeName}}</td>\n                        <td>{{storeInfoRequest.address}}</td>\n                        <td>\n                          <a class=\"success p-0\" data-original-title=\"\" title=\"\" (click)=\"editstoreInfoRequest(storeInfoRequest)\"\n                            placement=\"bottom\" ngbTooltip=\"Edit\">\n                            <i class=\"ft-edit-2 font-medium-3 mr-2\"></i>\n                          </a>\n                          <!-- <a class=\"danger p-0\" data-original-title=\"\" title=\"\"\n                                                      (click)=\"deleteClass(class.id)\">\n                                                    \n                                                      <i class=\"icon-trash font-medium-3 mr-2\"></i>\n                                                  </a> -->\n                        </td>\n                        <td><button type=\"button\" class=\"btn btn-raised\" disabled style=\"cursor:default\"\n                            [ngClass]=\"{' btn-primary' : storeInfoRequest.configured,'btn-warning' :!storeInfoRequest.configured }\">\n                            {{ storeInfoRequest.configured ? 'Yes' : 'No' }}\n\n                          </button></td>\n\n\n                      </tr>\n                    </tbody>\n                  </table>\n                  <!-- <div *ngIf=\"searchText != ''\">\n                    <table class=\"table table-responsive-md text-center\">\n                      <thead>\n                        <tr>\n                          <th>S.no</th>\n                          <th>Class Name</th>\n                          <th>Edit</th>\n                          <th>Status</th>\n\n                        </tr>\n                      </thead>\n                      <tbody *ngFor=\"let class of classes| filter:searchText ; index as i\">\n                        <tr>\n                          <td>{{i+1+(page-1) * pageSize}}</td>\n                          <td>{{class.name}}</td>\n                          <td>\n                            <a class=\"success p-0\" data-original-title=\"\" title=\"\" (click)=\"editClass(class)\"\n                              placement=\"bottom\" ngbTooltip=\"Edit\">\n                              <i class=\"ft-edit-2 font-medium-3 mr-2\"></i>\n                            </a>\n                            <a class=\"danger p-0\" data-original-title=\"\" title=\"\"\n                                                        (click)=\"deleteClass(class.id)\">\n                                                      \n                                                        <i class=\"icon-trash font-medium-3 mr-2\"></i>\n                                                    </a>\n                          </td>\n                          <td><button type=\"button\" class=\"btn btn-raised\" disabled style=\"cursor:default\"\n                              [ngClass]=\"{' btn-primary' : class.active,'btn-warning' :!class.active }\">\n                              {{ class.active ? 'Active' : 'Inactive' }}\n\n                            </button></td>\n\n\n                        </tr>\n                      </tbody>\n                    </table>\n                  </div> -->\n                  <!-- <div class=\"row\">\n                    <div class=\"col-sm-6\">\n                      <div class=\"formgroup\">\n                        <ngb-pagination [(page)]=\"page\" [pageSize]=\"pageSize\" [collectionSize]=\"classes.length\"\n                          [ellipses]=\"false\" [boundaryLinks]=\"true\"></ngb-pagination>\n                      </div>\n                    </div>\n                    <div class=\"col-sm-6\">\n                      <div class=\"formgroup\">\n                        Items per Page:\n                        <select (change)=\"handlePageSizeChange($event)\">\n                          <option *ngFor=\"let pageSize of pageSizes\" [ngValue]=\"pageSize\">\n                            {{ pageSize }}\n                          </option>\n                        </select>\n                      </div>\n                    </div>\n                  </div> -->\n                </div>\n              </div>\n            </section>\n          </div>\n        </div>\n      </div>\n    </div>\n  </div>\n</section>"

/***/ }),

/***/ "./src/app/model/storeInfoRequest.ts":
/*!*******************************************!*\
  !*** ./src/app/model/storeInfoRequest.ts ***!
  \*******************************************/
/*! exports provided: StoreInfoRequest */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "StoreInfoRequest", function() { return StoreInfoRequest; });
var StoreInfoRequest = /** @class */ (function () {
    function StoreInfoRequest() {
    }
    return StoreInfoRequest;
}());



/***/ }),

/***/ "./src/app/store/assignstore/assignstore.component.scss":
/*!**************************************************************!*\
  !*** ./src/app/store/assignstore/assignstore.component.scss ***!
  \**************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL3N0b3JlL2Fzc2lnbnN0b3JlL2Fzc2lnbnN0b3JlLmNvbXBvbmVudC5zY3NzIn0= */"

/***/ }),

/***/ "./src/app/store/assignstore/assignstore.component.ts":
/*!************************************************************!*\
  !*** ./src/app/store/assignstore/assignstore.component.ts ***!
  \************************************************************/
/*! exports provided: AssignstoreComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "AssignstoreComponent", function() { return AssignstoreComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var app_model_role__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! app/model/role */ "./src/app/model/role.ts");
/* harmony import */ var app_model_storeInfoRequest__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! app/model/storeInfoRequest */ "./src/app/model/storeInfoRequest.ts");
/* harmony import */ var app_model_user__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! app/model/user */ "./src/app/model/user.ts");
/* harmony import */ var app_service_toastr_service__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! app/service/toastr.service */ "./src/app/service/toastr.service.ts");
/* harmony import */ var environments_environment__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! environments/environment */ "./src/environments/environment.ts");









var AssignstoreComponent = /** @class */ (function () {
    function AssignstoreComponent(http, router, service, changeDetectorRefs) {
        this.http = http;
        this.router = router;
        this.service = service;
        this.changeDetectorRefs = changeDetectorRefs;
        this.role = new app_model_role__WEBPACK_IMPORTED_MODULE_4__["Role"]();
        this.store = new app_model_storeInfoRequest__WEBPACK_IMPORTED_MODULE_5__["StoreInfoRequest"]();
        this.selectedStore = new app_model_storeInfoRequest__WEBPACK_IMPORTED_MODULE_5__["StoreInfoRequest"]();
        //selectedStores:StoreInfoRequest[];
        this.employee = new app_model_user__WEBPACK_IMPORTED_MODULE_6__["UserAccount"]();
    }
    AssignstoreComponent.prototype.getStoreList = function () {
        return this.http.get(environments_environment__WEBPACK_IMPORTED_MODULE_8__["environment"].smartSafeAPIUrl + '/storeinfo/all');
    };
    AssignstoreComponent.prototype.getAllStoresList = function () {
        var _this = this;
        return this.getStoreList().
            subscribe(function (data) {
            console.log(data);
            _this.stores = data;
            _this.changeDetectorRefs.markForCheck();
        });
    };
    AssignstoreComponent.prototype.onStoreSelected = function (storeName) {
        var _this = this;
        this.getStoresByStoreName(storeName).
            subscribe(function (data) {
            _this.selectedStore = data;
        });
    };
    AssignstoreComponent.prototype.getStoresByStoreName = function (storeName) {
        return this.http.get(environments_environment__WEBPACK_IMPORTED_MODULE_8__["environment"].smartSafeAPIUrl + '/storeinfo/' + storeName);
    };
    AssignstoreComponent.prototype.getRoleList = function () {
        return this.http.get(environments_environment__WEBPACK_IMPORTED_MODULE_8__["environment"].smartSafeAPIUrl + '/role/all');
    };
    AssignstoreComponent.prototype.getAllRolesList = function () {
        var _this = this;
        return this.getRoleList().
            subscribe(function (data) {
            console.log(data);
            _this.roles = data;
            _this.changeDetectorRefs.markForCheck();
        });
    };
    AssignstoreComponent.prototype.onRoleSelected = function (roleName) {
        var _this = this;
        this.getEmployeesByrole(roleName).
            subscribe(function (data) {
            _this.employees = data;
        });
    };
    AssignstoreComponent.prototype.getEmployeesByrole = function (roleName) {
        return this.http.get(environments_environment__WEBPACK_IMPORTED_MODULE_8__["environment"].smartSafeAPIUrl + '/userInfo/role/' + roleName);
    };
    AssignstoreComponent.prototype.ngOnInit = function () {
        this.getAllStoresList();
        this.getAllRolesList();
    };
    AssignstoreComponent.ctorParameters = function () { return [
        { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"] },
        { type: _angular_router__WEBPACK_IMPORTED_MODULE_3__["Router"] },
        { type: app_service_toastr_service__WEBPACK_IMPORTED_MODULE_7__["NGXToastrService"] },
        { type: _angular_core__WEBPACK_IMPORTED_MODULE_2__["ChangeDetectorRef"] }
    ]; };
    AssignstoreComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_2__["Component"])({
            selector: 'app-assignstore',
            template: __webpack_require__(/*! raw-loader!./assignstore.component.html */ "./node_modules/raw-loader/index.js!./src/app/store/assignstore/assignstore.component.html"),
            providers: [app_service_toastr_service__WEBPACK_IMPORTED_MODULE_7__["NGXToastrService"]],
            styles: [__webpack_require__(/*! ./assignstore.component.scss */ "./src/app/store/assignstore/assignstore.component.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"],
            _angular_router__WEBPACK_IMPORTED_MODULE_3__["Router"],
            app_service_toastr_service__WEBPACK_IMPORTED_MODULE_7__["NGXToastrService"],
            _angular_core__WEBPACK_IMPORTED_MODULE_2__["ChangeDetectorRef"]])
    ], AssignstoreComponent);
    return AssignstoreComponent;
}());



/***/ }),

/***/ "./src/app/store/createstore/createstore.component.scss":
/*!**************************************************************!*\
  !*** ./src/app/store/createstore/createstore.component.scss ***!
  \**************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL3N0b3JlL2NyZWF0ZXN0b3JlL2NyZWF0ZXN0b3JlLmNvbXBvbmVudC5zY3NzIn0= */"

/***/ }),

/***/ "./src/app/store/createstore/createstore.component.ts":
/*!************************************************************!*\
  !*** ./src/app/store/createstore/createstore.component.ts ***!
  \************************************************************/
/*! exports provided: CreatestoreComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CreatestoreComponent", function() { return CreatestoreComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_common_http__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/common/http */ "./node_modules/@angular/common/fesm5/http.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var app_model_storeInfoRequest__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! app/model/storeInfoRequest */ "./src/app/model/storeInfoRequest.ts");
/* harmony import */ var app_service_toastr_service__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! app/service/toastr.service */ "./src/app/service/toastr.service.ts");
/* harmony import */ var environments_environment__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! environments/environment */ "./src/environments/environment.ts");







var CreatestoreComponent = /** @class */ (function () {
    function CreatestoreComponent(http, router, service, changeDetectorRefs) {
        this.http = http;
        this.router = router;
        this.service = service;
        this.changeDetectorRefs = changeDetectorRefs;
        this.storeInfoRequest = new app_model_storeInfoRequest__WEBPACK_IMPORTED_MODULE_4__["StoreInfoRequest"]();
    }
    CreatestoreComponent.prototype.getStoreList = function () {
        return this.http.get(environments_environment__WEBPACK_IMPORTED_MODULE_6__["environment"].smartSafeAPIUrl + '/storeinfo/all');
    };
    CreatestoreComponent.prototype.getAllStoresList = function () {
        var _this = this;
        return this.getStoreList().
            subscribe(function (data) {
            console.log(data);
            _this.storeInfoRequests = data;
            _this.changeDetectorRefs.markForCheck();
        });
    };
    CreatestoreComponent.prototype.addStore = function () {
        var _this = this;
        this.storeInfoRequest.configured = false;
        this.http.post(environments_environment__WEBPACK_IMPORTED_MODULE_6__["environment"].smartSafeAPIUrl + '/storeinfo/', this.storeInfoRequest).subscribe(function (res) {
            console.log(res);
            //event.confirm.resolve(event.newData);
            _this.service.addSuccess();
            _this.getAllStoresList();
        }, function (err) {
            if (err.error instanceof Error) {
                console.log("Client-side error occured.");
            }
            else {
                console.log("Server-side error occured.");
            }
            _this.service.typeWarning();
        });
        console.log(JSON.stringify(this.storeInfoRequest));
        this.getAllStoresList();
    };
    CreatestoreComponent.prototype.ngOnInit = function () {
        this.getAllStoresList();
    };
    CreatestoreComponent.ctorParameters = function () { return [
        { type: _angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"] },
        { type: _angular_router__WEBPACK_IMPORTED_MODULE_3__["Router"] },
        { type: app_service_toastr_service__WEBPACK_IMPORTED_MODULE_5__["NGXToastrService"] },
        { type: _angular_core__WEBPACK_IMPORTED_MODULE_2__["ChangeDetectorRef"] }
    ]; };
    CreatestoreComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_2__["Component"])({
            selector: 'app-createstore',
            template: __webpack_require__(/*! raw-loader!./createstore.component.html */ "./node_modules/raw-loader/index.js!./src/app/store/createstore/createstore.component.html"),
            providers: [app_service_toastr_service__WEBPACK_IMPORTED_MODULE_5__["NGXToastrService"]],
            styles: [__webpack_require__(/*! ./createstore.component.scss */ "./src/app/store/createstore/createstore.component.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [_angular_common_http__WEBPACK_IMPORTED_MODULE_1__["HttpClient"],
            _angular_router__WEBPACK_IMPORTED_MODULE_3__["Router"],
            app_service_toastr_service__WEBPACK_IMPORTED_MODULE_5__["NGXToastrService"],
            _angular_core__WEBPACK_IMPORTED_MODULE_2__["ChangeDetectorRef"]])
    ], CreatestoreComponent);
    return CreatestoreComponent;
}());



/***/ }),

/***/ "./src/app/store/store-routing.module.ts":
/*!***********************************************!*\
  !*** ./src/app/store/store-routing.module.ts ***!
  \***********************************************/
/*! exports provided: StoreRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "StoreRoutingModule", function() { return StoreRoutingModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _assignstore_assignstore_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./assignstore/assignstore.component */ "./src/app/store/assignstore/assignstore.component.ts");
/* harmony import */ var _createstore_createstore_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./createstore/createstore.component */ "./src/app/store/createstore/createstore.component.ts");





var routes = [
    {
        path: 'createstore',
        component: _createstore_createstore_component__WEBPACK_IMPORTED_MODULE_4__["CreatestoreComponent"],
        data: {
            title: 'Store Management'
        },
    },
    {
        path: 'assignstore',
        component: _assignstore_assignstore_component__WEBPACK_IMPORTED_MODULE_3__["AssignstoreComponent"],
        data: {
            title: 'Assign Store'
        },
    }
];
var StoreRoutingModule = /** @class */ (function () {
    function StoreRoutingModule() {
    }
    StoreRoutingModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"].forChild(routes)],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"]]
        })
    ], StoreRoutingModule);
    return StoreRoutingModule;
}());



/***/ }),

/***/ "./src/app/store/store.module.ts":
/*!***************************************!*\
  !*** ./src/app/store/store.module.ts ***!
  \***************************************/
/*! exports provided: StoreModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "StoreModule", function() { return StoreModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _ng_select_ng_select__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! @ng-select/ng-select */ "./node_modules/@ng-select/ng-select/fesm5/ng-select.js");
/* harmony import */ var _store_routing_module__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./store-routing.module */ "./src/app/store/store-routing.module.ts");
/* harmony import */ var _createstore_createstore_component__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! ./createstore/createstore.component */ "./src/app/store/createstore/createstore.component.ts");
/* harmony import */ var _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @ng-bootstrap/ng-bootstrap */ "./node_modules/@ng-bootstrap/ng-bootstrap/fesm5/ng-bootstrap.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _assignstore_assignstore_component__WEBPACK_IMPORTED_MODULE_8__ = __webpack_require__(/*! ./assignstore/assignstore.component */ "./src/app/store/assignstore/assignstore.component.ts");









var StoreModule = /** @class */ (function () {
    function StoreModule() {
    }
    StoreModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            declarations: [_createstore_createstore_component__WEBPACK_IMPORTED_MODULE_5__["CreatestoreComponent"], _assignstore_assignstore_component__WEBPACK_IMPORTED_MODULE_8__["AssignstoreComponent"]],
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _store_routing_module__WEBPACK_IMPORTED_MODULE_4__["StoreRoutingModule"],
                _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_6__["NgbModule"], _angular_forms__WEBPACK_IMPORTED_MODULE_7__["FormsModule"],
                _ng_select_ng_select__WEBPACK_IMPORTED_MODULE_3__["NgSelectModule"]
            ]
        })
    ], StoreModule);
    return StoreModule;
}());



/***/ })

}]);
//# sourceMappingURL=store-store-module.js.map