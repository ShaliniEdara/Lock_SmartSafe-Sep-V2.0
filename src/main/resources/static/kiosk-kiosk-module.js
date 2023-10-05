(window["webpackJsonp"] = window["webpackJsonp"] || []).push([["kiosk-kiosk-module"],{

/***/ "./node_modules/raw-loader/index.js!./src/app/kiosk/createkiosk/createkiosk.component.html":
/*!****************************************************************************************!*\
  !*** ./node_modules/raw-loader!./src/app/kiosk/createkiosk/createkiosk.component.html ***!
  \****************************************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "<!-- <ngx-spinner></ngx-spinner> -->\n<section id=\"image\">\n  <div class=row>\n    <div class=\" col-md-12 \">\n      <div class=\"row\">\n        <div class=\"container-fluid\">\n          <div class=\"row text-left\">\n            <div class=\"col-md-8\">\n              <div class=\"content-header\" style=\"font-size: 20px\">Kiosk Management</div>\n              <br>\n              <p class=\"content-sub-header\"> <a routerLink=\"/dashboard/dashboard1\"><span\n                    style=\"font-size: 15px\">Home</span></a>\n                <span style=\"font-size: 15px\">/Create Kiosk</span>\n\n              </p>\n            </div>\n\n          </div>\n        </div>\n      </div>\n      <div class=\"card\">\n        <div class=\"card-header\">\n\n\n          <div class=\"card-body\">\n            <div class=\"form-group\">\n              <div class=\"col-md-12\">\n                <form class=\"form\" #addClassForm=\"ngForm\" novalidate>\n                  <div class=\"form-body\">\n                    <button type=\"button\" class=\"btn btn-raised btn-info \" routerLink=\"/store/assignstore\">\n                      Assign Store\n                    </button>\n                    <div class=\"row\">\n                      <div class=\"col-md-6\">\n                        <div class=\"form-group\">\n                          <label for=\"name\"><span *ngIf=\"role.name == undefined || role.name =='' \"\n                              style=\"color: red\">*</span>Enter Kiosk Id\n                          </label>\n                          <span class=\" text-muted danger\" *ngIf=\"!name.valid && (name.dirty || name.touched)\">\n                            is required!\n                          </span>\n                          <input type=\"text\" id=\"name\" class=\"form-control border-primary\" name=\"name\"\n                            [(ngModel)]=\"role.name\" ngModel #name=\"ngModel\" required>\n\n                        </div>\n                      </div>\n                      <div class=\"col-md-6\">\n                        <div class=\"form-group\">\n                          <label for=\"description\"><span *ngIf=\"role.description == undefined || role.description =='' \"\n                              style=\"color: red\">*</span>Kiosk Name\n                          </label>\n                          <span class=\" text-muted danger\"\n                            *ngIf=\"!description.valid && (description.dirty || description.touched)\">\n                            is required!\n                          </span>\n                          <input type=\"text\" id=\"description\" class=\"form-control border-primary\" name=\"description\"\n                            [(ngModel)]=\"role.description\" ngModel #description=\"ngModel\" required>\n\n                        </div>\n                      </div>\n                    </div>\n                    <div class=\"row\">\n                      <div class=\"col-md-6\">\n                        <div class=\"form-group\">\n                          <label for=\"name\"><span *ngIf=\"role.name == undefined || role.name =='' \"\n                              style=\"color: red\">*</span>Bill Validator Id\n                          </label>\n                          <span class=\" text-muted danger\" *ngIf=\"!name.valid && (name.dirty || name.touched)\">\n                            is required!\n                          </span>\n                          <input type=\"text\" id=\"name\" class=\"form-control border-primary\" name=\"name\"\n                            [(ngModel)]=\"role.name\" ngModel #name=\"ngModel\" required>\n\n                        </div>\n                      </div>\n                      <div class=\"col-md-6\">\n                        <div class=\"form-group\">\n                          <label for=\"description\"><span *ngIf=\"role.description == undefined || role.description =='' \"\n                              style=\"color: red\">*</span>Bill Validator Name\n                          </label>\n                          <span class=\" text-muted danger\"\n                            *ngIf=\"!description.valid && (description.dirty || description.touched)\">\n                            is required!\n                          </span>\n                          <input type=\"text\" id=\"description\" class=\"form-control border-primary\" name=\"description\"\n                            [(ngModel)]=\"role.description\" ngModel #description=\"ngModel\" required>\n\n                        </div>\n                      </div>\n\n                    </div>\n                    <div class=\"row\">\n                      <div class=\"col-md-6\">\n                        <div class=\"form-group\">\n                          <label for=\"name\"><span *ngIf=\"role.name == undefined || role.name =='' \"\n                              style=\"color: red\">*</span>Printer Id\n                          </label>\n                          <span class=\" text-muted danger\" *ngIf=\"!name.valid && (name.dirty || name.touched)\">\n                            is required!\n                          </span>\n                          <input type=\"text\" id=\"name\" class=\"form-control border-primary\" name=\"name\"\n                            [(ngModel)]=\"role.name\" ngModel #name=\"ngModel\" required>\n\n                        </div>\n                      </div>\n                      <div class=\"col-md-6\">\n                        <div class=\"form-group\">\n                          <label for=\"description\"><span *ngIf=\"role.description == undefined || role.description =='' \"\n                              style=\"color: red\">*</span>Printer Name\n                          </label>\n                          <span class=\" text-muted danger\"\n                            *ngIf=\"!description.valid && (description.dirty || description.touched)\">\n                            is required!\n                          </span>\n                          <input type=\"text\" id=\"description\" class=\"form-control border-primary\" name=\"description\"\n                            [(ngModel)]=\"role.description\" ngModel #description=\"ngModel\" required>\n\n                        </div>\n                      </div>\n\n                    </div>\n                    <div class=\"row\">\n                      <div class=\"col-md-6\">\n                        <div class=\"form-group\">\n                          <label class=\"d-block\">Status</label>\n                          <div class=\"input-group\">\n                            <div class=\"custom-control custom-radio d-inline-block float-left\">\n                              <input type=\"radio\" checked id=\"active\" name=\"active\" class=\"custom-control-input\"\n                                [value]=true [(ngModel)]=\"role.active\">\n                              <label class=\"custom-control-label\" for=\"active\">Active</label>\n                            </div>\n                            <div class=\"custom-control custom-radio d-inline-block float-left ml-1\">\n                              <input type=\"radio\" id=\"inactive\" name=\"inactive\" class=\"custom-control-input\"\n                                [value]=false [(ngModel)]=\"role.active\">\n                              <label class=\"custom-control-label\" for=\"inactive\">In Active</label>\n                            </div>\n                          </div>\n                        </div>\n                      </div>\n                      <div class=\"col-md-6\">\n                        <fieldset class=\"form-group\">\n                          <label for=\"storeSelect\">Select Store Id</label>\n                          <select required [(ngModel)]=\"role\" id=\"role\" name=\"role\" class=\"form-control border-primary\">\n                            <option>Select Store Id</option>\n                            <option [ngValue]=\"role\" *ngFor=\"let role of roles\">\n                              {{role.name}} </option>\n                          </select>\n                        </fieldset>\n\n                      </div>\n                    </div>\n                  </div>\n             \n                  <div class=\"form-actions center\">\n\n                    <button type=\"button\" class=\"btn btn-raised btn-primary mr-1\" (click)=\"addRole()\"\n                      [disabled]=\"!addClassForm.valid\">\n                      <i class=\"fa fa-check-square-o\"></i> Create\n                    </button>\n                    <button type=\"button\" class=\"btn btn-raised btn-warning\">\n                      <i class=\"ft-x\"></i> Cancel\n                    </button>\n                   </div>\n                 </form>\n             </div>\n           </div>\n         </div>\n       </div>\n     </div>\n   </div>\n </div>\n</section>\n<section id=\"image\">\n  <div class=row>\n    <div class=\" col-md-12 \">\n      <div class=\"card\">\n\n        <div class=\"card-body\">\n          <div class=\"form-group\">\n            <section id=\"simple\">\n\n              <!-- <div class=\"col-sm-6\">\n                <form role=\"search\">\n                  <div class=\"position-relative has-icon-right\">\n                    <input type=\"text\" class=\"form-control round\" placeholder=\"Search\" name=\"search\"\n                      [(ngModel)]=\"searchText\" autocomplete=\"off\">\n                    <div class=\"form-control-position\">\n                      <i class=\"ft-search\"></i>\n                    </div>\n                  </div>\n                </form>\n              </div> -->\n              <br>\n              <div class=\"row text-left\">\n\n                <div class=\"col-sm-12\">\n                  <table class=\"table table-responsive-md text-center\">\n                    <thead>\n                      <tr>\n                        <th>S.no</th>\n                        <th>Store Id</th>\n                        <th>Store Name</th>\n                        <th>Store Location</th>\n                        <th>Status</th>\n                        <th>Action</th>\n                      </tr>\n                    </thead>\n                    <!-- <tbody\n                        *ngFor=\"let role of roles| slice: (page-1) * pageSize : +((page-1) * pageSize) + +pageSize; index as i\"> -->\n                    <tbody *ngFor=\"let role of roles\">\n                      <tr>\n                        <td>{{i+1+(page-1) * pageSize}}</td>\n                        <td>{{role.name}}</td>\n                        <td>\n                          <a class=\"success p-0\" data-original-title=\"\" title=\"\" (click)=\"editRole(role)\"\n                            placement=\"bottom\" ngbTooltip=\"Edit\">\n                            <i class=\"ft-edit-2 font-medium-3 mr-2\"></i>\n                          </a>\n                          <!-- <a class=\"danger p-0\" data-original-title=\"\" title=\"\"\n                                                      (click)=\"deleteClass(class.id)\">\n                                                    \n                                                      <i class=\"icon-trash font-medium-3 mr-2\"></i>\n                                                  </a> -->\n                        </td>\n                        <td><button type=\"button\" class=\"btn btn-raised\" disabled style=\"cursor:default\"\n                            [ngClass]=\"{' btn-primary' : role.active,'btn-warning' :!role.active }\">\n                            {{ role.active ? 'Active' : 'Inactive' }}\n\n                          </button></td>\n\n\n                      </tr>\n                    </tbody>\n                  </table>\n                  <!-- <div *ngIf=\"searchText != ''\">\n                    <table class=\"table table-responsive-md text-center\">\n                      <thead>\n                        <tr>\n                          <th>S.no</th>\n                          <th>Class Name</th>\n                          <th>Edit</th>\n                          <th>Status</th>\n\n                        </tr>\n                      </thead>\n                      <tbody *ngFor=\"let class of classes| filter:searchText ; index as i\">\n                        <tr>\n                          <td>{{i+1+(page-1) * pageSize}}</td>\n                          <td>{{class.name}}</td>\n                          <td>\n                            <a class=\"success p-0\" data-original-title=\"\" title=\"\" (click)=\"editClass(class)\"\n                              placement=\"bottom\" ngbTooltip=\"Edit\">\n                              <i class=\"ft-edit-2 font-medium-3 mr-2\"></i>\n                            </a>\n                            <a class=\"danger p-0\" data-original-title=\"\" title=\"\"\n                                                        (click)=\"deleteClass(class.id)\">\n                                                      \n                                                        <i class=\"icon-trash font-medium-3 mr-2\"></i>\n                                                    </a>\n                          </td>\n                          <td><button type=\"button\" class=\"btn btn-raised\" disabled style=\"cursor:default\"\n                              [ngClass]=\"{' btn-primary' : class.active,'btn-warning' :!class.active }\">\n                              {{ class.active ? 'Active' : 'Inactive' }}\n\n                            </button></td>\n\n\n                        </tr>\n                      </tbody>\n                    </table>\n                  </div> -->\n                  <!-- <div class=\"row\">\n                    <div class=\"col-sm-6\">\n                      <div class=\"formgroup\">\n                        <ngb-pagination [(page)]=\"page\" [pageSize]=\"pageSize\" [collectionSize]=\"classes.length\"\n                          [ellipses]=\"false\" [boundaryLinks]=\"true\"></ngb-pagination>\n                      </div>\n                    </div>\n                    <div class=\"col-sm-6\">\n                      <div class=\"formgroup\">\n                        Items per Page:\n                        <select (change)=\"handlePageSizeChange($event)\">\n                          <option *ngFor=\"let pageSize of pageSizes\" [ngValue]=\"pageSize\">\n                            {{ pageSize }}\n                          </option>\n                        </select>\n                      </div>\n                    </div>\n                  </div> -->\n                </div>\n              </div>\n            </section>\n          </div>\n        </div>\n      </div>\n    </div>\n  </div>\n</section>"

/***/ }),

/***/ "./src/app/kiosk/createkiosk/createkiosk.component.scss":
/*!**************************************************************!*\
  !*** ./src/app/kiosk/createkiosk/createkiosk.component.scss ***!
  \**************************************************************/
/*! no static exports found */
/***/ (function(module, exports) {

module.exports = "\n/*# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbXSwibmFtZXMiOltdLCJtYXBwaW5ncyI6IiIsImZpbGUiOiJzcmMvYXBwL2tpb3NrL2NyZWF0ZWtpb3NrL2NyZWF0ZWtpb3NrLmNvbXBvbmVudC5zY3NzIn0= */"

/***/ }),

/***/ "./src/app/kiosk/createkiosk/createkiosk.component.ts":
/*!************************************************************!*\
  !*** ./src/app/kiosk/createkiosk/createkiosk.component.ts ***!
  \************************************************************/
/*! exports provided: CreatekioskComponent */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "CreatekioskComponent", function() { return CreatekioskComponent; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var app_model_role__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! app/model/role */ "./src/app/model/role.ts");



var CreatekioskComponent = /** @class */ (function () {
    function CreatekioskComponent() {
        this.role = new app_model_role__WEBPACK_IMPORTED_MODULE_2__["Role"]();
    }
    CreatekioskComponent.prototype.ngOnInit = function () {
    };
    CreatekioskComponent = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["Component"])({
            selector: 'app-createkiosk',
            template: __webpack_require__(/*! raw-loader!./createkiosk.component.html */ "./node_modules/raw-loader/index.js!./src/app/kiosk/createkiosk/createkiosk.component.html"),
            styles: [__webpack_require__(/*! ./createkiosk.component.scss */ "./src/app/kiosk/createkiosk/createkiosk.component.scss")]
        }),
        tslib__WEBPACK_IMPORTED_MODULE_0__["__metadata"]("design:paramtypes", [])
    ], CreatekioskComponent);
    return CreatekioskComponent;
}());



/***/ }),

/***/ "./src/app/kiosk/kiosk-routing.module.ts":
/*!***********************************************!*\
  !*** ./src/app/kiosk/kiosk-routing.module.ts ***!
  \***********************************************/
/*! exports provided: KioskRoutingModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "KioskRoutingModule", function() { return KioskRoutingModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_router__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/router */ "./node_modules/@angular/router/fesm5/router.js");
/* harmony import */ var _createkiosk_createkiosk_component__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./createkiosk/createkiosk.component */ "./src/app/kiosk/createkiosk/createkiosk.component.ts");




var routes = [
    {
        path: 'createkiosk',
        component: _createkiosk_createkiosk_component__WEBPACK_IMPORTED_MODULE_3__["CreatekioskComponent"],
        data: {
            title: 'Kiosk Management'
        },
    },
];
var KioskRoutingModule = /** @class */ (function () {
    function KioskRoutingModule() {
    }
    KioskRoutingModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            imports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"].forChild(routes)],
            exports: [_angular_router__WEBPACK_IMPORTED_MODULE_2__["RouterModule"]]
        })
    ], KioskRoutingModule);
    return KioskRoutingModule;
}());



/***/ }),

/***/ "./src/app/kiosk/kiosk.module.ts":
/*!***************************************!*\
  !*** ./src/app/kiosk/kiosk.module.ts ***!
  \***************************************/
/*! exports provided: KioskModule */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
__webpack_require__.r(__webpack_exports__);
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "KioskModule", function() { return KioskModule; });
/* harmony import */ var tslib__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! tslib */ "./node_modules/tslib/tslib.es6.js");
/* harmony import */ var _angular_core__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! @angular/core */ "./node_modules/@angular/core/fesm5/core.js");
/* harmony import */ var _angular_common__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! @angular/common */ "./node_modules/@angular/common/fesm5/common.js");
/* harmony import */ var _kiosk_routing_module__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./kiosk-routing.module */ "./src/app/kiosk/kiosk-routing.module.ts");
/* harmony import */ var _createkiosk_createkiosk_component__WEBPACK_IMPORTED_MODULE_4__ = __webpack_require__(/*! ./createkiosk/createkiosk.component */ "./src/app/kiosk/createkiosk/createkiosk.component.ts");
/* harmony import */ var _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_5__ = __webpack_require__(/*! @ng-bootstrap/ng-bootstrap */ "./node_modules/@ng-bootstrap/ng-bootstrap/fesm5/ng-bootstrap.js");
/* harmony import */ var _angular_forms__WEBPACK_IMPORTED_MODULE_6__ = __webpack_require__(/*! @angular/forms */ "./node_modules/@angular/forms/fesm5/forms.js");
/* harmony import */ var _ng_select_ng_select__WEBPACK_IMPORTED_MODULE_7__ = __webpack_require__(/*! @ng-select/ng-select */ "./node_modules/@ng-select/ng-select/fesm5/ng-select.js");








var KioskModule = /** @class */ (function () {
    function KioskModule() {
    }
    KioskModule = tslib__WEBPACK_IMPORTED_MODULE_0__["__decorate"]([
        Object(_angular_core__WEBPACK_IMPORTED_MODULE_1__["NgModule"])({
            declarations: [_createkiosk_createkiosk_component__WEBPACK_IMPORTED_MODULE_4__["CreatekioskComponent"]],
            imports: [
                _angular_common__WEBPACK_IMPORTED_MODULE_2__["CommonModule"],
                _kiosk_routing_module__WEBPACK_IMPORTED_MODULE_3__["KioskRoutingModule"],
                _ng_bootstrap_ng_bootstrap__WEBPACK_IMPORTED_MODULE_5__["NgbModule"],
                _angular_forms__WEBPACK_IMPORTED_MODULE_6__["FormsModule"],
                _ng_select_ng_select__WEBPACK_IMPORTED_MODULE_7__["NgSelectModule"]
            ]
        })
    ], KioskModule);
    return KioskModule;
}());



/***/ })

}]);
//# sourceMappingURL=kiosk-kiosk-module.js.map